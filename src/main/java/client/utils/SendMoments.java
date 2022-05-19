/**
 * @title SendMoments.java
 *
 * @version 1.0
 *
 * @date 2020-11-27 05:45:02 
 * 
 * @author Yi Gu,SSE of BJTU
 * 
 */

package client.utils;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import client.net.NetClient;

/**
 * @author Yi Gu,SSE of BJTU
 *
 * @title SendMoments
 *
 * @description
 *
 */
public class SendMoments extends JDialog {
	NetClient client = NetClient.getNetClient();
	DataInputStream in = client.getIn();
	DataOutputStream out = client.getOut();
	JTextArea textArea;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			JFrame frame=new JFrame();
			SendMoments dialog = new SendMoments(frame);
			frame.setVisible(true);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public SendMoments(JFrame frame) {
		super(frame);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		{
			JLayeredPane layeredPane = new JLayeredPane();
			layeredPane.setBorder(new EmptyBorder(20, 0, 0, 0));
			getContentPane().add(layeredPane, BorderLayout.CENTER);
			layeredPane.setLayout(new BorderLayout(0, 0));
			{
				JPanel buttonPane = new JPanel();
				buttonPane.setBorder(new EmptyBorder(0, 30, 10, 30));
				layeredPane.add(buttonPane, BorderLayout.SOUTH);
				buttonPane.setLayout(new GridLayout(0, 2, 20, 0));
				{
					JButton okButton = new JButton("OK");
					okButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							sendMoments2Server();
						}
					});
					okButton.setActionCommand("OK");
					buttonPane.add(okButton);
				}
				{
					JButton cancelButton = new JButton("Cancel");
					cancelButton.addActionListener(new ActionListener() {
						public void actionPerformed(ActionEvent e) {
							dispose();
						}
					});
					cancelButton.setActionCommand("Cancel");
					buttonPane.add(cancelButton);
				}
			}
			{
				JPanel panel = new JPanel();
				layeredPane.add(panel, BorderLayout.CENTER);
				panel.setLayout(new BorderLayout(0, 0));
				{
					JLabel lblNewLabel = new JLabel("Every detail recorded the happiness of life.");
					lblNewLabel.setFont(new Font("Calibri", Font.BOLD | Font.ITALIC, 16));
					lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
					panel.add(lblNewLabel, BorderLayout.NORTH);
				}
				{
					JPanel panel_1 = new JPanel();
					panel_1.setBorder(new EmptyBorder(20, 20, 20, 20));
					panel.add(panel_1, BorderLayout.CENTER);
					panel_1.setLayout(new BorderLayout(0, 0));
					{
						JScrollPane scrollPane = new JScrollPane();
						panel_1.add(scrollPane, BorderLayout.CENTER);
						{
							textArea = new JTextArea();
							scrollPane.setViewportView(textArea);
						}
					}
				}
				{
					JPanel panel_1 = new JPanel();
					panel.add(panel_1, BorderLayout.SOUTH);
				}
			}
		}
		setVisible(true);

	}

	/**
	 * @description
	 *
	 */
	private void sendMoments2Server() {
		String text = textArea.getText();
		if (text.length() > 150) {
			JOptionPane.showMessageDialog(this, "Too many words", "Please try again with less text.",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		try {
			PlayerInfo info=PlayerInfo.getPlayerInfo();
			out.writeBoolean(true);
			out.writeUTF("inmoments");
			out.writeUTF(info.getId());
			out.writeInt(info.getPic());
			out.writeUTF(text);
			out.flush();
			if(in.readBoolean()) {
			JOptionPane.showMessageDialog(this, "Send Successfully", "Send Successfully.",
					JOptionPane.INFORMATION_MESSAGE);
			dispose();
			return;
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
