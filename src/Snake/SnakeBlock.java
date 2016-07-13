package Snake;

import javax.swing.*;



import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SnakeBlock extends JPanel implements KeyListener {
	
	int x,y;
	String direction = "right";

    public SnakeBlock() {
        Timer timer = new Timer(1000, new TimerListener());
        timer.start();
	}
    
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.fillRect( 150 + x, 150 + y, 10, 10 );
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

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void left() {
		x = x - 1;
		repaint();
	}
	
	public void right() {
		x = x + 1;
		repaint();
	}
	
	public void up() {
		y = y - 1;
		repaint();
	}
	
	public void down() {
		y = y + 1;
		repaint();
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
