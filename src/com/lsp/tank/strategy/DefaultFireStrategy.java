package com.lsp.tank.strategy;

/**
 * @author ：Lisp
 * @date： 2021/8/28
 * @version: V1.0
 * @slogan:
 * @description :
 */


import com.lsp.tank.entity.*;
import com.lsp.tank.entity.abstractEntity.BaseBullet;
import com.lsp.tank.mgr.ResourceMgr;

import java.util.UUID;

/**
 * @author ：Lisp
 * @date： 2021/8/22
 * @version: V1.0
 * @slogan:
 * @description :默认的开火模式  根据坦克前进方向来发射子弹
 */
public class DefaultFireStrategy implements FireStrategy {

    @Override
    public void fire(Tank t) {
        // 参考 炮弹坐标计算示意图.jpg
        // 子弹打出的初始位置为坦克的正中心
        // 需要计算出子弹图片左上角的位置所以要减去图片一半长宽
        int bX = t.x;
        int bY = t.y;
        int tankWidth = t.curTankImage.getWidth();
        int tankHeight = t.curTankImage.getHeight();
        switch (t.getDir()) {
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

        BaseBullet bullet = GameModel.INSTANCE.gameFactory.createBullet(UUID.randomUUID(), t.getId(), bX, bY, t.dir, t.group);
        GameModel.INSTANCE.add(bullet);

        if(t.getGroup() == Group.SELF){
            // 我的坦克发射炮弹要发出音乐 TODO
            new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
        }

    }
}