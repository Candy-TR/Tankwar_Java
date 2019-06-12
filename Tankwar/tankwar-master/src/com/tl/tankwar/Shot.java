package com.tl.tankwar;

/**
 *���
 */
public class Shot implements Runnable {
	//�ӵ�������
	private int x;
	//�ӵ�������
	private int y;
	//�ӵ��뾶
	private int r = 5;
	//�ӵ�����
	private int direct;
	//�ӵ��ٶ�
	private int speed = 20;
	//�ӵ�״̬
	public boolean isAlive = true;
	
	/**
	 * �����ӵ���
	 */
	public Shot(int x, int y, int direct) {
		this.x = x;
		this.y = y;
		this.direct = direct;
	}
	
	/**
	 * �ӵ�����
	 */
	public int getDirect() {
		return direct;
	}
	
	public int getR() {
		return r;
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
	
	public void run() {
		//�ӵ����
		while (this.isAlive) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if (this.direct == 0) {
				//�������
				this.y -= this.speed;
			} else if (this.direct == 1) {
				//�������
				this.y += this.speed;
			} else if (this.direct == 2) {
				//�������
				this.x -= this.speed;
			} else if (this.direct == 3) {
				//�������
				this.x += this.speed;
			}
			
			//����ӵ�������Ļ�ڣ���ʧЧ
			if (this.x < 0 || this.x > 800 || this.y < 0 || this.y > 600) {
				this.isAlive = false;
			}
		}
	}

	
}
