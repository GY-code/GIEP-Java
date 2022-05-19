/**
 * @title ClientGUI.java
 *
 * @version 1.0
 *
 * @date 2020-11-24 05:58:23 
 * 
 * @author Yi Gu,SSE of BJTU
 * 
 */

package client.ui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.ScrollPaneConstants;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.border.EmptyBorder;

import client.context.ContextScroll;
import client.net.NetClient;
import client.utils.PlayerInfo;
import client.utils.SendMoments;
import game.launch.PlaneWarClient;
import server.DataBaseIO;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Yi Gu,SSE of BJTU
 *
 * @title ClientGUI
 *
 * @description
 *
 */
public class PlatformGUI {

	private JFrame GIEP;
	private JTextField txtTestCharacters;
	private JTextField textField;
	private JLayeredPane pmlayeredPane;
	private JLayeredPane pslayeredPane;
	private NetClient client = NetClient.getNetClient();
	private DataInputStream in = client.getIn();
	private DataOutputStream out = client.getOut();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		PlayerInfo info = PlayerInfo.getPlayerInfo();
		info.setId("test id");
		info.setMotto("test motto");
		info.setPic(1);
		info.setGender(1);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PlatformGUI window = new PlatformGUI();
					window.GIEP.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void launchPlatform() {
		PlatformGUI window = new PlatformGUI();
		window.GIEP.setVisible(true);
	}

	/**
	 * Create the application.
	 */
	public PlatformGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		GIEP = new JFrame();
		GIEP.setMaximumSize(new Dimension(1500, 800));
		GIEP.setTitle("Game Information and Entertainment Platform");
		GIEP.setBounds(20, 5, 1500, 800);
		GIEP.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		PlayerInfo info = PlayerInfo.getPlayerInfo();

		JLayeredPane layeredPane = new JLayeredPane();
		GIEP.getContentPane().add(layeredPane, BorderLayout.CENTER);

		ImageIcon icon = new ImageIcon("./gif/bluebg.gif");
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(0, 0, 1486, 735);
		icon.setImage(icon.getImage().getScaledInstance(lblNewLabel.getWidth(), lblNewLabel.getHeight(),
				Image.SCALE_DEFAULT));
		lblNewLabel.setIcon(icon);

		JToolBar toolBar = new JToolBar();
		toolBar.setBounds(0, 21, 1486, 35);
		layeredPane.add(toolBar);

		JButton btnNewButton_1 = new JButton("Say something...");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JDialog dialog = new SendMoments(GIEP);
			}
		});
		toolBar.add(btnNewButton_1);

		JButton btnNewButton_2 = new JButton("Refresh Moments");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				gainMoments();
			}
		});

		JButton btnNewButton_3 = new JButton("Refresh Scores");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				gainScores();
			}
		});
		toolBar.add(btnNewButton_3);
		toolBar.add(btnNewButton_2);
		layeredPane.add(lblNewLabel);

		JPanel panel = new JPanel();
		layeredPane.setLayer(panel, 1);
		panel.setOpaque(false);
		panel.setBounds(0, 53, 1486, 682);
		layeredPane.add(panel);
		panel.setLayout(null);

		// 使标签页透明
		UIManager.put("TabbedPane.contentOpaque", false);
		UIManager.put("TabbedPane.tabsOpaque", false);
		JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setBounds(0, 41, 1176, 641);
		panel.add(tabbedPane);

		JPanel pScores = new JPanel();
		pScores.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				gainScores();
			}
		});
		pScores.setOpaque(false);
		tabbedPane.addTab("Scores", null, pScores, null);
		pScores.setLayout(new BorderLayout(0, 0));

		pslayeredPane = new JLayeredPane();
		pScores.add(pslayeredPane, BorderLayout.CENTER);
		pslayeredPane.setLayout(new BorderLayout(0, 0));

		JPanel pMoments = new JPanel();
		pMoments.setOpaque(false);
		pMoments.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentShown(ComponentEvent e) {
				gainMoments();
			}
		});
		tabbedPane.addTab("Moments", null, pMoments, null);
		pMoments.setLayout(new BorderLayout(0, 0));

		pmlayeredPane = new JLayeredPane();
		pMoments.add(pmlayeredPane, BorderLayout.CENTER);
		pmlayeredPane.setLayout(new BorderLayout(0, 0));

		JPanel pMe = new JPanel();
		pMe.setOpaque(false);
		tabbedPane.addTab("Me", null, pMe, null);
		pMe.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("");
		String picPath = "./portrait/" + info.getPic() + ".png";
		lblNewLabel_4.setBounds(44, 54, 100, 100);
		ImageIcon pic = new ImageIcon(picPath);
		pic.setImage(pic.getImage().getScaledInstance(lblNewLabel_4.getWidth(), lblNewLabel_4.getHeight(),
				Image.SCALE_DEFAULT));
		lblNewLabel_4.setIcon(pic);

		pMe.add(lblNewLabel_4);

		JLabel lblNewLabel_5 = new JLabel(info.getId());
		lblNewLabel_5.setFont(new Font("Segoe UI", Font.BOLD, 13));
		lblNewLabel_5.setBounds(253, 54, 108, 34);
		pMe.add(lblNewLabel_5);

		textField = new JTextField();
		textField.setOpaque(false);
		textField.setEnabled(false);
		textField.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 13));
		if (info.getMotto().equals("none")) {
			textField.setText("<motto>");
		} else {
			textField.setText(info.getMotto());
		}
		textField.setBounds(253, 121, 366, 34);
		pMe.add(textField);
		textField.setColumns(10);

		JLabel lScore = new JLabel("Scores", SwingConstants.CENTER);
		lScore.setPreferredSize(new Dimension(200, 30));
		JLabel lMoments = new JLabel("Moments", SwingConstants.CENTER);
		lMoments.setPreferredSize(new Dimension(200, 30));
		JLabel lGames = new JLabel("Games", SwingConstants.CENTER);
		lGames.setPreferredSize(new Dimension(200, 30));
		JLabel lMe = new JLabel("Me", SwingConstants.CENTER);
		lMe.setPreferredSize(new Dimension(200, 30));
		tabbedPane.setTabComponentAt(0, lScore);
		tabbedPane.setTabComponentAt(1, lMoments);
		tabbedPane.setTabComponentAt(2, lMe);

		JLabel lblNewLabel_1 = new JLabel("Game Information and Entertainment Platform");
		layeredPane.setLayer(lblNewLabel_1, 1);
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD, 23));
		lblNewLabel_1.setBounds(0, 0, 1486, 34);
		panel.add(lblNewLabel_1);

		JPanel GamelauchPane = new JPanel();
		GamelauchPane.setOpaque(false);
		GamelauchPane.setBounds(1174, 69, 312, 613);
		panel.add(GamelauchPane);
		GamelauchPane.setLayout(null);

		JLabel lblNewLabel_3 = new JLabel("Game Launcher");
		lblNewLabel_3.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(0, 0, 312, 41);
		GamelauchPane.add(lblNewLabel_3);

		JButton btnNewButton = new JButton("Fight Plane");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				launchFightPlane();
			}
		});
		btnNewButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		btnNewButton.setFont(new Font("Segoe UI Black", Font.PLAIN, 15));
		btnNewButton.setBounds(104, 51, 198, 73);
		GamelauchPane.add(btnNewButton);

		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(10, 51, 73, 72);
		ImageIcon icon2 = new ImageIcon("./logo/fight_plane.jpg");
		lblNewLabel_2.setIcon(icon2);
		icon2.setImage(icon2.getImage().getScaledInstance(lblNewLabel_2.getWidth(), lblNewLabel_2.getHeight(),
				Image.SCALE_DEFAULT));

		GamelauchPane.add(lblNewLabel_2);

		JToolBar statusBar = new JToolBar();
		layeredPane.setLayer(statusBar, 1);
		statusBar.setBounds(0, 738, 1486, 25);
		layeredPane.add(statusBar);

		txtTestCharacters = new JTextField();
		txtTestCharacters.setText("Test characters---");
		txtTestCharacters.setEditable(false);
		statusBar.add(txtTestCharacters);
		txtTestCharacters.setColumns(10);

		JMenuBar menuBar = new JMenuBar();
		layeredPane.setLayer(menuBar, 1);
		menuBar.setBounds(0, 0, 1486, 23);
		layeredPane.add(menuBar);

		JMenu mnNewMenu = new JMenu("Add...");
		menuBar.add(mnNewMenu);

		JMenuItem mntmNewMenuItem = new JMenuItem("New Friends");
		mnNewMenu.add(mntmNewMenuItem);

		JMenu mnNewMenu_1 = new JMenu("Settings");
		menuBar.add(mnNewMenu_1);

		JMenu mnNewMenu_2 = new JMenu("About");
		menuBar.add(mnNewMenu_2);

	}

	/**
	 * @description launch the fight plane game
	 *
	 */
	protected void launchFightPlane() {
		PlaneWarClient c = new PlaneWarClient();
		c.launchFrame();
		GIEP.dispose();
	}

	/**
	 * @description gain the moments from database
	 *
	 */
	private void gainMoments() {
		try {
			out.writeBoolean(true);
			out.writeUTF("moments");
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			int turn = in.readInt();
			pmlayeredPane.removeAll();
			ContextScroll scroll = new ContextScroll(turn);
			for (int i = 0; i < turn; i++) {
				String id = in.readUTF();
				int pic = in.readInt();
				String text = in.readUTF();
				String time = in.readUTF();
				scroll.addCard(id, pic, text, time);
			}
			pmlayeredPane.setLayer(scroll, 0);
			pmlayeredPane.add(scroll);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	/**
	 * @description gain the moments from database
	 *
	 */
	private void gainScores() {
		try {
			out.writeBoolean(true);
			out.writeUTF("scores");
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			int turn = in.readInt();
			pslayeredPane.removeAll();
			ContextScroll scroll = new ContextScroll(turn);
			for (int i = 0; i < turn; i++) {
				String id = in.readUTF();
				int pic = in.readInt();
				String scores = in.readUTF();
				String time = in.readUTF();
				scroll.addCard(id, pic, scores, time);
			}
			pslayeredPane.setLayer(scroll, 0);
			pslayeredPane.add(scroll);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
