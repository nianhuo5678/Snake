package Snake;

import javax.swing.*;
import java.awt.*;

public class Snake  extends JFrame {

	public Snake() {
		SnakeBlock sb = new SnakeBlock();
		addKeyListener(sb);
		add(sb);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Snake snakeFrame = new Snake();
		snakeFrame.setSize(320, 350);
		snakeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		snakeFrame.setVisible(true);
	}

}
