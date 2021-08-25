package com.lsp.tank.entity.abstractFactory;

import com.lsp.tank.entity.Dir;
import com.lsp.tank.entity.Group;
import com.lsp.tank.entity.TankFrame;

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

    /**
     * 默认是敌人的坦克
     */
    private Group group = Group.BAD;
    /**
     * 碰撞检测使用  只需要用一个就行  减少内存占用
     * 每次移动后更行rect的坐标
     */
    public Rectangle rect = new Rectangle();

    /**
     * 坦克的位置坐标
     */
    private int x, y;

    /**
     * 坦克的朝向
     */
    private Dir dir;

    /**
     * 游戏窗口引用
     */
    private TankFrame tf;


    public abstract Group getGroup();
    public abstract void setGroup(Group group);

    public abstract int getX();
    public abstract void setX(int x);

    public abstract int getY();
    public abstract void setY(int y);


    public abstract Dir getDir();
    public abstract void setDir(Dir dir);

    public abstract TankFrame getTf();

    public abstract void setTf(TankFrame tf);

    public abstract void die();
    public abstract void paint(Graphics graphics);

}
