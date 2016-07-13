package test;

import javax.swing.*;

public class MyFrame extends JFrame {
	
	private static final int DEFAULT_WIDTH = 300;
	private static final int DEFAULT_LENGTH =300;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame frame = new MyFrame();
		frame.setSize(DEFAULT_WIDTH, DEFAULT_LENGTH);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
