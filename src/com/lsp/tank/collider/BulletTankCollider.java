package com.lsp.tank.collider;

import com.lsp.tank.entity.*;

/**
 * @author ：Lisp
 * @date： 2021/8/26
 * @version: V1.0
 * @slogan:
 * @description :子弹和坦克的碰撞处理
 */
public class BulletTankCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1.getGameObjectType() == GameObjectType.BULLET && o2.getGameObjectType() == GameObjectType.TANK) {
            // 如果是己方坦克，无敌
            if (o2.getGroup() == Group.SELF) {
                return false;
            }

            // 敌人的坦克与敌人的子弹不做碰撞检测  intersects 横断;相交;交叉;横穿;贯穿
            if (o1.getGroup() == o2.getGroup()) {
                return false;
            }

            // 如果子弹和坦克碰撞到一起了
            if (o1.getRect().intersects(o2.getRect())) {
                // 移除子弹
                GameModel.INSTANCE.gameObjects.remove(o1);
                // 移除坦克
                GameModel.INSTANCE.gameObjects.remove(o2);
                // 添加一个爆炸动画
                GameModel.INSTANCE.gameObjects.add(GameModel.INSTANCE.gameFactory.createExplode(o2.getRect()));
                return true;
            }
            return true;
        } else if (o1.getGameObjectType() == GameObjectType.TANK && o2.getGameObjectType() == GameObjectType.BULLET) {
            collide(o2, o1);
        }
        return false;
    }
}
