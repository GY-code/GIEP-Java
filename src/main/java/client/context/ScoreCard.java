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

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * @author Yi Gu,SSE of BJTU
 *
 * @title MomentsCard
 *
 * @description
 *
 */
public class ScoreCard extends JPanel {

	/**
	 * Create the panel.
	 * @param time 
	 * @param text 
	 * @param pic 
	 * @param id 
	 */
	
	public ScoreCard(String id, int pic, String score, String time) {
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
		
		JLabel textLabel = new JLabel(score);
		textLabel.setBounds(149, 20, 978, 30);
		add(textLabel);
		
		JLabel timeLabel = new JLabel(time);
		timeLabel.setBounds(292, 0, 266, 20);
		add(timeLabel);
		
		setPreferredSize(new Dimension(1176, 60));


		
	}
}
