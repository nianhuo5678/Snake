package Snake;

import javax.swing.*;
import java.awt.*;
import Snake.Snake.Direction;

public class MyFrame  extends JFrame{

	Direction direction;
	public MyFrame() {
//		SnakeBlock sb = new SnakeBlock();
		Game game = new Game();
		Snake snake = new Snake(direction.RIGHT, 4);
		addKeyListener(game);
		add(game);
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyFrame snakeFrame = new MyFrame();
		snakeFrame.setSize(400, 350);
		snakeFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		snakeFrame.setVisible(true);
	}

}
