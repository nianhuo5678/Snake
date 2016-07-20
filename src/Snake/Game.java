package Snake;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.*;
import Snake.Snake.Direction;

public class Game extends JPanel implements KeyListener {
	/**
	 * 游戏类控制类。游戏的得分、难度。负责开始游戏、重置有戏状态。
	 * 打印围墙，蛇，食物
	 */
	private int score;
	private int difficulty;//分 1 2 3级，1级最慢，3级最快。
	private static int DEFALUT_SNAKE_LENGTH = 4;//蛇初始化长度4
//	private Direction direction = Direction.RIGHT;//蛇初始化运动方向右
	private static final int BLOCK_SIZE = 10;
	Timer timer;
	Snake snake;
	Food food;
	
	public int getScore() {
		return score;
	}
	public void addScore() {
		this.score++;
	}
	public Game() {
//		super();
		this.score = 0;
		this.difficulty = 1;
		snake = new Snake(Direction.RIGHT, DEFALUT_SNAKE_LENGTH);
		food = new Food();
		timer = new Timer(350,new TimerListener());
		this.start();
	}
	//游戏开始的方法
	public void start(){
		timer.start();
	}
	//游戏结束的方法
	public void over(){
		timer.stop();
	}
	
	@Override
	public void keyPressed(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	//处理键盘上下左右事件
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		//设定蛇下一步的运行方向
        switch (e.getKeyCode()) {
        case KeyEvent.VK_RIGHT:
        	if (snake.getDirection() == Direction.UP || snake.getDirection() == Direction.DOWN) {
        		snake.setDirection(Direction.RIGHT);
        	}
        	break;
        	
        case KeyEvent.VK_LEFT:
        	if (snake.getDirection() == Direction.UP || snake.getDirection() == Direction.DOWN) {
        		snake.setDirection(Direction.LEFT);
        	}
        	break;
     
        case KeyEvent.VK_UP:
        	if (snake.getDirection() == Direction.LEFT || snake.getDirection() == Direction.RIGHT) {
        		snake.setDirection(Direction.UP);
        	}
        	break;
        	
        case KeyEvent.VK_DOWN:
        	if (snake.getDirection() == Direction.LEFT || snake.getDirection() == Direction.RIGHT) {
        		snake.setDirection(Direction.DOWN);
        	}
        	break;
        }
        //以下内容执行不了
        //如果蛇死结束游戏，如果蛇不死调用移动蛇方法，吃食物方法。
    	if (snake.die(snake.snakeX, snake.snakeY, snake.getDirection()))
    		this.over();
    	else {
    		snake.move();
    		if (snake.eat(snake.snakeX[0], snake.snakeY[0], food.getX(), food.getY())) {
    			snake.addLength();
    			this.addScore();
    			//重置食物的XY坐标
    	        food.setX(food.newPosition(snake.snakeX));
    	        food.setX(food.newPosition(snake.snakeY));
    			repaint();
    		}
    		repaint();
    	}
	}
	
    @Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //打印围栏
        for (int i = 0; i < 30; i++)
        {
        	for (int j = 0; j < 30; j++) {
        		if (i == 0 || j == 0 || i == 29 || j == 29) {
        			g.fillRect( (i * BLOCK_SIZE), (j * BLOCK_SIZE), BLOCK_SIZE, BLOCK_SIZE );
        		}
        	}
        }
        //打印蛇
        for (int i = 0; i <= snake.getLength(); i++) {
        	g.fillRect( snake.snakeX[i], snake.snakeY[i], BLOCK_SIZE, BLOCK_SIZE );
        }
        //打印食物
        g.fillRect(food.getX(), food.getY(), BLOCK_SIZE, BLOCK_SIZE);
    }
    
    class TimerListener implements ActionListener {
    	@Override
        public void actionPerformed(ActionEvent e) {
    		snake.move();  
    		repaint();
        }
    }
}
