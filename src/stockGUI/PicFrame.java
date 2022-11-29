package stockGUI;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class PicFrame extends JFrame {

	public PicFrame(String stockNum) {
		this.setTitle("Stock number : " + stockNum);
		this.setResizable(false); //prevent this from being resize
		this.setSize(600, 400);
		String date = "20221129";
		ImageIcon image = new ImageIcon("../Project/stockimg/20221129/" + stockNum + "/"+stockNum+ "_DATE.gif");
		JLabel label = new JLabel();
		label.setHorizontalAlignment(JLabel.CENTER);//border layout
		label.setIcon(image);
		this.getContentPane().add(label);

	}



}
