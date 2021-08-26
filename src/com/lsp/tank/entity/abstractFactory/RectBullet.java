package com.lsp.tank.entity.abstractFactory;

import com.lsp.tank.entity.*;

import java.awt.*;

/**
 * @author ：Lisp
 * @date： 2021/8/21
 * @version: V1.0
 * @slogan:
 * @description :方形的坦克子弹
 */
public class RectBullet extends BaseBullet {



    /**
     * 默认是敌人的子弹
     */
    private Group group = Group.BAD;
    /**
     * 子弹的速度
     * 注意需要于坦克的速度有差值 如果相同则同时移动时看不出来是否发射了子弹
     */
    private static final int SPEED = 15;
    /** 子弹为圆形 外切正方形的长和宽的大小*/
    public static int WIDTH = ResourceMgr.bulletU.getWidth();
    public static int HEIGHT = ResourceMgr.bulletU.getHeight();

    /**子弹是否还存活 默认存活的 */
    private boolean living = true;

    /**
     * 碰撞检测使用  只需要用一个就行  减少内存占用
     * 每次移动后更行rect的坐标
     */
    Rectangle rect = new Rectangle();

    /**
     * 子弹位置
     */
    private int x, y;
    /** 子弹方向*/
    private Dir dir;

    public RectBullet(int x, int y, Dir dir, Group group, GameModel gameModel) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.gameModel = gameModel;
        // 初始化用来检测碰撞的Rectangle
        rect.x = this.x;
        rect.y = this.y;
        rect.width = WIDTH;
        rect.height = HEIGHT;

        // 在创建的同时加入到tf的子弹即可中  就不需要在new 方法之外单独做bullets.add操作了
        gameModel.bullets.add(this);
    }

    @Override
    public void paint(Graphics g){
        if (!living) {
            gameModel.bullets.remove(this);
        }
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x,y,20,20);
        g.setColor(c);
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

        // 更新碰撞检测的矩形
        rect.x = this.x;
        rect.y = this.y;
        // 移动越界的时候需要删除子弹  页面不再显示
        if(x < 0 || y < 0 || x > TankFrame.GAME_WIDTH || y > TankFrame.GAME_HEIGHT){
            living = false;
        }

    }

    /**
     * 检测子弹是否与坦克发生了碰撞
     * 碰撞了的话需要从界面上移除坦克与子弹  同时创建爆炸图片
     * @param tank
     */
    @Override
    public void collideWith(BaseTank tank) {
        // 敌人的坦克与敌人的子弹不做碰撞检测
        if (this.group == tank.getGroup()) {return;}
         // intersects 横断;相交;交叉;横穿;贯穿
        /**
         * 踩坑点 父类和子类中都有rect属性时
         * 参数传入的实参为RectTank 在获取tank.rect时 获取的是父类的rect属性
         * 所以会已知不能发生碰撞
         */
        if(rect.intersects(tank.rect)){
            // 设置living属性为false 下次执行paint方法时 不再画图形
            this.die();
            tank.die();
            // 发生了碰撞 就创建一个新的爆炸
            int eX = tank.getX() + Tank.WIDTH / 2 - Explode.WIDTH / 2;
            int eY = tank.getY() + Tank.HEIGHT / 2 - Explode.HEIGHT / 2;
            gameModel.explodes.add(gameModel.gameFactory.createExplode(eX, eY, gameModel));
        }
    }

    /**
     * 子弹消亡
     */
    public void die() {
        this.living = false;
    }
}
