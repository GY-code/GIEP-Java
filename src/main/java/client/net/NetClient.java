/**
 * @title netClient.java
 *
 * @version 1.0
 *
 * @date 2020-11-25 10:15:15 
 * 
 * @author Yi Gu,SSE of BJTU
 * 
 */  
 
package client.net;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * @author Yi Gu,SSE of BJTU
 *
 * @title netClient
 *
 * @description class works for net connecting client 
 *
 */
public class NetClient {

	//singleton design pattern
	private static NetClient instance=new NetClient();
	// i-o stream for connecting
	private DataInputStream in;
	private DataOutputStream out;
	private Socket Socket;

	public static NetClient getNetClient() {
		return instance;
	}
	private NetClient() {

		try {
			System.out.println("Platform Client started.");
			Socket = new Socket("127.0.0.1", 8888);
			System.out.println("Customer Client has connected with the Server.");
			InputStream input = Socket.getInputStream();
			OutputStream output = Socket.getOutputStream();
			out = new DataOutputStream(output);
			out.flush();
			in = new DataInputStream(input);
			System.out.println("IO is established with the Platform Server.");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();

		}

	}

	/**
	 * @return the in
	 */
	public DataInputStream getIn() {
		return in;
	}

	/**
	 * @return the out
	 */
	public DataOutputStream getOut() {
		return out;
	}

	public void close() {
		try {
			out.close();
			in.close();
			Socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
