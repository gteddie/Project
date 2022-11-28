package stockGUI;

import java.awt.Color;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Logo extends JLabel {
	public Logo() {

		ImageIcon logo = new ImageIcon("../Project/stocklogo3.png");

		this.setText("Make Differences");
		this.setIcon(logo);
		this.setHorizontalTextPosition(JLabel.CENTER); // LEFT, CENTER, RIGHT of image icon
		this.setVerticalTextPosition(JLabel.BOTTOM); // TOP CENTER BOTTOM of the image icon
		this.setForeground(new Color(0xFFFFFF)); // set font color
		this.setFont(new Font("Arial", Font.BOLD, 15)); // set font
		this.setVerticalAlignment(JLabel.TOP); //border layout 
		this.setHorizontalAlignment(JLabel.RIGHT);//border layout
		this.setIconTextGap(-20); // set gap of text to img
	}
}
