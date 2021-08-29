package com.lsp.tank.collider;

import com.lsp.tank.entity.GameModel;
import com.lsp.tank.entity.GameObject;
import com.lsp.tank.entity.GameObjectType;

/**
 * @author ：Lisp
 * @date： 2021/8/28
 * @version: V1.0
 * @slogan:
 * @description :子弹和墙的碰撞处理
 */
public class BulletWallCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1.getGameObjectType() == GameObjectType.BULLET && o2.getGameObjectType() == GameObjectType.WALL) {
            if(o1.getRect().intersects(o2.getRect())){
                GameModel.INSTANCE.remove(o1);
                return true;
            }
        } else if (o1.getGameObjectType() == GameObjectType.WALL && o2.getGameObjectType() == GameObjectType.BULLET) {
            collide(o2, o1);
        }
        return false;
    }
}
