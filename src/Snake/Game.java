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
	private static final int DEFALUT_SNAKE_LENGTH = 4;//蛇初始化长度4
	private static final Direction DEFAULT_SNAKE_DIRECTION = Direction.RIGHT;//蛇初始化运动方向右
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
		this.difficulty = 1;
		snake = new Snake(DEFAULT_SNAKE_DIRECTION, DEFALUT_SNAKE_LENGTH);
		food = new Food();
		timer = new Timer(300, new TimerListener());
		this.start();
	}
	
	//游戏开始的方法
	public void start(){
		this.score = 0;
		timer.start();
	}
	//游戏结束的方法
	public void over(){
		timer.stop();
		JOptionPane.showMessageDialog(null, "GAME OVER!! Your score:" + this.score );
		System.out.println("GAME OVER Your score: " + this.score );
	}
	
	public void moveSnake() {
    	if (snake.die(snake.snakeX, snake.snakeY, snake.getDirection()))
    		this.over();
    	else {
    		snake.move();
    		if (snake.eat(snake.snakeX[0], snake.snakeY[0], food.getX(), food.getY())) {
    			snake.addLength();
    			this.addScore();
    			//重置食物的XY坐标
    	        food.setX(food.newPosition(snake.snakeX));
    	        food.setY(food.newPosition(snake.snakeY));
    			repaint();
    		}
    	}
	}
	@Override
	public void keyPressed(KeyEvent e) {
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
        this.moveSnake();
	}
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}
	//处理键盘上下左右事件
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
        //打印分数
        g.drawString("score="+ this.score, 300, 10);
    }
    
    class TimerListener implements ActionListener {
    	@Override
        public void actionPerformed(ActionEvent e) {
    		moveSnake();
    		repaint();
        }
    }
}
