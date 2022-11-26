package stockGUI;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class StockFrame extends JFrame {
	public StockFrame() {
		this.setTitle("Stockdata access tool");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // make closing window terminate the program as well
		// this.setResizable(false); //prevent this from being resize

		this.setSize(1200, 700);
		this.setVisible(true);

		ImageIcon image = new ImageIcon("../Project/stocklogo.jpg");
		this.setIconImage(image.getImage());
		this.getContentPane().setBackground(new Color(40, 46, 62));

	}
}
