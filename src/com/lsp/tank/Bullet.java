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


    /**
     * 默认是敌人的子弹
     */
    private Group group = Group.BAD;
    /**
     * 子弹的速度
     * 注意需要于坦克的速度有差值 如果相同则同时移动时看不出来是否发射了子弹
     */
    private static final int SPEED = 4;
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

    public Bullet(int x, int y, Dir dir, Group group, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
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

    /**
     * 检测子弹是否与坦克发生了碰撞
     * 碰撞了的话需要从界面上移除坦克与子弹
     * @param tank
     */
    public void collideWith(Tank tank) {
        // 敌人的坦克与敌人的子弹不做碰撞检测
        if (this.group == tank.getGroup()) {return;}


        // 矩形的位置与大小
        Rectangle b = new Rectangle(this.x, this.y, Bullet.WIDTH, Bullet.HEIGHT);
        Rectangle t = new Rectangle(tank.getX(), tank.getY(), Tank.WIDTH, Tank.HEIGHT);

         // intersects 横断;相交;交叉;横穿;贯穿
        if(b.intersects(t)){
            // 设置living属性为false 下次执行paint方法时 不再画图形
            this.die();
            tank.die();
        }
    }

    /**
     * 子弹消亡
     */
    public void die() {
        this.living = false;
    }
}
