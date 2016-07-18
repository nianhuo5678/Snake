package Snake;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import Snake.Snake.Direction;

public class Game {
	//游戏类控制类。游戏的得分、难度。负责开始游戏、重置有戏状态
	private int score;
	private int difficulty;//分 1 2 3级，1级最慢，3级最快。
	private static int DEFALUT_SNAKE_LENGTH = 4;//蛇初始化长度4
	private Direction direction = Direction.RIGHT;//蛇初始化运动方向右
	private static final int BLOCK_SIZE = 10;
	Timer timer;
	Snake snake;
	
	public int getScore() {
		return score;
	}
	public void addScore() {
		this.score++;
	}
	public Game() {
		super();
		this.score = 0;
		this.difficulty = 1;
		snake = new Snake(direction, DEFALUT_SNAKE_LENGTH);
		timer = new Timer(1000,new TimerListener());
	}
	//游戏开始的方法
	public void start(){
		timer.start();
	}
	//游戏结束的方法
	public void over(){
		timer.stop();
	}
	
	
    class TimerListener implements ActionListener {
    	@Override
        public void actionPerformed(ActionEvent e) {
        	switch(direction) {
        	case UP:
        		up();
        	break; 	
        	case DOWN:
        		down();
        	break;
        	
        	case LEFT:
        		left();
        	break;
        	
        	case RIGHT:
        		right();
        	break;
        	}         	
        }
    }
}
