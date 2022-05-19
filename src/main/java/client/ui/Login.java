/**
 * @title log_in.java
 *
 * @version 1.0
 *
 * @date 2020-11-25 05:26:36 
 * 
 * @author Yi Gu,SSE of BJTU
 * 
 */  
 
package client.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import client.net.NetClient;
import client.utils.PlayerInfo;

/**
 * @author Yi Gu,SSE of BJTU
 *
 * @title log_in
 *
 * @description
 *
 */
public class Login {

	private boolean connected=false;
	NetClient client=NetClient.getNetClient();
	private DataInputStream in=client.getIn();
	private DataOutputStream out=client.getOut();
	private JFrame frame;
	private JTextField id;
	private JPasswordField pin;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login window = new Login();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Login() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {
				if(connected) {
					try {
						out.writeBoolean(false);
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					client.close();
				}
			}
		});
		frame.setBackground(Color.WHITE);
		frame.getContentPane().setBackground(Color.WHITE);
		frame.setBounds(550, 270, 435, 325);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		
		JLayeredPane layeredPane = new JLayeredPane();
		layeredPane.setBackground(Color.WHITE);
		frame.getContentPane().add(layeredPane, BorderLayout.CENTER);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBackground(Color.WHITE);
		lblNewLabel.setBounds(0, 0, 421, 288);
		ImageIcon icon=new ImageIcon("./gif/greenbg.gif");
		icon.setImage(icon.getImage().getScaledInstance(lblNewLabel.getWidth(),lblNewLabel.getHeight(),Image.SCALE_DEFAULT));
		lblNewLabel.setIcon(icon);
		layeredPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Game Information and Entertainment Platform");
		lblNewLabel_1.setFont(new Font("Arial", Font.BOLD, 15));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		layeredPane.setLayer(lblNewLabel_1, 1);
		lblNewLabel_1.setBounds(0, 28, 421, 30);
		layeredPane.add(lblNewLabel_1);
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		layeredPane.setLayer(panel, 1);
		panel.setBorder(new EmptyBorder(0, 50, 0, 50));
		panel.setBounds(0, 84, 421, 96);
		layeredPane.add(panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{0, 0, 0};
		gbl_panel.rowHeights = new int[]{0, 0, 0};
		gbl_panel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel_2 = new JLabel("ID");
		lblNewLabel_2.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
		gbc_lblNewLabel_2.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_2.gridx = 0;
		gbc_lblNewLabel_2.gridy = 0;
		panel.add(lblNewLabel_2, gbc_lblNewLabel_2);
		
		id = new JTextField();
		id.setOpaque(false);
		GridBagConstraints gbc_id = new GridBagConstraints();
		gbc_id.insets = new Insets(0, 0, 5, 0);
		gbc_id.fill = GridBagConstraints.HORIZONTAL;
		gbc_id.gridx = 1;
		gbc_id.gridy = 0;
		panel.add(id, gbc_id);
		id.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("PIN");
		lblNewLabel_3.setFont(new Font("Calibri", Font.BOLD, 15));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
		gbc_lblNewLabel_3.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_3.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_3.gridx = 0;
		gbc_lblNewLabel_3.gridy = 1;
		panel.add(lblNewLabel_3, gbc_lblNewLabel_3);
		
		pin = new JPasswordField();
		pin.setOpaque(false);
		GridBagConstraints gbc_pin = new GridBagConstraints();
		gbc_pin.fill = GridBagConstraints.HORIZONTAL;
		gbc_pin.gridx = 1;
		gbc_pin.gridy = 1;
		panel.add(pin, gbc_pin);
		
		JButton btnNewButton = new JButton("Log in");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
					String id_value=id.getText();
					String pin_value=new String(pin.getPassword());
					if(id_value.length()==0||pin_value.length()==0) {
						JOptionPane.showMessageDialog(frame, "ID and PIN can't be empty.", "Format Wrong",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
					login(id_value,pin_value);
			}
		});
		layeredPane.setLayer(btnNewButton, 1);
		btnNewButton.setFont(new Font("Segoe UI", Font.BOLD, 16));
		btnNewButton.setBounds(130, 190, 155, 38);
		layeredPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Register...");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					Register dialog = new Register();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception ex) {
					ex.printStackTrace();
				}

			}
		});
		layeredPane.setLayer(btnNewButton_1, 1);
		btnNewButton_1.setFont(new Font("Segoe UI", Font.BOLD, 13));
		btnNewButton_1.setBounds(10, 255, 97, 23);
		layeredPane.add(btnNewButton_1);
	}

	/**
	 * @description execute log in operation
	 *
	 */
	private void login(String id,String pin) {
		try {
			out.writeBoolean(true);
			out.writeUTF("login");
			out.writeUTF(id);
			out.writeUTF(pin);
			out.flush();
			if(in.readBoolean()) {
				PlayerInfo info= PlayerInfo.getPlayerInfo();
				info.setId(id);
				info.setGender(in.readInt());
				info.setMotto(in.readUTF());
				info.setFriends_name(in.readUTF());
				info.setPic(in.readInt());
				System.out.println(info);
				PlatformGUI.launchPlatform();
				frame.dispose();
			}else {
				if(in.readChar()==1) {
					System.out.println("Already logged in.");
					JOptionPane.showMessageDialog(frame, "You have already logged in.", "Log-in rejected",
							JOptionPane.WARNING_MESSAGE);
				}else {
					System.out.println("Log in failed.");
					JOptionPane.showMessageDialog(frame, "Wrong ID or password, please try again", "Log-in rejected",
							JOptionPane.ERROR_MESSAGE);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
