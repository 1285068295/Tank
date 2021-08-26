package com.lsp.tank.entity.abstractFactory;

import com.lsp.tank.entity.Dir;
import com.lsp.tank.entity.GameModel;
import com.lsp.tank.entity.Group;

import java.awt.*;

/**
 * @author ：Lisp
 * @date： 2021/8/23
 * @version: V1.0
 * @slogan:
 * @description :抽象坦克 需要抽取的方法较多
 * 需要抽取的属性也是很多
 * 在这个父类的抽象的坦克中 可以设置一些public的属性
 */
public abstract class BaseTank {

    public GameModel gameModel;

    /**
     * 默认是敌人的坦克
     */
    public Group group = Group.BAD;
    /**
     * 碰撞检测使用  只需要用一个就行  减少内存占用
     * 每次移动后更行rect的坐标
     */
    public Rectangle rect = new Rectangle();

    /**
     * 坦克的位置坐标
     */
    public int x, y;

    /**
     * 坦克的朝向
     */
    public Dir dir;

    /**
     * 是否移动坦克 只有在按下上下左右键时才移动坦克
     */
    private boolean moving = true;


    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public abstract void die();
    public abstract void paint(Graphics graphics);
    public abstract void fire();
}
