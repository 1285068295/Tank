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
