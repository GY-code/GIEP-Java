/**
 * @title ContextPane.java
 *
 * @version 1.0
 *
 * @date 2020-11-27 12:04:51 
 * 
 * @author Yi Gu,SSE of BJTU
 * 
 */  
 
package client.context;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Yi Gu,SSE of BJTU
 *
 * @title ContextPane
 *
 * @description
 *
 */
public class ContextPane extends JPanel {

	/**
	 * Create the panel.
	 */
	public ContextPane() {
		super();
		setOpaque(false);
	}
	public void addCard(String id, int pic, String text, String time) {
		JLabel l=new JLabel();
		l.setOpaque(false);
		l.add(new MomentsCard(id, pic, text, time));
		add(l);
	}

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame=new JFrame();
					frame.setBounds(0,0,1176,641);
					ContextPane pane=new ContextPane();
					pane.setPreferredSize(new Dimension(1176,641));
					pane.addCard("1", 1, "hahaha", "2020.11.27");
					pane.addCard("1", 1, "xixixi", "2020.11.27");
					pane.addCard("1", 1, "muamua", "2020.11.27");
					frame.getContentPane().add(pane);
					frame.setVisible(true);
					frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

					pane.repaint();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
