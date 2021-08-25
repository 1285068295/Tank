package com.lsp.tank.entity;

/**
 * @author ：Lisp
 * @date： 2021/8/19
 * @version: V1.0
 * @slogan:
 * @description :
 *
 * 游戏的主入口文件
 *
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        // 窗口类
        TankFrame tf = new TankFrame();

        // 创建敌人坦克
        for (int i = 0; i <1; i++) {
            tf.tanks.add(tf.gameFactory.createTank(100 + i * 100, 200, Dir.DOWN, Group.BAD, tf));
        }


        // 背景音乐  需要开启新的线程  因为loop是死循环
//        new Thread(()->{
//                Audio a = new Audio("audio/war1.wav");
//        a.loop();
//        }).start();



        while (true){
            Thread.sleep(50);
            tf.repaint();// 会调用paint方法
        }



    }

}
