package com.lsp.tank.collider;

import com.lsp.tank.entity.GameObject;

/**
 * @author ：Lisp
 * @date： 2021/8/26
 * @version: V1.0
 * @slogan:
 * @description :碰撞器：完成两个游戏物体间的碰撞检测
 */
public interface Collider {

    /**
     * 碰撞检测
     * @param o1 游戏物体
     * @param o2 游戏物体
     * @return 是否发生了碰撞
     */
    boolean collide(GameObject o1, GameObject o2);
}
