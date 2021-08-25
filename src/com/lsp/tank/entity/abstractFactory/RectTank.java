package com.lsp.tank.entity.abstractFactory;

import com.lsp.tank.entity.*;

import java.awt.*;
import java.util.Random;

public class RectTank extends BaseTank {
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
   // public Rectangle rect = new Rectangle();

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
     * 坦克开炮的策略模式
     */
    private FireStrategy fireStrategy;

    /**
     * 构造方法创建坦克时默认设置方向为向右
     */
	public RectTank(int x, int y, Dir dir, Group group, TankFrame tf) {
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

        if (this.group == Group.GOOD) {
            // 配置文件加载  灵活性更强
            String goodFS = (String) PropertyMgr.get("goodFS");
            try {
                this.fireStrategy = (FourDirFireStrategy) Class.forName(goodFS).getConstructor().newInstance();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            this.fireStrategy = new DefaultFireStrategy();
        }

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

    public TankFrame getTf() {
        return tf;
    }

    public void setTf(TankFrame tf) {
        this.tf = tf;
    }

    public FireStrategy getFireStrategy() {
        return fireStrategy;
    }

    public void setFireStrategy(FireStrategy fireStrategy) {
        this.fireStrategy = fireStrategy;
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

        if(this.group == Group.GOOD){
            // 我的坦克发射炮弹要发出音乐
            // new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
        }


        // 敌人的坦克时随机的发射炮弹
        if ((this.group==Group.BAD) && random.nextInt(100) > 95) {
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
        if (this.x > TankFrame.GAME_WIDTH - RectTank.WIDTH - 2) {
            x = TankFrame.GAME_WIDTH - RectTank.WIDTH - 2;
        }
        if (this.y > TankFrame.GAME_HEIGHT - RectTank.HEIGHT - 2) {
            y = TankFrame.GAME_HEIGHT - RectTank.HEIGHT - 2;
        }
    }


    /**
     * 随机改变移动的方向
     */
    private void randomDir() {
        // Dir.values() 返回所有可能值的数组
        this.dir = Dir.values()[random.nextInt(4)];
    }

    /**
     * 子线程定时的执行repaint的方法  repaint的方法会执行坦克 子弹 爆炸 paint方法
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        if(!living) {
            tf.tanks.remove(this);
        }
        Color c = g.getColor();
        g.setColor(Group.GOOD == this.group ? Color.BLUE:Color.WHITE);
        g.fillRect(x,y,RectTank.WIDTH,RectTank.HEIGHT);
        g.setColor(c);
        if (!moving) {
            return;
        }
        move();
    }


    /**
     * 发射子弹
     * 有两种方式
     * 1 用参数的方式传进来  这种方式缺点是每次调用都需要new因此需要把策略设计成单例的
     * 2 使用成员变量，一般情况下不用成员变量 传参数会使得类结构复杂
     *   这里我们使用成员变量的方式来解决
     */
    public void fire() {
        fireStrategy.fire(this);
    }

    /**
     * 坦克消亡
     */
    @Override
    public void die() {
        this.living = false;
    }
}
