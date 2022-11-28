package test;

import java.awt.BorderLayout;
import java.awt.Color;

import javax.swing.JLabel;
import javax.swing.JPanel;

import stockGUI.Logo;
import stockGUI.StockFrame;

public class Test { 
    public static void main(String[] args) { 
//    	StockFrame frame = new StockFrame();
//    	JLabel label = new Logo();
//    	
//    	
//		JPanel inputPanel = new JPanel();
//		inputPanel.setBackground(Color.white);
//		inputPanel.setBounds(0,0,600,700);
//		inputPanel.setLayout(new BorderLayout());
//		
//		
//		JPanel outputPanel = new JPanel();
//		outputPanel.setBackground(new Color(40, 46, 62));
//		outputPanel.setBounds(600,0,600,700);
//		outputPanel.setLayout(new BorderLayout());
//		
//		frame.add(inputPanel);
//		frame.add(outputPanel);
//		outputPanel.add(label);
//		frame.revalidate();
    	char s = '￧';
    	String s2 ="stockId=2, stockNum=0051, stockName=￥ﾅﾃ￥ﾤﾧ￤ﾸﾭ￥ﾞﾋ100, closePrice=52.15, monAvgPrice=51.78\r\n";
    	String s3 = "元大中型";
    	String s4 = "￥ﾅﾃ￥ﾤﾧ￤ﾸﾭ￥ﾞﾋ";
    	System.out.println(s3.length());
    	System.out.println(s4.length());
    	System.out.println((int)'元');
    	System.out.println((char)80);
    }
}