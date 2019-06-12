package com.tl.tankwar;

/*
 * 敌人
 */

public class EnemyTank extends Tank implements Runnable {

	public static int num = 0;
	
	public EnemyTank(int x, int y, int direct) {
		super(x, y, direct);
	}

	public void run() {
		while (this.isAlive) { //如果该敌人存活

			int ran = (int)(Math.random() * (100));
			if (ran == 0) {

				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}

				int randomDirect = (int)(Math.random() * 4);
				this.setDirect(randomDirect);
			} else if (ran >0 && ran < 90) {

				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				this.move();
			} else if (ran >= 90) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				this.fire();
			}
		}
	}

	public void move() {
		// 敌人移动
		if (this.getDirect() == 0) {
			// 向上移动
			if (this.getY() > 0) {
				this.moveUp();
			} else {
				//如果已经在最上方
				int randomDirect = (int)(Math.random() * 4);
				this.setDirect(randomDirect);
			}
		} else if (this.getDirect() == 1) {
			// 向下移动
			if (this.getY() < (600 - 80)) {
				this.moveDown();
			} else {
				//如果已经在最下方
				int randomDirect = (int)(Math.random() * 4);
				this.setDirect(randomDirect);
			}
		} else if (this.getDirect() == 2) {
			// 向左移动
			if (this.getX() > 0) {
				this.moveLeft();
			} else {
				//如果已经在最左方
				int randomDirect = (int)(Math.random() * 4);
				this.setDirect(randomDirect);
			}
		} else if (this.getDirect() == 3) {
			// 向右移动
			if (this.getX() < (800 - 50)) {
				this.moveRight();
			} else {
				//如果已经在最右方
				int randomDirect = (int)(Math.random() * 4);
				this.setDirect(randomDirect);
			}
		}
	}
}
