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

import javax.swing.*;
import java.awt.*;

public class ContextScroll extends JPanel {

    ContextPane pane;
    public ContextScroll(int CardsNum){
        pane=new ContextPane();
        pane.setPreferredSize(new Dimension(1150,CardsNum*66+50));
        setOpaque(false);
        setPreferredSize(new Dimension(1150, 641));
        JScrollPane scrollPane = new JScrollPane(pane);
        add(scrollPane, BorderLayout.CENTER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setOpaque(false);
		scrollPane.getViewport().setOpaque(false);
        scrollPane.setPreferredSize(new Dimension(1176, 641));
        JScrollBar v=scrollPane.getVerticalScrollBar();
        v.setUnitIncrement(5);
        scrollPane.setVisible(true);
        //scrollPane.repaint();
    }

    public void addCard(String id, int pic, String text, String time) {
        pane.add(new JLabel().add(new MomentsCard(id, pic, text, time)));
        //pane.repaint();
    }


    public static void main(String[] args) {
        JFrame frame=new JFrame();
        frame.setPreferredSize(new Dimension(1200,641));
        frame.setBounds(0,0,1200,641);
        ContextScroll scroll=new ContextScroll(2000);
        scroll.addCard("1", 1, "hahaha", "2020.11.27");
        scroll.addCard("1", 1, "hahaha", "2020.11.27");
        scroll.addCard("1", 1, "hahaha", "2020.11.27");
        scroll.addCard("1", 1, "hahaha", "2020.11.27");
        frame.add(scroll);
        frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


    }
}
