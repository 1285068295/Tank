package com.lsp.tank.entity.abstractFactory;

import com.lsp.tank.entity.Explode;
import com.lsp.tank.entity.GameModel;
import com.lsp.tank.entity.ResourceMgr;
import com.lsp.tank.entity.TankFrame;

import java.awt.*;

/**
 * @author ：Lisp
 * @date： 2021/8/23
 * @version: V1.0
 * @slogan:
 * @description :
 */
public class RectExplode extends Explode {

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

    /**
     * 注意父类必须要有空参构造方法
     */
    public RectExplode(int x, int y, GameModel gameModel) {
        this.x = x;
        this.y = y;
        this.gameModel = gameModel;
        // 播放爆炸声音
        // 如果再主线程进行加载播放声音 会造成页面卡顿现象
        // new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    /**
     * 画出爆炸方形的图片
     */
    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.RED);
        g.fillRect(x,y,10*step,10*step);
        step++;
        if (step >= 5) {
            this.gameModel.explodes.remove(this);
        }

    }
}
