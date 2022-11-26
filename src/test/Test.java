package test;

import javax.swing.JLabel;

import stockGUI.Logo;
import stockGUI.StockFrame;

public class Test { 
    public static void main(String[] args) { 
    	StockFrame frame = new StockFrame();
    	JLabel label = new Logo();
    	frame.add(label);
    	frame.revalidate();
    }
}