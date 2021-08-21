package com.lsp.tank;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.LinkedList;

/**
 * @author ：Lisp
 * @date： 2021/8/19
 * @version: V1.0
 * @slogan:
 * @description :
 */
public class TankFrame extends Frame {


    // 创建一个单人的坦克
    Tank myTank = new Tank(20,20,Dir.RIGHT);
    /**
     * 用来存储按下的方向四个键，当同时按下多个键时，以最后一次的按键为主
     * 每松开一个键就从栈中弹出一个数据，所以栈中最多存4个按键数据
     *
     * 注意 不能用stack来存储  释放按键时是无序释放的
     * →↓←
     */
    LinkedList<Dir> moveDir = new LinkedList<>();


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
     * @param g
     */
    @Override
    public void paint(Graphics g) {
        myTank.paint(g);
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
         */
        @Override
        public void keyPressed(KeyEvent e) {
            int key = e.getKeyCode();
            switch (key) {
                case KeyEvent.VK_LEFT:
                    if (!moveDir.contains(Dir.LEFT)) {
                        moveDir.addLast(Dir.LEFT);
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (!moveDir.contains(Dir.RIGHT)) {
                        moveDir.addLast(Dir.RIGHT);
                    }
                    break;
                case KeyEvent.VK_UP:
                    if (!moveDir.contains(Dir.UP)) {
                        moveDir.addLast(Dir.UP);
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (!moveDir.contains(Dir.DOWN)) {
                        moveDir.addLast(Dir.DOWN);
                    }
                    break;
                default:
                    break;
            }
            myTank.setMoving(true);
            setMainTankDir();
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
