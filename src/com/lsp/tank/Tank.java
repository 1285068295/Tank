package com.lsp.tank;

import java.awt.*;

public class Tank {
    /**
     * 坦克的速度
     */
    private static final int SPEED = 2;

    /**
     * 坦克的大小
     */
    public static int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static int HEIGHT = ResourceMgr.goodTankU.getHeight();


    /**
     * 坦克的位置坐标
     */
	private int x, y;
    /**
     * 坦克移动的方向
     */
	private Dir dir;
    /**
     * 是否移动坦克 只有在按下上下左右键时才移动坦克
     */
    private boolean moving = false;

    /**
     * 游戏窗口引用
     */
    private TankFrame tf;

    /**
     * 构造方法创建坦克时默认设置方向为向右
     */
	public Tank(int x, int y, Dir dir, TankFrame tf) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.tf = tf;
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

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }


    /**
     * 移动
     */
    private void move() {
        switch (dir) {
            case LEFT:
                // 这里修改x值之后，在下次调用paint方法时 调用fillRect实现坦克的移动
                x -= SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }

    }

    public void paint(Graphics g) {
        // 根据方向画出坦克
        switch (dir) {
            case LEFT:
                g.drawImage(ResourceMgr.goodTankL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.goodTankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.goodTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.goodTankD, x, y, null);
                break;
        }
        if (!moving) {
            return;
        }
        move();
    }


    /**
     * 发射子弹
     */
    public void fire() {
        // 子弹打出的初始位置为坦克的正中心
        int bX = this.x + Tank.WIDTH/2 - Bullet.WIDTH/2;
        int bY = this.y + Tank.HEIGHT/2 - Bullet.HEIGHT/2;
        tf.bullets.add( new Bullet(bX, bY, this.dir, this.tf));
    }
}
