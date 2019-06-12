package com.tl.tankwar;

import java.util.Vector;

/*
 * Ì¹¿Ë´óÐ¡
 */
public class Tank {

	private int x;

	private int y;

	private int r = 15;
	
	public int getR() {
		return r;
	}

	private int direct;

	private int speed = 5;

	public boolean isAlive = true;

	public Vector<Shot> shots = new Vector<Shot>();

	public Tank(int x, int y, int direct) {
		this.x = x;
		this.y = y;
		this.direct = direct;
	}
	
	public void fire() {
		Shot s = null;

		if (this.direct == 0) {

			s = new Shot(this.x + 10, this.y - 10, this.direct);
			shots.add(s);
		} else if (this.direct == 1) {

			s = new Shot(this.x + 10, this.y + 30, this.direct);
			shots.add(s);
		} else if (this.direct == 2) {

			s = new Shot(this.x - 10, this.y + 10, this.direct);
			shots.add(s);
		} else if (this.direct == 3) {

			s = new Shot(this.x + 30, this.y + 10, this.direct);
			shots.add(s);
		}
		new Thread(s).start();
	}


	public void moveUp() {
		this.y -= this.speed;
	}

	public void moveDown() {
		this.y += this.speed;
	}
	

	public void moveLeft() {
		this.x -= this.speed;
	}
	

	public void moveRight() {
		this.x += this.speed;
	}
	

	public int getDirect() {
		return direct;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
}
