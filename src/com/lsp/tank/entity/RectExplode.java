package com.lsp.tank.entity;

import com.lsp.tank.entity.Audio;
import com.lsp.tank.entity.GameModel;
import com.lsp.tank.entity.abstractEntity.BaseExplode;

import java.awt.*;

/**
 * @author ：Lisp
 * @date： 2021/8/23
 * @version: V1.0
 * @slogan:
 * @description :方形的爆炸效果
 */
public class RectExplode extends BaseExplode {

    public RectExplode(Rectangle rectangle) {
        super(rectangle);
        // 播放爆炸声音
        // 如果再主线程进行加载播放声音 会造成页面卡顿现象 TODO
        new Thread(()->new Audio("audio/explode.wav").play()).start();
    }

    @Override
    public Rectangle getRect() {
        return rect;
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
            GameModel.INSTANCE.gameObjects.remove(this);
        }
    }

}
