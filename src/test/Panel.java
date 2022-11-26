package test;

import java.awt.Color;

import javax.swing.JPanel;

import stockGUI.StockFrame;

public class Panel {

	public static void main(String[] args) {
		//Jpanel = a GUI component that function as a container to hold other components.
		StockFrame frame = new StockFrame();
		JPanel redPanel = new JPanel();
		redPanel.setBackground(Color.red);
		redPanel.setBounds(0,0,100,100);
		redPanel.setOpaque(true);
		frame.setLayout(null);
    	frame.add(redPanel);
    	frame.revalidate();
	}

}
