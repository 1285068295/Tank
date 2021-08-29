package com.lsp.tank.entity.abstractEntity;

import com.lsp.tank.entity.Dir;
import com.lsp.tank.entity.GameObject;
import com.lsp.tank.entity.GameObjectType;
import com.lsp.tank.entity.Group;

import java.awt.*;
import java.util.UUID;

/**
 * @author ：Lisp
 * @date： 2021/8/23
 * @version: V1.0
 * @slogan:
 * @description :这里的方法是伴随着提取BaseBullet的过程中创建的
 *              需要抽取哪个方法的时候再进行抽取
 */
public abstract class BaseBullet extends GameObject {
    /**
     * 爆炸坦克的尺寸和坐标
     */
    protected Rectangle tankRect;
    protected Group group;
    protected Rectangle rect;
    protected int speed = 6;
    protected int step = 0;
    protected GameObjectType gameObjectType = GameObjectType.BULLET;
    protected UUID id;
    /**
     * 用来标识是哪个坦克发射的
     */
    protected UUID tankId;

    /**
     * 绘制爆炸效果
     */
    @Override
    public abstract void paint(Graphics g);

    /**
     * 获取爆炸动画到了第几张图片
     * @return
     */
    public int getStep() {
        return step;
    }

    @Override
    public boolean isMoving() {
        return true;
    }

    @Override
    public Rectangle getRect() {
        return rect;
    }

    @Override
    public Group getGroup() {
        return group;
    }

    @Override
    public int getX() {
        return x;
    }

    @Override
    public int getY() {
        return y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    @Override
    public void setY(int y) {
        this.y = y;
    }

    @Override
    public GameObjectType getGameObjectType() {
        return gameObjectType;
    }

    @Override
    public void back() {}

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void fire() {}

    @Override
    public void setMoving(boolean b) {}

    @Override
    public void setDir(Dir dir) {}

    @Override
    public Dir getDir() {
        return dir;
    }


    /**
     * 判断子弹的状态
     * @return
     */
    public abstract boolean isLiving();

    /**
     * 获取坦克id
     * @return
     */
    @Override
    public abstract UUID getTankId();

}
