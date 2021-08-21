package com.lsp.tank;

/**
 * @author ：Lisp
 * @date： 2021/8/19
 * @version: V1.0
 * @slogan:
 * @description :
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        // 窗口类
        TankFrame tf = new TankFrame();

        for (int i = 0; i < 5; i++) {

            tf.tanks.add(new Tank(100+i*100 ,200, Dir.DOWN,tf));
        }



        while (true){
            Thread.sleep(50);
            tf.repaint();// 会调用paint方法
        }
    }

}
