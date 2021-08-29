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
 * @description :抽象坦克 需要抽取的方法较多
 * 需要抽取的属性也是很多
 * 在这个父类的抽象的坦克中 可以设置一些public的属性
 */
public abstract class BaseTank extends GameObject {
    /**
     * 游戏物体类别
     */
    protected GameObjectType gameObjectType = GameObjectType.TANK;

    /**
     * 是否移动坦克 只有在按下上下左右键时才移动坦克
     */
    public boolean moving = true;


    public String name;

    public UUID id;

    @Override
    public Dir getDir() {
        return dir;
    }

    @Override
    public void setMoving(boolean moving){
        this.moving = moving;
    }

    @Override
    public boolean isMoving() {
        return moving;
    }

    /**
     * 画出坦克图形
     * @param graphics 画笔
     */
    @Override
    public abstract void paint(Graphics graphics);

    /**
     * 坦克开火方式
     */
    @Override
    public abstract void fire();

    /**
     *  获取坦克图片坐标和宽高
     */
    @Override
    public abstract Rectangle getRect();

    @Override
    public abstract Group getGroup();

    /**
     * 坦克在碰撞后回到上一次的位置
     */
    @Override
    public void back() {
        x = oldX;
        y = oldY;
    }

    @Override
    public GameObjectType getGameObjectType() {
        return gameObjectType;
    }

    @Override
    public UUID getId() {
        return id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public int getX() { return x; }

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
