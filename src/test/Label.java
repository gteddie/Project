package test;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import stockGUI.StockFrame;

public class Label {
	public static void main(String[] args) {
    	StockFrame frame = new StockFrame();
    	
    	
    	ImageIcon logo = new ImageIcon("../Project/stocklogo3.png");
  
    	
    
    	JLabel label = new JLabel();   	
    	label.setText("Make Differences"); 
    	label.setIcon(logo);
    	label.setHorizontalTextPosition(JLabel.CENTER); //LEFT, CENTER, RIGHT of image icon
    	label.setVerticalTextPosition(JLabel.BOTTOM); //TOP CENTER BOTTOM of the image icon   	
    	label.setForeground(new Color(0xFFFFFF)); //set font color
    	label.setFont(new Font("Arial",Font.BOLD, 15)); //set font 

    	label.setIconTextGap(-20); //set gap of text to img
//    	label.setBackground(Color.BLACK);
//   	    label.setOpaque(true); //opacity of label, true: show false: disappear
//    	Border border = BorderFactory.createLineBorder(Color.green,3);
//    	label.setBorder(border); 
    	label.setVerticalAlignment(JLabel.TOP);
    	label.setHorizontalAlignment(JLabel.RIGHT);
 //   	label.setBounds(990,0,200,200);
    	
    	
//    	frame.setLayout(null);
    	frame.add(label);
//    	frame.pack(); //make frame 
    	frame.revalidate();
	}
}
