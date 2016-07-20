package Snake;

import javax.swing.*;
import java.awt.*;
import Snake.Snake.Direction;

public class MyFrame  extends JFrame{

	Direction direction;
	public MyFrame() {
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
		JMenuBar menu = new JMenuBar();
		snakeFrame.setJMenuBar(menu);
		snakeFrame.setJMenuBar(menu);
		JMenu game = new JMenu("游戏");
		JMenuItem newgame = game.add("新游戏");
		JMenuItem pause = game.add("暂停");
		JMenuItem goon = game.add("继续");
		JMenuItem exit = game.add("退出");
		JMenu help = new JMenu("帮助");
		JMenuItem about = help.add("关于");
		menu.add(game);
		menu.add(help);
		snakeFrame.setVisible(true);
	}

}
