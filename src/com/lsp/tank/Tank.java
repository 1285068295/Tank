package com.lsp.tank;

import java.awt.*;

public class Tank {
    /**
     * 坦克的速度
     */
    private static final int SPEED = 2;
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
     * 构造方法创建坦克时默认设置方向为向右
     */
	public Tank(int x, int y, Dir dir ) {
		this.x = x;
		this.y = y;
		this.dir = dir;
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
        // 画出一个矩形 向右x变大  向下y变大
        g.fillRect(x, y, 50, 50);
        if (!moving) {
            // 按下键盘修改为true才移动坦克
            return;
        }
        move();
    }


}
