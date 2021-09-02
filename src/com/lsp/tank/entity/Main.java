package com.lsp.tank.entity;

import music.MusicPlayThreadPool;
import music.War;

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

        // 背景音乐 使用线程池来播放音乐
        MusicPlayThreadPool.playMusic(new War());
        while (true){
            Thread.sleep(25);
            tf.repaint();// 会调用paint方法
        }

    }

}
