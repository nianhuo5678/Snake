package Snake;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class SnakeBlock extends JPanel implements KeyListener {
	
	enum Direction {
		LEFT, RIGHT, UP, DOWN
	};
	//默认方向为向右
	Direction direction = Direction.RIGHT;
	Timer timer;
//	int x = 150,y = 150;
	int length = 4;
	//两个数组snakeX,snakeY分别储存蛇的X和Y坐标,坐标的取值必须时10的倍数
	int[] snakeX = new int[30];
	int[] snakeY = new int[30];
	private static final int BLOCK_SIZE = 5;
	Food food;
	//食物XY坐标
	int foodX, foodY;
	

    public SnakeBlock() {
    	//初始化蛇的X,Y坐标
        snakeX[0] = 150;
        snakeX[1] = 140;
        snakeX[2] = 130;
        snakeX[3] = 120;
        snakeY[0] = 150;
        snakeY[1] = 150;
        snakeY[2] = 150;
        snakeY[3] = 150;
        //初始化食物的XY坐标
        food = new Food();
        foodX = food.getX();
        foodY = food.getY();
        //开始计数器
    	timer = new Timer(100, new TimerListener());
        timer.start();
	}
    
    @Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        //打印围栏
        for (int i = 0; i < 30; i++)
        {
        	for (int j = 0; j < 30; j++) {
        		if (i == 0 || j == 0 || i == 29 || j == 29) {
        			g.fillRect( (i * 10), (j * 10), 10, 10 );
        		}
        	}
        }
        //打印蛇
        for (int i = 0; i <= length; i++) {
        	g.fillRect( snakeX[i], snakeY[i], 10, 10 );
        }
        //打印食物
        g.fillRect(foodX, foodY, 10, 10);
    }
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
        switch (e.getKeyCode()) {
    
        case KeyEvent.VK_RIGHT:
        	if (direction == Direction.UP || direction == Direction.DOWN) {
        		direction = Direction.RIGHT;
        		right();
        		break;
        	}
        	
        case KeyEvent.VK_LEFT:
        	if (direction == Direction.UP || direction == Direction.DOWN) {
        		direction = Direction.LEFT;
        		left();
        	}
        	break;
        	
        case KeyEvent.VK_UP:
        	if (direction == Direction.LEFT || direction == Direction.RIGHT) {
        		direction = Direction.UP;
        		up();
        	}
        	break;
        	
        case KeyEvent.VK_DOWN:
        	if (direction == Direction.LEFT || direction == Direction.RIGHT) {
        		direction = Direction.DOWN;
        		down();
        	}
        	break;
        }
	}
	
	public void gameOver(){
		System.out.println("you lost");
		JOptionPane.showMessageDialog(null, "GAME OVER");
		timer.stop();
	}

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
	
	public boolean eat(int snakeX, int snakeY, int foodX, int foodY) {
		if (snakeX == foodX && snakeY == foodY)
			return true;
		else
			return false;
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	public void move(int[] snake) {
		for(int i = length - 1; i > 0; i--) {
			snake[i] = snake[i-1];
		}
	}

	public void left() {
		if ( die(snakeX, snakeY, direction) ) {
			gameOver();
		}
		else {
//			x = x - BLOCK_SIZE;
			move(snakeX);
			move(snakeY);
			snakeX[0] = snakeX[0] - BLOCK_SIZE;
			repaint();	
			if (eat(snakeX[0], snakeY[0], foodX, foodY)) {
				length++;
	        	foodX = food.newPosition(snakeX);
	        	foodY = food.newPosition(snakeY);
				repaint();
			}
		}
	}
	
	public void right() {
		if ( die(snakeX, snakeY, direction) ) {
			gameOver();
		}
		else {
//			x = x + BLOCK_SIZE;
			move(snakeX);
			move(snakeY);
			snakeX[0] = snakeX[0] + BLOCK_SIZE;
			repaint();
			if (eat(snakeX[0], snakeY[0], foodX, foodY)) {
				length++;
	        	foodX = food.newPosition(snakeX);
	        	foodY = food.newPosition(snakeY);
				repaint();
			}
		}
	}
	
	public void up() {
		if ( die(snakeX, snakeY, direction) ) {
			gameOver();
		}
		else {
//			y = y - BLOCK_SIZE;
			move(snakeX);
			move(snakeY);
			snakeY[0] = snakeY[0] - BLOCK_SIZE;
			repaint();
			if (eat(snakeX[0], snakeY[0], foodX, foodY)) {
				length++;
	        	foodX = food.newPosition(snakeX);
	        	foodY = food.newPosition(snakeY);
				repaint();
			}
		}
	}
	
	public void down() {
		if ( die(snakeX, snakeY, direction) ) {
			gameOver();
		}
		else {
//			y = y + BLOCK_SIZE;
			move(snakeX);
			move(snakeY);
			snakeY[0] = snakeY[0] + BLOCK_SIZE;
			repaint();
			if (eat(snakeX[0], snakeY[0], foodX, foodY)) {
				length++;
	        	foodX = food.newPosition(snakeX);
	        	foodY = food.newPosition(snakeY);
				repaint();
			}
		}
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
        	
        	//try food
//        	foodX = food.newPosition(snakeX);
//        	foodY = food.newPosition(snakeY);
//        	System.out.println("(X:Y)=(" + foodX + ":" + foodY + ")");
        }
    }
	
}
