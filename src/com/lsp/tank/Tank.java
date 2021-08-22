package com.lsp.tank;

import java.awt.*;
import java.util.Random;

public class Tank {
    /**
     * 默认是敌人的坦克
     */
    private Group group = Group.BAD;
    /**
     * 坦克的速度
     */
    private static final int SPEED = 10;
    /**
     * 敌人移动和发射炮弹都需要是随机的
     */
    private Random random = new Random();

    /**
     * 碰撞检测使用  只需要用一个就行  减少内存占用
     * 每次移动后更行rect的坐标
     */
    Rectangle rect = new Rectangle();

    /**
     * 坦克的大小 图片是 60*60
     */
    public static int WIDTH = ResourceMgr.goodTankU.getWidth();
    public static int HEIGHT = ResourceMgr.goodTankU.getHeight();


    /**坦克是否还存活 默认存活的 */
    private boolean living = true;

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
    private boolean moving = true;

    /**
     * 游戏窗口引用
     */
    private TankFrame tf;

    /**
     * 构造方法创建坦克时默认设置方向为向右
     */
	public Tank(int x, int y, Dir dir, Group group, TankFrame tf) {
		this.x = x;
		this.y = y;
		this.dir = dir;
		this.group = group;
		this.tf = tf;

		// 初始化用来检测碰撞的Rectangle
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;
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

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
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
        if (!living) {
            return;
        }

        if (!moving) {
            return;
        }
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


        // 敌人的坦克时随机的发射炮弹
        if (this.group.equals(Group.BAD) && random.nextInt(100) > 95) {
            fire();
            randomDir();
        }
        // 进行边界检测
        boundsCheck();


        // 更新碰撞检测的矩形
        rect.x = this.x;
        rect.y = this.y;
    }


    /**
     * 边界检测
     */
    private void boundsCheck() {
        if (this.x < 2) {
            x = 2;
        }
        if (this.y < 28) {
            y = 28;
        }
        if (this.x > TankFrame.GAME_WIDTH - Tank.WIDTH - 2) {
            x = TankFrame.GAME_WIDTH - Tank.WIDTH - 2;
        }
        if (this.y > TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2) {
            y = TankFrame.GAME_HEIGHT - Tank.HEIGHT - 2;
        }
    }


    /**
     * 随机改变移动的方向
     */
    private void randomDir() {
        // Dir.values() 返回所有可能值的数组
        this.dir = Dir.values()[random.nextInt(4)];
    }

    public void paint(Graphics g) {
        if(!living) {
            tf.tanks.remove(this);
        }
        // 根据方向画出坦克 根据敌我坦克画出不同的坦克
        switch (dir) {
            case LEFT:
                g.drawImage(Group.GOOD == this.group ? ResourceMgr.goodTankL : ResourceMgr.badTankL, x, y, null);
                break;
            case UP:
                g.drawImage(Group.GOOD == this.group ? ResourceMgr.goodTankU : ResourceMgr.badTankU, x, y, null);
                break;
            case RIGHT:
                g.drawImage(Group.GOOD == this.group ? ResourceMgr.goodTankR : ResourceMgr.badTankR, x, y, null);
                break;
            case DOWN:
                g.drawImage(Group.GOOD == this.group ? ResourceMgr.goodTankD : ResourceMgr.badTankD, x, y, null);
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
        // 需要计算出子弹图片左上角的位置所以要减去图片一半长宽
        int bX = this.x;
        int bY = this.y;
        switch (dir) {
            case LEFT:
                // 了让子弹贴住炮筒
                bX = bX - Bullet.WIDTH;
                bY = bY + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
                break;
            case RIGHT:
                bX += Tank.WIDTH;
                bY += Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
                break;
            case UP:
                bX = bX + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
                bY = bY - Bullet.HEIGHT;
                break;
            case DOWN:
                bX = bX + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
                bY = bY + Tank.HEIGHT;
                break;
            default:
                break;
        }
        // 坦克创建子弹时  需要把敌我分类传入进去
        tf.bullets.add(new Bullet(bX, bY, this.dir, this.group, this.tf));
    }

    /**
     * 坦克消亡
     */
    public void die() {
        this.living = false;
    }
}
