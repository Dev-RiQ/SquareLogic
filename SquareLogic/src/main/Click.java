package main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Click implements MouseListener, MouseMotionListener{
	
	public boolean isClick, isX ,isDragC, isDragX ,is0, is1, is2;
	public int cy, cx, pcy, pcx, sy,sx;
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) { // 누르면
		int code = e.getButton();
		if(code == e.BUTTON1) {
			isClick = true;
			isDragC = true;
			cy = e.getY();
			cx = e.getX();
			pcy = e.getY();
			pcx = e.getX();
		}else if(code == e.BUTTON3) {
			isX = true;
			isDragX = true;
			cy = e.getY();
			cx = e.getX();
			pcy = e.getY();
			pcx = e.getX();
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) { // 떼면
		int code = e.getButton();
		if(code == e.BUTTON1) {
			isClick = false;
			isDragC = false;
			is0 = false;
			is1 = false;
			is2 = false;
		}else if(code == e.BUTTON3) {
			isX = false;
			isDragX = false;
			is0 = false;
			is1 = false;
			is2 = false;
		}
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(MouseEvent e) { // 누르고 있으면
		cy = e.getY();
		cx = e.getX();
		sy = cy;
		sx = cx;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		sy = e.getY();
		sx = e.getX();
	}

}
