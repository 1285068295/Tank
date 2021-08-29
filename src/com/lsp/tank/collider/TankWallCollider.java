package com.lsp.tank.collider;

import com.lsp.tank.entity.GameObject;
import com.lsp.tank.entity.GameObjectType;

/**
 * @author ：Lisp
 * @date： 2021/8/28
 * @version: V1.0
 * @slogan:
 * @description :坦克和墙的碰撞器
 */
public class TankWallCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1.getGameObjectType() == GameObjectType.TANK && o2.getGameObjectType() == GameObjectType.WALL) {
            // 碰撞时 回退
            if(o1.getRect().intersects(o2.getRect())){
                o1.back();
                return true;
            }
        }else if (o1.getGameObjectType() == GameObjectType.WALL && o2.getGameObjectType() == GameObjectType.TANK) {
            collide(o2,o1);
        }
        return false;
    }

}
