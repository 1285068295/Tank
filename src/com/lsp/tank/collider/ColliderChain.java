package com.lsp.tank.collider;

import com.lsp.tank.entity.GameObject;
import com.lsp.tank.mgr.PropertyMgr;

import java.util.List;

/**
 * @author ：Lisp
 * @date： 2021/8/27
 * @version: V1.0
 * @slogan:
 * @description :ColliderChain实现Collider 是因为如存在两个Chain时
 * 只需要调用ColliderChain1.add(ColliderChain2) 就能把两个链表合并
 *
 * 用到了责任连模式
 */
public class ColliderChain implements Collider {

    private List<Collider> colliders;

    public ColliderChain() {
        colliders = PropertyMgr.getColliders();
    }

    public void add(Collider c){
        colliders.add(c);
    }

    @Override
    public boolean collide(GameObject o1, GameObject o2) {

        for (Collider collider : colliders) {
            // 用所有的碰撞器进行碰撞
            // 对于if的判断说明 如果两个物体发生了碰撞 后面的碰撞检测无需在往下进行
            if(collider.collide(o1, o2)) {
                return true;
            }
        }
        return false;
    }
}
