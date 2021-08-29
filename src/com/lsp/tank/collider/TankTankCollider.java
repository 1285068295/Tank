package com.lsp.tank.collider;

import com.lsp.tank.entity.GameObject;
import com.lsp.tank.entity.GameObjectType;

/**
 * @author ：Lisp
 * @date： 2021/8/26
 * @version: V1.0
 * @slogan:
 * @description :坦克和坦克的碰撞器
 */
public class TankTankCollider implements Collider {

    @Override
    public boolean collide(GameObject o1, GameObject o2) {
        if (o1.getGameObjectType() == GameObjectType.TANK && o2.getGameObjectType() == GameObjectType.TANK) {
            // 碰撞时 把方向处理成反方向
            if(o1.getRect().intersects(o2.getRect())){
                o1.back();
                o2.back();
                return true;
            }
        }
        return false;
    }
}
