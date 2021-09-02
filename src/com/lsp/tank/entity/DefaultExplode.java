package com.lsp.tank.entity;

import com.lsp.tank.entity.abstractEntity.BaseExplode;
import com.lsp.tank.mgr.ResourceMgr;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * @author ：Lisp
 * @date： 2021/8/22
 * @version: V1.0
 * @slogan:
 * @description :默认风格的爆炸动画
 */
public class DefaultExplode extends BaseExplode {

    public DefaultExplode(Rectangle rectangle) {
        super(rectangle);
        // 播放爆炸声音
        // 如果再主线程进行加载播放声音 会造成页面卡顿现象
        // MusicPlayThreadPool.playMusic(new TankExplode());
    }

    @Override
    public Rectangle getRect() {
        return rect;
    }

    /**
     * 画出爆炸图片
     */
    @Override
    public void paint(Graphics g) {
        // 根据爆炸坦克的x y 坐标计算得到爆炸图的x y 坐标
        BufferedImage explodeImage = ResourceMgr.explodes[step];
        int width = explodeImage.getWidth();
        int height = explodeImage.getHeight();
        x = tankRect.x + (tankRect.width - width) / 2;
        y = tankRect.y - (tankRect.height - height) / 2;
        rect.x = x;
        rect.y = y;
        rect.width = width;
        rect.height = height;
        g.drawImage(explodeImage, x, y, null);
        step++;
        // 播放到爆炸动画的最后一帧，就移除这个爆炸动画
        if (step == ResourceMgr.explodes.length) {
            GameModel.INSTANCE.gameObjects.remove(this);
        }
    }
}
