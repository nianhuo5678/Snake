package Snake;

import javax.swing.*;
import java.awt.*;

public class Snake extends JPanel {
	public enum Direction {LEFT, RIGHT, UP, DOWN};
	private Direction direction;//蛇运行的方向
	private int length;//蛇的长度
	public int[] snakeX, snakeY;//两个数组分别记录蛇在X和Y坐标的位置
	private static final int BLOCK_SIZE = 10;
	private Game game;
    private Food food;
	public int getLength() {
		return length;
	}
	public void setLength(int length) {
		this.length = length;
	}
	public Direction getDirection() {
		return direction;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public Snake(Direction direction, int length) {
//		super();
		this.direction = direction;
		this.length = length;
		game = new Game();
		snakeX = new int[30];
		snakeY = new int[30];
    	//初始化蛇的X,Y坐标
        snakeX[0] = 150;
        snakeX[1] = 140;
        snakeX[2] = 130;
        snakeX[3] = 120;
        snakeY[0] = 150;
        snakeY[1] = 150;
        snakeY[2] = 150;
        snakeY[3] = 150;
	}
	//根据方向移动整条蛇的坐标
	public void move() {
		if ( this.die(this.snakeX, this.snakeY, this.direction) ) {
			game.over();
		}
		else {
			//把蛇身向蛇头方向移动一格，再根据方向，设置蛇头的新位置
			this.follow(snakeX, snakeY);
	    	switch(this.direction) {
	    	case UP:
	    		snakeY[0] = snakeY[0] - BLOCK_SIZE;
	    	break;
	    	case DOWN:
	    		snakeY[0] = snakeY[0] + BLOCK_SIZE;
	    	break;
	    	case LEFT:
	    		snakeX[0] = snakeX[0] - BLOCK_SIZE;
	    	break;
	    	case RIGHT:
	    		snakeX[0] = snakeX[0] + BLOCK_SIZE;
	    	break;
	    	} 
			repaint();	
			if (eat(snakeX[0], snakeY[0], food.getX(), food.getY())) {
				length++;
				game.addScore();
				//重置食物的XY坐标
	        	food.setX(food.newPosition(snakeX));
	        	food.setX(food.newPosition(snakeY));
				repaint();
			}
		}
	}
	//蛇身跟随蛇头移动
	public void follow(int[] snakeX, int[] snakeY) {
		for(int i = length - 1; i > 0; i--) {
			snakeX[i] = snakeX[i-1];
			snakeY[i] = snakeY[i-1];
		}
	}
	//判断是否能吃食物
	public boolean eat(int snakeX, int snakeY, int foodX, int foodY) {
		if (snakeX == foodX && snakeY == foodY)
			return true;
		else
			return false;
	}
	//判断蛇是否死
	public boolean die(int snakeX[], int snakeY[], Direction d) {
		boolean isDie = true;
		int newX = 0, newY = 0;
		
		//如果移动后的XY坐标碰到围墙，返回true
		if (	(snakeX[0] - BLOCK_SIZE) < 0 || 
				(snakeX[0] + BLOCK_SIZE) > 290 ||
				(snakeY[0] - BLOCK_SIZE) < 0 ||
				(snakeY[0] + BLOCK_SIZE) > 290 ) {
			isDie = true;
			return isDie;
		}
//		//如果头碰到自身，返回true
    	switch(d) {
    	case UP:
    		newX = snakeX[0];
    		newY = snakeY[0] - BLOCK_SIZE;
    	break;
    	
    	case DOWN:
    		newX = snakeX[0];
    		newY = snakeY[0] + BLOCK_SIZE;
    	break;
    	
    	case LEFT:
    		newX = snakeX[0] - BLOCK_SIZE;
    		newY = snakeY[0];
    	break;
    	
    	case RIGHT:
    		newX = snakeX[0] + BLOCK_SIZE;
    		newY = snakeY[0];
    	break;
    	} 
    	
		for (int i = 1; i < length; i++) {
			if ( newX == snakeX[i] && newY == snakeY[i] ) {
				isDie = true;
				return isDie;
			}
			else {
				isDie = false;
			}
		}
		return isDie;	
	}
}
