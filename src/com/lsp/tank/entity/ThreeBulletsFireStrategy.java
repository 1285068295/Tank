package com.lsp.tank.entity;

import com.lsp.tank.entity.abstractFactory.BaseTank;

/**
 * @author ：Lisp
 * @date： 2021/8/22
 * @version: V1.0
 * @slogan:
 * @description :同一方向发射四颗炮弹
 */
public class ThreeBulletsFireStrategy implements FireStrategy {

    @Override
    public void fire(BaseTank t) {
        // 子弹打出的初始位置为坦克的正中心
        // 需要计算出子弹图片左上角的位置所以要减去图片一半长宽
            int bX = t.getX();
            int bY = t.getY();
            switch (t.getDir()) {
                case LEFT:
                    // 了让子弹贴住炮筒
                    bX = bX - Bullet.WIDTH;
                    bY = bY + Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
                    break;
                case RIGHT:
                    bX += Tank.WIDTH;
                    bY += Tank.HEIGHT / 2 - Bullet.HEIGHT / 2;
                    break;
                case UP:
                    bX = bX + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
                    bY = bY - Bullet.HEIGHT;
                    break;
                case DOWN:
                    bX = bX + Tank.WIDTH / 2 - Bullet.WIDTH / 2;
                    bY = bY + Tank.HEIGHT;
                    break;
                default:
                    break;
            }
            // 坦克创建子弹时  需要把敌我分类传入进去
            new Bullet(bX, bY, t.getDir(), t.getGroup(), t.getTf());
            if(t.getGroup() == Group.GOOD){
                // 我的坦克发射炮弹要发出音乐
                new Thread(()->new Audio("audio/tank_fire.wav").play()).start();
            }
    }
}
