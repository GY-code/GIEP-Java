/**
 * @title MomentsCard.java
 *
 * @version 1.0
 *
 * @date 2020-11-27 12:39:13 
 * 
 * @author Yi Gu,SSE of BJTU
 * 
 */  
 
package client.context;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;

/**
 * @author Yi Gu,SSE of BJTU
 *
 * @title MomentsCard
 *
 * @description
 *
 */
public class MomentsCard extends JPanel {

	/**
	 * Create the panel.
	 * @param time 
	 * @param text 
	 * @param pic 
	 * @param id 
	 */
	
	public MomentsCard(String id, int pic, String text, String time) {
		super();
		setSize(new Dimension(1176, 61));
		setOpaque(false);
		setMinimumSize(new Dimension(1176, 61));
		
		setLayout(null);
		
		JLabel picLabel = new JLabel("");
		picLabel.setBounds(53, 0, 64, 61);
		String picPath="./portrait/"+pic+".png";
		ImageIcon icon = new ImageIcon(picPath);
		icon.setImage(icon.getImage().getScaledInstance(picLabel.getWidth(),picLabel.getHeight(), Image.SCALE_DEFAULT));
		picLabel.setIcon(icon);
		add(picLabel);
		
		JLabel idLabel = new JLabel(id);
		idLabel.setToolTipText("");
		idLabel.setBounds(149, 0, 74, 20);
		add(idLabel);
		
		JLabel textLabel = new JLabel(text);
		textLabel.setBounds(149, 20, 978, 30);
		add(textLabel);
		
		JLabel timeLabel = new JLabel(time);
		timeLabel.setBounds(292, 0, 266, 20);
		add(timeLabel);
		
		setPreferredSize(new Dimension(1176, 60));


		
	}
}
