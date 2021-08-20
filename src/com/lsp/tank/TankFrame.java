package com.lsp.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author ：Lisp
 * @date： 2021/8/19
 * @version: V1.0
 * @slogan:
 * @description :
 */
public class TankFrame extends Frame {

    /**
     * 坐标轴左上角为原点 向下向右为正
     */
    int x = 200, y = 200;
    Dir dir = Dir.LEFT;
    /** 坦克速度*/
    final int SPEED = 10;


    public TankFrame() {
        setSize(800, 600);
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

    /**
     * 会清空页面 重新画图
     * while死循环执行repaint方法会一直调用paint的方法
     *
     *
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        // 画出一个矩形 向右x变大  向下y变大
        g.fillRect(x, y, 50, 50);
        switch (dir){
            case LEFT:
                x-=SPEED;
                break;
            case RIGHT:
                x+=SPEED;
                break;
            case UP:
                y-=SPEED;
                break;
            case DOWN:
                y+=SPEED;
                break;
        }

    }


    /**
     * 这个类只提供TankFrame使用 所以定义为内部类
     * 键盘监听事件
     * 方向+速度决定坦克往哪个位置走
     * 根据四个状态来确定坦克的方向
     *
     *
     */
    class MyKeyListener extends KeyAdapter{
        // 根据按键来确定坦克的方向
        boolean bL = false;
        boolean bR = false;
        boolean bU = false;
        Boolean bD = false;

        /**
         * 按下键盘时 设定方向
         * @param e
         */
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = true;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = true;
                    break;
                case KeyEvent.VK_UP:
                    bU = true;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = true;
                    break;
                default:
                    break;
            }

            setMainTankDir();
        }
        /**
         * 松开键盘时 设定方向
         * @param e
         */
        @Override
        public void keyReleased(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    bL = false;
                    break;
                case KeyEvent.VK_RIGHT:
                    bR = false;
                    break;
                case KeyEvent.VK_UP:
                    bU = false;
                    break;
                case KeyEvent.VK_DOWN:
                    bD = false;
                    break;
                default:
                    break;

            }
            // 实际上这里是没有修改方向的  因为都是false不会改变dir的值
            // 也就是说只有在按下键时 修改了dir的值改变了坦克的移动方向
            setMainTankDir();
        }


        /**
         * 改变坦克的方向
         */
        private void setMainTankDir() {
            if(bL){ dir = Dir.LEFT;}
            if(bR){ dir = Dir.RIGHT;}
            if(bU){ dir = Dir.UP;}
            if(bD){ dir = Dir.DOWN;}

        }
    }
}
