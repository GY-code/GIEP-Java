/**
 * @title Server.java
 *
 * @version 1.0
 *
 * @date 2020-11-25 05:03:52 
 * 
 * @author Yi Gu,SSE of BJTU
 * 
 */

package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author Yi Gu,SSE of BJTU
 *
 * @title Server
 *
 * @description
 *
 */
public class PlatformServer implements Runnable {
	// Separate IO for customers
	private DataInputStream in;
	private DataOutputStream out;
	private Socket cSocket;

	public PlatformServer(Socket cSocket) {
		// TODO Auto-generated constructor stub
		this.cSocket = cSocket;
		try {
			InputStream input = cSocket.getInputStream();
			OutputStream output = cSocket.getOutputStream();
			in = new DataInputStream(input);
			out = new DataOutputStream(output);
			out.flush();
			System.out.println("IO is connected with the Client.");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void close() {
		try {
			out.close();
			in.close();
			cSocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @description thread run from here
	 *
	 */
	private String id = null;
	private String pin;

	public void run() {
		try {
			// true stands for server should keep listening to the object back from the
			// client
			// receive the customer from the client, cover the new one and return the
			// success signal
			while (in.readBoolean()) {
				String instruction = in.readUTF();
				// login operation
				if (instruction.equals("login")) {
					login();
				}
				// register operation
				if (instruction.equals("register")) {
					registerIn();
				}
				if (instruction.equals("moments")) {
					outMoments();
				}
				if (instruction.equals("scores")) {
					outScores();
				}
				if (instruction.equals("inmoments")) {
					inMoments();
				}
				if (instruction.equals("writescores")) {
					inScore();
				}

				// more operations

			}
			System.out.println("A client exit.");
			close();

		} catch (IOException e) {
		} finally {
			// set login status to false
			String sql = "UPDATE platform_data.info SET login = 0 WHERE id= \'" + id + "\'";
			DataBaseIO.updateData(sql);
		}

	}

	/**
	 * @description method for responding login situation
	 *
	 */
	private void login() {
		// TODO Auto-generated method stub
		// attempt for logging in

		try {

			// read the information and match
			id = in.readUTF();
			pin = in.readUTF();
			String sql = "SELECT * FROM info WHERE id = \'" + id + "\' AND pin = \'" + pin + "\'";
			ResultSet rs = DataBaseIO.queryData(sql);
			if (rs != null && rs.next()) {
				if (rs.getBoolean("login")) {
					out.writeBoolean(false);
					// already log in
					System.out.println("This customer has already logged in.");
					out.writeChar(1);
					out.flush();
					DataBaseIO.closeStatement();
					return;
				}
				out.writeBoolean(true);
				System.out.println("Client login successfully.");
				out.writeInt(rs.getInt("gender"));
				out.writeUTF(rs.getString("motto"));
				out.writeUTF(rs.getString("friends_name"));
				out.writeInt(rs.getInt("pic"));
				out.flush();
				// set login status to true
				sql = "UPDATE platform_data.info SET login = 1 WHERE id= \'" + id + "\'";
				DataBaseIO.updateData(sql);
				System.out.println("Server has return the corresponding info.");
			} else {
				out.writeBoolean(false);
				// no search result
				System.out.println("Client has a false try.");
				out.writeChar(0);
				out.flush();

			}
			DataBaseIO.closeStatement();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @description create a new account
	 *
	 */
	private void registerIn() {
		// TODO Auto-generated method stub
		try {
			String id = in.readUTF();
			String pin = in.readUTF();
			int gender = in.readChar();
			String motto = in.readUTF();
			int pic = in.readChar();
			String sql = "SELECT * FROM platform_data.info WHERE id = \'" + id + "\'";
			ResultSet rs = DataBaseIO.queryData(sql);
			if (rs != null && rs.next()) {
				out.writeBoolean(false);
				out.flush();

			} else {
				sql = "INSERT INTO platform_data.info (id, pin, gender, motto, friends_name, friends_request, pic, login) VALUES (\'"
						+ id + "\', \'" + pin + "\', " + gender + ", \'" + motto + "\', DEFAULT, DEFAULT," + pic
						+ ", DEFAULT)";
				DataBaseIO.updateData(sql);
				out.writeBoolean(true);
			}
			DataBaseIO.closeStatement();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @description output the moments content
	 *
	 */
	private void outMoments() {
		String sql = "SELECT count(*) from platform_data.moments";
		ResultSet rs = DataBaseIO.queryData(sql);
		try {
			rs.next();
			int turn = rs.getInt("count(*)");
			out.writeInt(turn);
			out.flush();
			sql = "SELECT * from platform_data.moments order by time desc";
			rs = DataBaseIO.queryData(sql);
			for (int i = 0; i < turn; i++) {
				rs.next();
				String id = rs.getString("id");
				int pic = rs.getInt("pic");
				String text = rs.getString("text");
				String time = rs.getString("time");
				out.writeUTF(id);
				out.writeInt(pic);
				out.writeUTF(text);
				out.writeUTF(time);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseIO.closeStatement();
	}

	/**
	 * @description the main methods of the server
	 *
	 */

	/**
	 * @description write the new moments to the database
	 *
	 */
	private void inMoments() {
		try {
			String id = in.readUTF();
			int pic = in.readInt();
			String text = in.readUTF();
			String sql = "INSERT INTO platform_data.moments (id, pic, text, time) VALUES (\'" + id + "\', " + pic
					+ ", \'" + text + "\', DEFAULT)";
			DataBaseIO.updateData(sql);
			out.writeBoolean(true);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * @description output the scores content
	 *
	 */
	private void outScores() {
		String sql = "SELECT count(*) from platform_data.score";
		ResultSet rs = DataBaseIO.queryData(sql);
		try {
			rs.next();
			int turn = rs.getInt("count(*)");
			out.writeInt(turn);
			out.flush();
			sql = "SELECT * from platform_data.score order by fight_plane desc";
			rs = DataBaseIO.queryData(sql);
			for (int i = 0; i < turn; i++) {
				rs.next();
				String id = rs.getString("id");
				int pic = rs.getInt("pic");
				int score = rs.getInt("fight_plane");
				String time = rs.getString("time");
				out.writeUTF(id);
				out.writeInt(pic);
				out.writeUTF(score+"");
				out.writeUTF(time);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DataBaseIO.closeStatement();
	}

	/**
	 * @description write the new score to the database
	 *
	 */
	private void inScore() {
		try {
			String id = in.readUTF();
			int pic = in.readInt();
			int score = in.readInt();
			String sql = "INSERT INTO platform_data.score (id, pic, fight_plane, time) VALUES (\'" + id + "\', " + pic
					+ ", " + score + ", DEFAULT)";
			DataBaseIO.updateData(sql);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public static void main(String args[]) {
		System.out.println("Platform Server started.");
		ServerSocket sSocket = null;
		try {
			sSocket = new ServerSocket(8888);
			System.out.println("Platform Server Socket is made.");
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		System.out.println("Conecting to the DataBase");
		DataBaseIO.connect("root", "mysql");
		while (true) {
			try {
				Socket cSocket = sSocket.accept();
				// when a new client is connected, a new thread started
				System.out.println("A Client connected.");
				new Thread(new PlatformServer(cSocket)).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}
