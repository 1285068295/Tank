package com.lsp.tank;

/**
 * @author ：Lisp
 * @date： 2021/8/19
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description :
 */
public class Main {

    public static void main(String[] args) throws InterruptedException {
        // 窗口类
        TankFrame tf = new TankFrame();
        while (true){
            Thread.sleep(50);
            tf.repaint();// 会调用paint方法
        }
    }
}
