/**
 * @title Register.java
 *
 * @version 1.0
 *
 * @date 2020-11-26 03:21:10 
 * 
 * @author Yi Gu,SSE of BJTU
 * 
 */

package client.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import client.net.NetClient;

import java.awt.GridLayout;
import java.awt.Image;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JRadioButton;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import java.awt.event.ActionListener;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.awt.event.ActionEvent;

/**
 * @author Yi Gu,SSE of BJTU
 *
 * @title Register
 *
 * @description
 *
 */
public class Register extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	NetClient client = NetClient.getNetClient();
	DataOutputStream out = client.getOut();
	DataInputStream in = client.getIn();
	private JTextField idField;
	private JTextField mottoField;
	private JPasswordField pinField;
	private JPasswordField confirmField;
	private JRadioButton RB_g1;
	private JRadioButton RB_i1;
	private JRadioButton RB_i2;
	private JRadioButton RB_i3;
	private JRadioButton RB_i4;

	/**
	 * Launch the application.
	 */
	/*
	public static void main(String[] args) {
		try {
			Register dialog = new Register();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	*/

	/**
	 * Create the dialog.
	 */
	public Register() {
		setBounds(550, 270, 435, 401);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));

		JLayeredPane layeredPane = new JLayeredPane();
		contentPanel.add(layeredPane, BorderLayout.CENTER);
		{
			JLabel lblNewLabel_bg = new JLabel("");
			lblNewLabel_bg.setBounds(0, 0, 411, 354);
			ImageIcon icon = new ImageIcon("./gif/greenbg.gif");
			icon.setImage(icon.getImage().getScaledInstance(lblNewLabel_bg.getWidth(), lblNewLabel_bg.getHeight(),
					Image.SCALE_DEFAULT));
			lblNewLabel_bg.setIcon(icon);
			layeredPane.add(lblNewLabel_bg);
		}
		{
			JLabel lblNewLabel = new JLabel("Register");
			layeredPane.setLayer(lblNewLabel, 1);
			lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel.setFont(new Font("Segoe UI Black", Font.BOLD, 17));
			lblNewLabel.setBounds(0, 0, 411, 24);
			layeredPane.add(lblNewLabel);
		}
		{
			JPanel iconPanel = new JPanel();
			iconPanel.setOpaque(false);
			layeredPane.setLayer(iconPanel, 1);
			iconPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
			iconPanel.setBounds(0, 180, 411, 131);
			layeredPane.add(iconPanel);
			GridBagLayout gbl_iconPanel = new GridBagLayout();
			gbl_iconPanel.columnWidths = new int[] { 0, 0, 0, 0, 0, 0 };
			gbl_iconPanel.rowHeights = new int[] { 0, 0, 0, 0 };
			gbl_iconPanel.columnWeights = new double[] { 0.0, 1.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
			gbl_iconPanel.rowWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
			iconPanel.setLayout(gbl_iconPanel);
			{
				JLabel lblNewLabel_5 = new JLabel("Icon");
				lblNewLabel_5.setFont(new Font("Calibri", Font.BOLD, 16));
				lblNewLabel_5.setHorizontalAlignment(SwingConstants.CENTER);
				GridBagConstraints gbc_lblNewLabel_5 = new GridBagConstraints();
				gbc_lblNewLabel_5.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_5.gridx = 0;
				gbc_lblNewLabel_5.gridy = 0;
				iconPanel.add(lblNewLabel_5, gbc_lblNewLabel_5);
			}
			{
				JLabel lblNewLabel_1 = new JLabel("");
				ImageIcon icon = new ImageIcon("./portrait/0.png");
				icon.setImage(icon.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
				lblNewLabel_1.setIcon(icon);
				GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
				gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_1.gridx = 1;
				gbc_lblNewLabel_1.gridy = 0;
				iconPanel.add(lblNewLabel_1, gbc_lblNewLabel_1);
			}
			{
				JLabel lblNewLabel_2 = new JLabel("");
				ImageIcon icon = new ImageIcon("./portrait/1.png");
				icon.setImage(icon.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
				lblNewLabel_2.setIcon(icon);
				GridBagConstraints gbc_lblNewLabel_2 = new GridBagConstraints();
				gbc_lblNewLabel_2.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_2.gridx = 2;
				gbc_lblNewLabel_2.gridy = 0;
				iconPanel.add(lblNewLabel_2, gbc_lblNewLabel_2);
			}
			{
				JLabel lblNewLabel_3 = new JLabel("");
				ImageIcon icon = new ImageIcon("./portrait/2.png");
				icon.setImage(icon.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
				lblNewLabel_3.setIcon(icon);
				GridBagConstraints gbc_lblNewLabel_3 = new GridBagConstraints();
				gbc_lblNewLabel_3.insets = new Insets(0, 0, 5, 5);
				gbc_lblNewLabel_3.gridx = 3;
				gbc_lblNewLabel_3.gridy = 0;
				iconPanel.add(lblNewLabel_3, gbc_lblNewLabel_3);
			}
			{
				JLabel lblNewLabel_4 = new JLabel("");
				ImageIcon icon = new ImageIcon("./portrait/3.png");
				icon.setImage(icon.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
				lblNewLabel_4.setIcon(icon);
				GridBagConstraints gbc_lblNewLabel_4 = new GridBagConstraints();
				gbc_lblNewLabel_4.insets = new Insets(0, 0, 5, 0);
				gbc_lblNewLabel_4.gridx = 4;
				gbc_lblNewLabel_4.gridy = 0;
				iconPanel.add(lblNewLabel_4, gbc_lblNewLabel_4);
			}
			{
				RB_i1 = new JRadioButton("");
				RB_i1.setOpaque(false);
				buttonGroup.add(RB_i1);
				RB_i1.setSelected(true);
				RB_i1.setHorizontalAlignment(SwingConstants.CENTER);
				GridBagConstraints gbc_RB_i1 = new GridBagConstraints();
				gbc_RB_i1.insets = new Insets(0, 0, 5, 5);
				gbc_RB_i1.gridx = 1;
				gbc_RB_i1.gridy = 1;
				iconPanel.add(RB_i1, gbc_RB_i1);
			}
			{
				RB_i2 = new JRadioButton("");
				RB_i2.setOpaque(false);
				buttonGroup.add(RB_i2);
				RB_i2.setHorizontalAlignment(SwingConstants.CENTER);
				GridBagConstraints gbc_RB_i2 = new GridBagConstraints();
				gbc_RB_i2.insets = new Insets(0, 0, 5, 5);
				gbc_RB_i2.gridx = 2;
				gbc_RB_i2.gridy = 1;
				iconPanel.add(RB_i2, gbc_RB_i2);
			}
			{
				RB_i3 = new JRadioButton("");
				RB_i3.setOpaque(false);
				buttonGroup.add(RB_i3);
				RB_i3.setHorizontalAlignment(SwingConstants.CENTER);
				GridBagConstraints gbc_RB_i3 = new GridBagConstraints();
				gbc_RB_i3.insets = new Insets(0, 0, 5, 5);
				gbc_RB_i3.gridx = 3;
				gbc_RB_i3.gridy = 1;
				iconPanel.add(RB_i3, gbc_RB_i3);
			}
			{
				RB_i4 = new JRadioButton("");
				RB_i4.setOpaque(false);
				buttonGroup.add(RB_i4);
				RB_i4.setHorizontalAlignment(SwingConstants.CENTER);
				GridBagConstraints gbc_RB_i4 = new GridBagConstraints();
				gbc_RB_i4.insets = new Insets(0, 0, 5, 0);
				gbc_RB_i4.gridx = 4;
				gbc_RB_i4.gridy = 1;
				iconPanel.add(RB_i4, gbc_RB_i4);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setOpaque(false);
			layeredPane.setLayer(buttonPane, 1);
			buttonPane.setBorder(new EmptyBorder(0, 50, 10, 50));
			buttonPane.setBounds(0, 321, 411, 33);
			layeredPane.add(buttonPane);
			buttonPane.setLayout(new GridLayout(0, 2, 10, 0));
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						register();
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

		JPanel infoPanel = new JPanel();
		infoPanel.setOpaque(false);
		infoPanel.setBorder(new EmptyBorder(0, 10, 0, 10));
		layeredPane.setLayer(infoPanel, 1);
		infoPanel.setBounds(0, 34, 411, 136);
		layeredPane.add(infoPanel);
		GridBagLayout gbl_infoPanel = new GridBagLayout();
		gbl_infoPanel.columnWidths = new int[] { 0, 0, 0, 0 };
		gbl_infoPanel.rowHeights = new int[] { 0, 0, 0, 0, 0, 0 };
		gbl_infoPanel.columnWeights = new double[] { 0.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_infoPanel.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		infoPanel.setLayout(gbl_infoPanel);

		JLabel lblNewLabel_6 = new JLabel("ID");
		lblNewLabel_6.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_6.setFont(new Font("Calibri", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel_6 = new GridBagConstraints();
		gbc_lblNewLabel_6.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_6.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_6.gridx = 0;
		gbc_lblNewLabel_6.gridy = 0;
		infoPanel.add(lblNewLabel_6, gbc_lblNewLabel_6);
		{
			idField = new JTextField();
			idField.setOpaque(false);
			GridBagConstraints gbc_idField = new GridBagConstraints();
			gbc_idField.gridwidth = 2;
			gbc_idField.insets = new Insets(0, 0, 5, 5);
			gbc_idField.fill = GridBagConstraints.HORIZONTAL;
			gbc_idField.gridx = 1;
			gbc_idField.gridy = 0;
			infoPanel.add(idField, gbc_idField);
			idField.setColumns(10);
		}

		JLabel lblNewLabel_7 = new JLabel("PIN");
		lblNewLabel_7.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_7.setFont(new Font("Calibri", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel_7 = new GridBagConstraints();
		gbc_lblNewLabel_7.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_7.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_7.gridx = 0;
		gbc_lblNewLabel_7.gridy = 1;
		infoPanel.add(lblNewLabel_7, gbc_lblNewLabel_7);
		{
			pinField = new JPasswordField();
			pinField.setOpaque(false);
			GridBagConstraints gbc_pinField = new GridBagConstraints();
			gbc_pinField.gridwidth = 2;
			gbc_pinField.insets = new Insets(0, 0, 5, 5);
			gbc_pinField.fill = GridBagConstraints.HORIZONTAL;
			gbc_pinField.gridx = 1;
			gbc_pinField.gridy = 1;
			infoPanel.add(pinField, gbc_pinField);
		}
		{
			JLabel lblNewLabel_10 = new JLabel("Confirm");
			lblNewLabel_10.setHorizontalAlignment(SwingConstants.CENTER);
			lblNewLabel_10.setFont(new Font("Calibri", Font.BOLD, 16));
			GridBagConstraints gbc_lblNewLabel_10 = new GridBagConstraints();
			gbc_lblNewLabel_10.anchor = GridBagConstraints.EAST;
			gbc_lblNewLabel_10.insets = new Insets(0, 0, 5, 5);
			gbc_lblNewLabel_10.gridx = 0;
			gbc_lblNewLabel_10.gridy = 2;
			infoPanel.add(lblNewLabel_10, gbc_lblNewLabel_10);
		}
		{
			confirmField = new JPasswordField();
			confirmField.setOpaque(false);
			GridBagConstraints gbc_confirmField = new GridBagConstraints();
			gbc_confirmField.gridwidth = 2;
			gbc_confirmField.insets = new Insets(0, 0, 5, 5);
			gbc_confirmField.fill = GridBagConstraints.HORIZONTAL;
			gbc_confirmField.gridx = 1;
			gbc_confirmField.gridy = 2;
			infoPanel.add(confirmField, gbc_confirmField);
		}

		JLabel lblNewLabel_8 = new JLabel("Gender");
		lblNewLabel_8.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_8.setFont(new Font("Calibri", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel_8 = new GridBagConstraints();
		gbc_lblNewLabel_8.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_8.gridx = 0;
		gbc_lblNewLabel_8.gridy = 3;
		infoPanel.add(lblNewLabel_8, gbc_lblNewLabel_8);
		{
			RB_g1 = new JRadioButton("Female");
			RB_g1.setOpaque(false);
			buttonGroup_1.add(RB_g1);
			RB_g1.setSelected(true);
			RB_g1.setFont(new Font("Calibri", Font.BOLD, 16));
			RB_g1.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_RB_g1 = new GridBagConstraints();
			gbc_RB_g1.insets = new Insets(0, 0, 5, 5);
			gbc_RB_g1.gridx = 1;
			gbc_RB_g1.gridy = 3;
			infoPanel.add(RB_g1, gbc_RB_g1);
		}
		{
			JRadioButton RB_g2 = new JRadioButton("Male");
			RB_g2.setOpaque(false);
			buttonGroup_1.add(RB_g2);
			RB_g2.setFont(new Font("Calibri", Font.BOLD, 16));
			RB_g2.setHorizontalAlignment(SwingConstants.CENTER);
			GridBagConstraints gbc_RB_g2 = new GridBagConstraints();
			gbc_RB_g2.insets = new Insets(0, 0, 5, 0);
			gbc_RB_g2.gridx = 2;
			gbc_RB_g2.gridy = 3;
			infoPanel.add(RB_g2, gbc_RB_g2);
		}

		JLabel lblNewLabel_9 = new JLabel("Motto");
		lblNewLabel_9.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_9.setFont(new Font("Calibri", Font.BOLD, 16));
		GridBagConstraints gbc_lblNewLabel_9 = new GridBagConstraints();
		gbc_lblNewLabel_9.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_9.insets = new Insets(0, 0, 0, 5);
		gbc_lblNewLabel_9.gridx = 0;
		gbc_lblNewLabel_9.gridy = 4;
		infoPanel.add(lblNewLabel_9, gbc_lblNewLabel_9);
		{
			mottoField = new JTextField();
			mottoField.setOpaque(false);
			GridBagConstraints gbc_mottoField = new GridBagConstraints();
			gbc_mottoField.gridwidth = 2;
			gbc_mottoField.insets = new Insets(0, 0, 0, 5);
			gbc_mottoField.fill = GridBagConstraints.HORIZONTAL;
			gbc_mottoField.gridx = 1;
			gbc_mottoField.gridy = 4;
			infoPanel.add(mottoField, gbc_mottoField);
			mottoField.setColumns(10);
		}
		{
			{
				ImageIcon icon = new ImageIcon("./portrait/0.png");
				icon.setImage(icon.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
			}
			{
				ImageIcon icon = new ImageIcon("./portrait/1.png");
				icon.setImage(icon.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
			}
			{
				ImageIcon icon = new ImageIcon("./portrait/2.png");
				icon.setImage(icon.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
			}
			{
				ImageIcon icon = new ImageIcon("./portrait/3.png");
				icon.setImage(icon.getImage().getScaledInstance(80, 80, Image.SCALE_DEFAULT));
			}
		}
	}

	/**
	 * @description send information to the server database
	 *
	 */
	private void register() {
		try {
			String pin = new String(pinField.getPassword());
			String confirm = new String(confirmField.getPassword());
			if (!pin.equals(confirm)) {
				JOptionPane.showMessageDialog(null, "Please check the setting PIN and confirmation PIN.",
						"Two entered PINs do not match.", JOptionPane.INFORMATION_MESSAGE);
				return;
			}
			out.writeBoolean(true);
			out.writeUTF("register");
			// content
			// id
			out.writeUTF(idField.getText());
			// pin
			out.writeUTF(new String(pinField.getPassword()));
			// gender
			if (RB_g1.isSelected()) {
				out.writeChar(0);
			} else {
				out.writeChar(1);
			}
			// motto
			String motto = mottoField.getText();
			if (motto == null) {
				out.writeUTF("none");
			} else if (motto.trim().equals("")) {
				out.writeUTF("none");
			} else {
				out.writeUTF(motto);
			}
			// pic selection
			if (RB_i1.isSelected()) {
				out.writeChar(0);
			} else if (RB_i2.isSelected()) {
				out.writeChar(1);
			} else if (RB_i3.isSelected()) {
				out.writeChar(2);
			} else if (RB_i4.isSelected()) {
				out.writeChar(3);
			}
			out.flush();
			if (!in.readBoolean()) {
				JOptionPane.showMessageDialog(null, "Your ID exists. Please change and try again. ", "ID exists.",
						JOptionPane.ERROR_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Register successfully.", "Register successfully.",
						JOptionPane.INFORMATION_MESSAGE);
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
