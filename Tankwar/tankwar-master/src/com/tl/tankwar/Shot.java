package com.tl.tankwar;

/**
 *射击
 */
public class Shot implements Runnable {
	//子弹横坐标
	private int x;
	//子弹纵坐标
	private int y;
	//子弹半径
	private int r = 5;
	//子弹方向
	private int direct;
	//子弹速度
	private int speed = 20;
	//子弹状态
	public boolean isAlive = true;
	
	/**
	 * 创建子弹类
	 */
	public Shot(int x, int y, int direct) {
		this.x = x;
		this.y = y;
		this.direct = direct;
	}
	
	/**
	 * 子弹动作
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
		//子弹射击
		while (this.isAlive) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			
			if (this.direct == 0) {
				//向上射击
				this.y -= this.speed;
			} else if (this.direct == 1) {
				//向下射击
				this.y += this.speed;
			} else if (this.direct == 2) {
				//向左射击
				this.x -= this.speed;
			} else if (this.direct == 3) {
				//向右射击
				this.x += this.speed;
			}
			
			//如果子弹不在屏幕内，则失效
			if (this.x < 0 || this.x > 800 || this.y < 0 || this.y > 600) {
				this.isAlive = false;
			}
		}
	}

	
}
