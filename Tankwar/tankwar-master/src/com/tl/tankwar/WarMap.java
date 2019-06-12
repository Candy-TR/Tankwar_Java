package com.tl.tankwar;

import java.awt.*;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.*;
import java.util.Vector;

import javax.swing.JLabel;
import javax.swing.JPanel;


public class WarMap extends JPanel implements KeyListener, Runnable {

	private static final long serialVersionUID = 1L;


	Hero hero = null;


	Vector<Tank> tanks = new Vector<Tank>();
	

	int enemyNum = 5;
	
	Singleton info = Singleton.getSingleton();
 
	public WarMap() {

		this.hero = new Hero(400, 300, 0);
		tanks.add(this.hero);
		this.add(new JLabel(info.info()));

		for (int i = 0; i < enemyNum; i++) {
			randomCreateEnemyTank();
		}
	}


	public void randomCreateEnemyTank() {

		int et_x = (int)(Math.random() * (800 - 30));

		int et_y = (int)(Math.random() * (600 - 50));

		int et_direct = (int)(Math.random() * (4));
  
		EnemyTank et = new EnemyTank(et_x, et_y, et_direct);

		EnemyTank.num++;
		

		tanks.add(et);
		
		new Thread(et).start();
	}


	public void paint(Graphics g) {
		super.paint(g);

		this.setBackground(Color.BLACK);

		for (int i = 0; i < tanks.size(); i++) {

			Tank t = tanks.get(i);

			if (t.isAlive) {

				this.drawTank(t, g);				
			} else {

				tanks.remove(t);
			}
		}

		for (int i = 0; i < tanks.size(); i++) {
			for (int j = 0; j < tanks.get(i).shots.size(); j++) {

				Shot s = tanks.get(i).shots.get(j);
	
				if (s.isAlive) {
				int x = s.getX();
				int y = s.getY();
					this.drawShot(s, x, y, g);
				} else {
				
					tanks.get(i).shots.remove(s);
				}
			}
		}

		for (int i = 0; i < tanks.size(); i++) {
			for (int j = 0; j < tanks.get(i).shots.size(); j++) {

				Shot s = tanks.get(i).shots.get(j);
				for (int k = 0; k < tanks.size(); k++) {
				
					Tank t = tanks.get(k);
				
					this.hitTank(s, t);
				}
			}
		}
	}

	public void hitTank(Shot s, Tank t) {

		for (int i = 0; i < t.shots.size(); i++) {
			if (s == t.shots.get(i)) {
				return;
			}
		}
		
	
		for (Tank tank : tanks) {
	
			if (tank instanceof EnemyTank) {
			
				for (int j = 0; j < tank.shots.size(); j++) {
				
					if (s == tank.shots.get(j) && t instanceof EnemyTank) {
					
						return;
					}
				}
			}
		}
		

		int tCenterX = t.getX() + t.getR();

		int tCenterY = t.getY() + t.getR();

		int sCenterX = s.getX() + s.getR();

		int sCenterY = s.getY() + s.getR();
	
		int x_s = Math.abs(tCenterX - sCenterX);
	
		int y_s = Math.abs(tCenterY - sCenterY);
		
		double c_s = Math.sqrt(x_s * x_s + y_s * y_s);
	
		if (c_s < (t.getR() + s.getR())) {
			t.isAlive = false;
			s.isAlive = false;
			

			if (t instanceof EnemyTank) {
				EnemyTank.num--;
			}
		}
	}

	private static Toolkit tk = Toolkit.getDefaultToolkit();
	private static Image[] tankImages = null;
	private static Map<String,Image> imgs = new HashMap<String,Image>();
	static {
		tankImages = new Image[] {
			tk.getImage(WarMap.class.getClassLoader().getResource("images/player1_up.png")),
			tk.getImage(WarMap.class.getClassLoader().getResource("images/player1_down.png")),
			tk.getImage(WarMap.class.getClassLoader().getResource("images/player1_left.png")),
			tk.getImage(WarMap.class.getClassLoader().getResource("images/player1_right.png")),
		};//装图片的数组
		
		imgs.put("0",tankImages[0]);
		imgs.put("1",tankImages[1]);
		imgs.put("2",tankImages[2]);
		imgs.put("3",tankImages[3]);//导入容器中
	}
	private static Image[] enemyImages = null;
	private static Map<String,Image> imgs_enemy = new HashMap<String,Image>();
	static {
		enemyImages = new Image[] {
			tk.getImage(WarMap.class.getClassLoader().getResource("images/bot_up.png")),
			tk.getImage(WarMap.class.getClassLoader().getResource("images/bot_down.png")),
			tk.getImage(WarMap.class.getClassLoader().getResource("images/bot_left.png")),
			tk.getImage(WarMap.class.getClassLoader().getResource("images/bot_right.png")),
		};//装图片的数组
		
		imgs_enemy.put("0",enemyImages[0]);
		imgs_enemy.put("1",enemyImages[1]);
		imgs_enemy.put("2",enemyImages[2]);
		imgs_enemy.put("3",enemyImages[3]);//导入容器中
	}
	private static Image[] bulletImages = null;
	private static Map<String,Image> imgs_bullet = new HashMap<String,Image>();
	static {
		bulletImages = new Image[] {
			tk.getImage(WarMap.class.getClassLoader().getResource("images/bullet_up.gif")),
			tk.getImage(WarMap.class.getClassLoader().getResource("images/bullet_down.gif")),
			tk.getImage(WarMap.class.getClassLoader().getResource("images/bullet_left.gif")),
			tk.getImage(WarMap.class.getClassLoader().getResource("images/bullet_right.gif")),
		};//装图片的数组
		
		imgs_bullet.put("0",bulletImages[0]);
		imgs_bullet.put("1",bulletImages[1]);
		imgs_bullet.put("2",bulletImages[2]);
		imgs_bullet.put("3",bulletImages[3]);//导入容器中
	}

	public void drawTank(Tank tank, Graphics g) {

		int x = tank.getX();
		int y = tank.getY();
		
		if (tank instanceof Hero) {
			if (tank.getDirect() == 0) {	

				g.drawImage(imgs.get("0"), x, y, null);
			} else if (tank.getDirect() == 1) {

				g.drawImage(imgs.get("1"), x, y, null);
			} else if (tank.getDirect() == 2) {

				g.drawImage(imgs.get("2"), x, y, null);
			} else if (tank.getDirect() == 3) {

				g.drawImage(imgs.get("3"), x, y, null);
			}
		}
 
		else {
			if (tank.getDirect() == 0) {	

				g.drawImage(imgs_enemy.get("0"), x, y, null);
			} else if (tank.getDirect() == 1) {

				g.drawImage(imgs_enemy.get("1"), x, y, null);
			} else if (tank.getDirect() == 2) {

				g.drawImage(imgs_enemy.get("2"), x, y, null);
			} else if (tank.getDirect() == 3) {

				g.drawImage(imgs_enemy.get("3"), x, y, null);
			}
		}
	}


	
	public void drawShot(Shot s, int x, int y, Graphics g) {
		
		if (s.getDirect() == 0) {	

			g.drawImage(imgs_bullet.get("0"), x, y, null);
		} else if (s.getDirect() == 1) {

			g.drawImage(imgs_bullet.get("1"), x, y, null);
		} else if (s.getDirect() == 2) {

			g.drawImage(imgs_bullet.get("2"), x, y, null);
		} else if (s.getDirect() == 3) {

			g.drawImage(imgs_bullet.get("3"), x, y, null);
		}
	}

	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
	
			this.hero.setDirect(0);
			this.hero.moveUp();
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
		
			this.hero.setDirect(1);
			this.hero.moveDown();
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			
			this.hero.setDirect(2);
			this.hero.moveLeft();
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			
			this.hero.setDirect(3);
			this.hero.moveRight();
		}

	
		if (e.getKeyCode() == KeyEvent.VK_F) {
			this.hero.fire();
		}
	}

	public void keyReleased(KeyEvent e) {
		

	}

	public void keyTyped(KeyEvent e) {
		

	}

	public void run() {
		
		while (true) {

			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
/*
 * 敌人数量增加
 */
		
			if (EnemyTank.num < 5) {
		
				int ran = (int)(Math.random() * 100);
				if (ran == 0) {
					randomCreateEnemyTank();
				}
			}
			
			this.repaint();
		}
	}
}
