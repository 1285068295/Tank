package com.lsp.tank;

import java.awt.*;

/**
 * @author ：Lisp
 * @date： 2021/8/22
 * @version: V1.0
 * @slogan:
 * @description :
 */
public class Explode {


    /**
     * 爆炸的大小 图片是 60*60
     */
    public static int WIDTH = ResourceMgr.explodes[0].getWidth();
    public static int HEIGHT = ResourceMgr.explodes[0].getHeight();


    /**
     * 爆炸显示到了第几张图片
     */
    private int step;

    /**
     * 爆炸的位置坐标
     */
    private int x, y;

    /**
     * 游戏窗口引用
     */
    private TankFrame tf;

    public Explode(int x, int y, TankFrame tf) {
        this.x = x;
        this.y = y;
        this.tf = tf;
        // 播放爆炸声音
        // 如果再主线程进行加载播放声音 会造成页面卡顿现象
        new Thread(()->new Audio("audio/explode.wav").play()).start();

    }

    /**
     * 画出爆炸图片
     */
    public void paint(Graphics g) {
        g.drawImage(ResourceMgr.explodes[step++], this.x, this.y, null);
        if (step == ResourceMgr.explodes.length) {
            step = 0;
        }

    }

}
