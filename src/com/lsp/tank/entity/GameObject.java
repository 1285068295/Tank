package com.lsp.tank.entity;

import java.awt.*;
import java.util.UUID;

/**
 * @author ：Lisp
 * @date： 2021/8/26
 * @version: V1.0
 * @slogan:
 * @description :游戏物体类 顶层设计
 */
public abstract class GameObject {

    /**
     * 游戏物体坐标
     */
    public int x, y;

    /**
     * 移动物体的上一步的位置
     */
    protected int oldX, oldY;

    /**
     * 方向
     */
    public Dir dir = Dir.DOWN;


    /**
     * 获取游戏物体类别
     *
     * @return 游戏物体类别
     */
    public abstract GameObjectType getGameObjectType();

    /**
     * 获取游戏物体图形
     * @return 游戏物体图形
     */
    public abstract Rectangle getRect();

    /**
     * 绘制页面图形
     * @param g 画笔
     */
    public abstract void paint(Graphics g);

    /**
     * 移动物体的回退上一步
     */
    public abstract void back();

    /**
     *  物体的类别
     * @return
     */
    public abstract Group getGroup();

    /**
     * 物体的id
     * @return id
     */
    public abstract UUID getId();

    /**
     * 是否在移动
     * @return 是否在移动
     */
    public abstract boolean isMoving();

    /**
     * 游戏物体的名称
     * @return 游戏物体的名称
     */
    public abstract String getName();

    /**
     * 开火
     */
    public abstract void fire();

    /**
     * 设置物体移动属性
     * @param b 是否移动 true false
     */
    public abstract void setMoving(boolean b);

    /**
     * 设置物体的方向
     * @param dir 移动方向
     */
    public abstract void setDir(Dir dir);

    /**
     * 获取物体的方向
     * @return 物体的方向
     */
    public abstract Dir getDir();

    /**
     * 获取物体坐标x
     * @return 物体坐标x
     */
    public abstract int getX();

    /**
     * 获取物体坐标y
     * @return 物体坐标y
     */
    public abstract int getY();

    /**
     * 设置物体坐标x
     * @param x 坐标x
     */
    public abstract void setX(int x);
    /**
     * 设置物体坐标y
     * @param y 坐标y
     */
    public abstract void setY(int y);

    /**
     * 获取tankId
     * @return 坦克id
     */
    public abstract UUID getTankId();

}
