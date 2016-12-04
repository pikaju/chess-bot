package project.io;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Input implements MouseListener, MouseMotionListener {

	public static final int BUTTON_LEFT = MouseEvent.BUTTON1;
	
	private boolean[] buttons;
	private int mx;
	private int my;
	private int lx;
	private int ly;
	private int dx;
	private int dy;
	
	public Input() {
		buttons = new boolean[16];
	}
	
	public void refresh() {
		dx = mx - lx;
		dy = my - ly;
		lx = mx;
		ly = my;
		for (int i = 0; i < buttons.length; i++) {
			buttons[i] = false;
		}
	}

	public boolean getButton(int button) {
		return buttons[button];
	}
	
	public int getX()  {
		return mx;
	}
	
	public int getY() {
		return my;
	}
	
	public int getDX() {
		return dx;
	}
	
	public int getDY() {
		return dy;
	}
	
	public void mouseDragged(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
	}

	public void mouseMoved(MouseEvent e) {
		mx = e.getX();
		my = e.getY();
	}

	public void mouseClicked(MouseEvent e) {
		buttons[e.getButton()] = true;
	}

	public void mouseEntered(MouseEvent e) {
		
	}

	public void mouseExited(MouseEvent e) {
		
	}

	public void mousePressed(MouseEvent e) {
		
	}

	public void mouseReleased(MouseEvent e) {
		
	}
	
}
