package com.lsp.tank.strategy;

import com.lsp.tank.entity.Dir;
import com.lsp.tank.entity.GameModel;
import com.lsp.tank.entity.Tank;
import com.lsp.tank.entity.abstractEntity.BaseBullet;
import com.lsp.tank.mgr.ResourceMgr;
import music.MusicPlayThreadPool;
import music.TankFire;

import java.util.UUID;

/**
 * @author ：Lisp
 * @date： 2021/8/22
 * @version: V1.0
 * @slogan:
 * @description :四个方向的开火模式
 */
public class FourDirectionsFireStrategy implements FireStrategy {


    @Override
    public void fire(Tank t) {
        int tankWidth = t.curTankImage.getWidth();
        int tankHeight = t.curTankImage.getHeight();
        for (Dir dir : Dir.values()) {
            // 必须放到for循环内部 每次都要重置bx by
            int bX = t.x;
            int bY = t.y;
            switch (dir) {
                case LEFT:
                    // 为了让子弹贴住炮筒
                    bX -= ResourceMgr.bulletL.getWidth();
                    bY += tankHeight / 2 - ResourceMgr.bulletL.getHeight() / 2;
                    break;
                case RIGHT:
                    bX += tankWidth;
                    bY += tankHeight / 2 - ResourceMgr.bulletR.getHeight() / 2;
                    break;
                case UP:
                    bX += tankWidth / 2 - ResourceMgr.bulletU.getWidth() / 2;
                    bY -= ResourceMgr.bulletU.getHeight();
                    break;
                case DOWN:
                    bX += tankWidth / 2 - ResourceMgr.bulletD.getWidth() / 2;
                    bY += tankHeight;
                    break;
                default:
                    break;
            }
            // 坦克创建子弹时  需要把敌我分类传入进去  注意不是坦克的朝向
            BaseBullet bullet = GameModel.INSTANCE.gameFactory.createBullet(UUID.randomUUID(), t.getId(), bX, bY, dir, t.group);
            GameModel.INSTANCE.add(bullet);

            // 背景音乐 使用线程池来播放音乐
             MusicPlayThreadPool.playMusic(new TankFire());
        }


    }
}
