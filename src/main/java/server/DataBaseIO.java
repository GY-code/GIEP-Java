/**
 * @title DatabaseIO.java
 *
 * @version 1.0
 *
 * @date 2020-11-25 05:07:54 
 * 
 * @author Yi Gu,SSE of BJTU
 * 
 */

package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.mysql.cj.xdevapi.PreparableStatement;

/**
 * @author Yi Gu,SSE of BJTU
 *
 * @title DatabaseIO
 *
 * @description handle the data exchange with MySQL database
 *
 */
public class DataBaseIO {
	// driver for connection
	static final String driver = "com.mysql.cj.jdbc.Driver";
	// The URL points to the database to be accessed
	// ****My DataBase name: account_data****
	static final String url = "jdbc:mysql://localhost:3306/platform_data?useSSL=false&"
			+ "allowPublicKeyRetrieval=true&serverTimezone=UTC";
	// MySQL userName
	static String user;
	// MySQL password
	static String password;
	// MySQL connection
	static Connection conn;
	static Statement statement;

	public static void connect(String u, String p) {
		user = u;
		password = p;
		// load driver
		try {
			Class.forName(driver);
			// Connect to MySQL database
			conn = DriverManager.getConnection(url, user, password);
			if (!conn.isClosed())
				System.out.println("Succeeded connecting to the Database.");
		} catch (ClassNotFoundException e) {
			System.out.println("Sorry,can`t find the Driver!");
			e.printStackTrace();
		} catch (SQLException e) {
			System.out.println("Sorry,SQL Exception occurred.");
			e.printStackTrace();
		}
		System.out.println("Database data obtained successfully.");

	}

	public static void closeConn() {
		try {
			conn.close();
		} catch (SQLException e) {
			System.out.println("Sorry,SQL Exception occurred.");
			e.printStackTrace();
		}
	}

	public static void closeStatement() {
		try {
			statement.close();
		} catch (SQLException e) {
			System.out.println("Sorry,SQL Exception occurred.");
			e.printStackTrace();
		}
	}
	//using string to operate to query
	public static ResultSet queryData(String sql) {
		try {
			if(conn==null) {
				System.out.println("null!");
			}
			statement = conn.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			return rs;
		} catch (SQLException e) {
			System.out.println("Sorry,SQL Exception occurred.");
			e.printStackTrace();
		} 
		return null;

	}
	//using string to update
	public static void updateData(String sqlStr) {
		try {
			// Create a statement class object to execute SQL statements
			Statement statement = conn.createStatement();
			// read the information and write into database
			statement.executeUpdate(sqlStr);
			statement.close();
		} catch (SQLException e) {
			System.out.println("Sorry,SQL Exception occurred.");
			e.printStackTrace();
		} finally {
			System.out.println("Update database Successfully.");
		}

	}

}
