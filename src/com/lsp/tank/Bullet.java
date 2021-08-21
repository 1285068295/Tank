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
    /**
     * 游戏窗口引用
     */
    private TankFrame tf;
    /** 子弹的速度 */
    private static final int SPEED = 2;
    /** 子弹为圆形 外切正方形的长和宽的大小*/
    public static int WIDTH = ResourceMgr.bulletU.getWidth();
    public static int HEIGHT = ResourceMgr.bulletU.getHeight();

    /**子弹是否还存活 默认存活的 */
    private boolean living = true;

    /**
     * 子弹位置
     */
    private int x, y;
    /** 子弹方向*/
    private Dir dir;

    public Bullet(int x, int y, Dir dir, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.tf = tf;
    }

    public void paint(Graphics g){
        if(!living) {
            tf.bullets.remove(this);
        }
        switch(dir) {
            case LEFT:
                g.drawImage(ResourceMgr.bulletL, x, y, null);
                break;
            case UP:
                g.drawImage(ResourceMgr.bulletU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(ResourceMgr.bulletR, x, y, null);
                break;
            case DOWN:
                g.drawImage(ResourceMgr.bulletD, x, y, null);
                break;
        }
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
        // 移动越界的时候需要删除子弹  页面不再显示
        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT){
            living = false;
        }

    }

}
