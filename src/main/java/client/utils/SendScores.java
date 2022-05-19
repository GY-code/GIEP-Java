/**
 * @title SendScores.java
 *
 * @version 1.0
 *
 * @date 2020-11-27 09:44:15 
 * 
 * @author Yi Gu,SSE of BJTU
 * 
 */

package client.utils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

import client.net.NetClient;

/**
 * @author Yi Gu,SSE of BJTU
 *
 * @title SendScores
 *
 * @description
 *
 */
public class SendScores {
	public static void sendScores2Server(int score) {
		NetClient client = NetClient.getNetClient();
		DataInputStream in = client.getIn();
		DataOutputStream out = client.getOut();
		try {
			PlayerInfo info = PlayerInfo.getPlayerInfo();
			out.writeBoolean(true);
			out.writeUTF("writescores");
			out.writeUTF(info.getId());
			out.writeInt(info.getPic());
			out.writeInt(score);
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
//	public static void main(String[] args) {
//		PlayerInfo info = PlayerInfo.getPlayerInfo();
//		info.setId("test id");
//		info.setPic(1);
//		sendScores2Server(9999);
//
//	}

}
