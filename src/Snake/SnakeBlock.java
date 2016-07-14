package Snake;

import javax.swing.*;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeBlock extends JPanel implements KeyListener {
	
	int x = 150,y = 150;
	String direction = "right";
	private static final int BLOCK_SIZE = 10;
	

    public SnakeBlock() {
        Timer timer = new Timer(1000, new TimerListener());
        timer.start();
	}
    @Override
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < 30; i++)
        {
        	for (int j = 0; j < 30; j++) {
        		if (i == 0 || j == 0 || i == 29 || j == 29) {
        			g.fillRect( (i * BLOCK_SIZE), (j * BLOCK_SIZE), 10, 10 );
        		}
        	}
        }
        g.fillRect( x, y, 10, 10 );
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
//            forward = Direction.right;
        	direction = "right";
            break;
            
        case KeyEvent.VK_LEFT:
//            forward = Direction.left;
        	direction = "left";
            break;
            
        case KeyEvent.VK_UP:
//        	forward = Direction.up;
        	direction = "up";
        	break;
        	
        case KeyEvent.VK_DOWN:
//        	forward = Direction.down;
        	direction = "down";
        	break;
        	
        }
	}

	public void die() {
		System.out.println("you lost");
		JOptionPane.showMessageDialog(null, "GAME OVER");
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void left() {
		if ( (x - BLOCK_SIZE) <= 0 ) {
			die();
		}
		else {
			x = x - BLOCK_SIZE;
			repaint();	
		}
	}
	
	public void right() {
		if ( (x + BLOCK_SIZE) >= 290) {
			die();
		}
		else {
			x = x + BLOCK_SIZE;
			repaint();
		}
	}
	
	public void up() {
		if ( (y - BLOCK_SIZE) <= 0) {
			die();
		}
		else {
			y = y - BLOCK_SIZE;
			repaint();
		}
	}
	
	public void down() {
		if ( (y + BLOCK_SIZE) >= 290) {
			die();
		}
		else {
			y = y + BLOCK_SIZE;
			repaint();
		}
	}
	
    class TimerListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
        	switch (direction) {
        	case "up":
        		up();
        	break;
        	case "down":
        		down();
        	break;
        	case "left":
        		left();
        	break;
        	case "right":
        		right();
        	break;
        	}
            repaint();
 
        }
    }
	
}
