package com.lsp.tank;

import java.awt.*;

/**
 * @author ：Lisp
 * @date： 2021/8/21
 * @version: V1.0
 * @slogan:
 * @description :坦克子弹
 */
public class Bullet {

    private static final int SPEED = 10;
    /** 子弹为圆形 外切正方形的长和宽的大小*/
    private static final int WIDTH = 10;
    private static final int HEIGHT = 10;

    /**
     * 子弹位置
     */
    private int x, y;
    /** 子弹方向*/
    private Dir dir;

    public Bullet(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }

    public void paint(Graphics g){
        Color originalColor = g.getColor();
        g.setColor(Color.RED);
        // 设置子弹的颜色后画出子弹
        g.fillOval(x,y,WIDTH,HEIGHT);
        g.setColor(originalColor);
        // 移动子弹
        move();
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

}
