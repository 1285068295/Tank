package com.lsp.tank;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ：Lisp
 * @date： 2021/8/19
 * @version: V1.0
 * @slogan:
 * @description :
 */
public class TankFrame extends Frame {


    /** 创建一个单人的坦克 */
    Tank myTank = new Tank(200, 400, Dir.RIGHT, Group.GOOD, this);
    /** 创建坦克子弹容器 */
    List<Bullet> bullets = new ArrayList<>();

    /** 创建敌人坦克容器 */
    List<Tank> tanks = new ArrayList<>();

    /** 游戏界面的大小*/
    static final int GAME_WIDTH = 1080, GAME_HEIGHT = 960;


    /**
     * 用来存储按下的方向四个键，当同时按下多个键时，以最后一次的按键为主
     * 每松开一个键就从栈中弹出一个数据，所以栈中最多存4个按键数据
     *
     * 注意 不能用stack来存储  释放按键时是无序释放的
     * →↓←
     */
    LinkedList<Dir> moveDir = new LinkedList<>();


    public TankFrame() {
        setSize(GAME_WIDTH, GAME_HEIGHT);
        setResizable(false);
        setTitle("tank war");
        setVisible(true);
        // 键盘监听处理
        this.addKeyListener(new MyKeyListener());

        // 监听器 监听窗口关闭  从控制台中断程序
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }


    Image offScreenImage = null;

    /**
     * 双缓冲解决闪烁问题
     * 先把画面画到内存里面，画完后直接从内存中输出到屏幕上
     * update 方法在paint方法之前进行的调用的
     */
    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }


    /**
     * 会清空页面 重新画图
     * while死循环执行repaint方法会一直调用paint的方法
     * @param g 画笔
     */
    @Override
    public void paint(Graphics g) {

        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("子弹数量为:" + bullets.size(), 10,60);
        g.setColor(c);

        myTank.paint(g);
        // 所有的子弹都一样  使用普通循环删除处理即可
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }

        // 画出敌人坦克
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }


        // 检测坦克与子弹是否碰撞了  碰撞后要移除子弹和坦克
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                // 检测子弹是否与坦克碰撞了
                bullets.get(i).collideWith(tanks.get(j));
            }
        }

    }


    /**
     * 这个类只提供TankFrame使用 所以定义为内部类
     * 键盘监听事件
     * 方向+速度决定坦克往哪个位置走
     * 根据四个状态来确定坦克的方向
     *
     *  为了解决同时按下多个按键时，以最后一次的按键为主要方向移动，
     *  需要注意的是 松开按键的顺序是无序的  所以不能用stack来弹出数据
     */
    class MyKeyListener extends KeyAdapter{

        /**
         * 按着键盘不松手时会产生多个key，但是同一个key我们应该只加入一次
         *
         * 只有上下左右四个按键处理方向
         */
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    if (!moveDir.contains(Dir.LEFT)) {
                        moveDir.addLast(Dir.LEFT);
                    }
                    myTank.setMoving(true);
                    setMainTankDir();
                    break;
                case KeyEvent.VK_RIGHT:
                    if (!moveDir.contains(Dir.RIGHT)) {
                        moveDir.addLast(Dir.RIGHT);
                    }
                    myTank.setMoving(true);
                    setMainTankDir();
                    break;
                case KeyEvent.VK_UP:
                    if (!moveDir.contains(Dir.UP)) {
                        moveDir.addLast(Dir.UP);
                    }
                    myTank.setMoving(true);
                    setMainTankDir();
                    break;
                case KeyEvent.VK_DOWN:
                    if (!moveDir.contains(Dir.DOWN)) {
                        moveDir.addLast(Dir.DOWN);
                    }
                    myTank.setMoving(true);
                    setMainTankDir();
                    break;
                default:
                    break;
            }

        }
        /**
         * 松开键盘时必须要恢复为false
         * @param e
         */
        @Override
        public void keyReleased(KeyEvent e) {
            // 松开键盘时不移动  只要有一个按键  坦克就应该处于移动状态
            // 必须判断四个键盘  因为存在按下两个键的情况 松开一个不能直接设置为false
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    moveDir.remove(Dir.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    moveDir.remove(Dir.RIGHT);
                    break;
                case KeyEvent.VK_UP:
                    moveDir.remove(Dir.UP);
                    break;
                case KeyEvent.VK_DOWN:
                    moveDir.remove(Dir.DOWN);
                    break;
                // 抬起ctrl发射一颗子弹
                case KeyEvent.VK_CONTROL:
                    myTank.fire();
                    break;
                default:
                    break;
            }
            if(moveDir.isEmpty()){
                // 没有按键的时候不再移动坦克
                myTank.setMoving(false);
            }else{
                // 实际上这里是没有修改方向的  因为都是false不会改变dir的值
                // 也就是说只有在按下键时 修改了dir的值改变了坦克的移动方向
                setMainTankDir();
            }
        }


        /**
         * 改变坦克的方向
         */
        private void setMainTankDir() {
            if(moveDir.isEmpty()){
                return;
            }
            Dir dir =  moveDir.getLast();
            if (dir.equals(Dir.LEFT)) {
                myTank.setDir(Dir.LEFT);
            } else if (dir.equals(Dir.RIGHT)) {
                myTank.setDir(Dir.RIGHT);
            } else if (dir.equals(Dir.UP)) {
                myTank.setDir(Dir.UP);
            } else if (dir.equals(Dir.DOWN)) {
                myTank.setDir(Dir.DOWN);
            }
        }
    }
}
