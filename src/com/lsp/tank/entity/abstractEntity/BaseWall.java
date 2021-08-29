package com.lsp.tank.entity.abstractEntity;

import com.lsp.tank.entity.Dir;
import com.lsp.tank.entity.GameObject;
import com.lsp.tank.entity.GameObjectType;
import com.lsp.tank.entity.Group;

import java.awt.*;
import java.util.UUID;

/**
 * @author ：Lisp
 * @date： 2021/8/28
 * @version: V1.0
 * @slogan:
 * @description :墙
 */
public abstract class BaseWall extends GameObject {

    /**
     * protected 修饰为了子类继承使用
     * 注意父类中没有这个属性
     */
    protected GameObjectType gameObjectType = GameObjectType.WALL;

    /**
     * 用来判断碰撞使用的
     */
    protected Rectangle rect;

    protected int width, height;


    public BaseWall(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        rect = new Rectangle(x, y, width, height);
    }

    @Override
    public GameObjectType getGameObjectType() {
        return gameObjectType;
    }


    /**
     * 碰撞后回到上一次的位置
     */
    @Override
    public void back() {}

    @Override
    public Rectangle getRect() {
        return rect;
    }

    @Override
    public Group getGroup() {
        return null;
    }

    @Override
    public UUID getId() {
        return null;
    }

    @Override
    public void fire() {}

    @Override
    public boolean isMoving() {
        return false;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setMoving(boolean b) {}

    @Override
    public void setDir(Dir dir) {
    }

    @Override
    public Dir getDir() {
        return dir;
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
    public UUID getTankId() {
        return null;
    }



}
