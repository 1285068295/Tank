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
 * @description :
 * 在抽取父类的时候 用到哪个就抽取哪个 不要一下子全抽出来
 */
public abstract class BaseExplode extends GameObject {
    /**
     * 爆炸坦克的尺寸和坐标
     */
    protected Rectangle tankRect;
    protected Rectangle rect;
    protected int step = 0;
    protected GameObjectType gameObjectType = GameObjectType.EXPLODE;

    @Override
    public GameObjectType getGameObjectType() {
        return gameObjectType;
    }

    /**
     * 注意这里的参数为爆炸坦克的矩形
     * @param tankRect
     */
    public BaseExplode(Rectangle tankRect) {
        this.tankRect = tankRect;
        rect = new Rectangle();
        // 线程池播放音乐
        // MusicPlayThreadPool.playMusic(new TankExplode());
    }

    /**
     * 画图爆炸效果图
     * @param g 画笔
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
    public Rectangle getRect(){
        return rect;
    }

    @Override
    public void back() {}

    @Override
    public Group getGroup() {
        return null;
    }

    @Override
    public UUID getId() {
        return null;
    }

    @Override
    public boolean isMoving() {
        return true;
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
