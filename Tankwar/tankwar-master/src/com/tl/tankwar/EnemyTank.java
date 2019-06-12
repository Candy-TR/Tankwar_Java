package com.tl.tankwar;

/*
 * ����
 */

public class EnemyTank extends Tank implements Runnable {

	public static int num = 0;
	
	public EnemyTank(int x, int y, int direct) {
		super(x, y, direct);
	}

	public void run() {
		while (this.isAlive) { //����õ��˴��

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
		// �����ƶ�
		if (this.getDirect() == 0) {
			// �����ƶ�
			if (this.getY() > 0) {
				this.moveUp();
			} else {
				//����Ѿ������Ϸ�
				int randomDirect = (int)(Math.random() * 4);
				this.setDirect(randomDirect);
			}
		} else if (this.getDirect() == 1) {
			// �����ƶ�
			if (this.getY() < (600 - 80)) {
				this.moveDown();
			} else {
				//����Ѿ������·�
				int randomDirect = (int)(Math.random() * 4);
				this.setDirect(randomDirect);
			}
		} else if (this.getDirect() == 2) {
			// �����ƶ�
			if (this.getX() > 0) {
				this.moveLeft();
			} else {
				//����Ѿ�������
				int randomDirect = (int)(Math.random() * 4);
				this.setDirect(randomDirect);
			}
		} else if (this.getDirect() == 3) {
			// �����ƶ�
			if (this.getX() < (800 - 50)) {
				this.moveRight();
			} else {
				//����Ѿ������ҷ�
				int randomDirect = (int)(Math.random() * 4);
				this.setDirect(randomDirect);
			}
		}
	}
}
