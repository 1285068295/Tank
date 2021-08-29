package com.lsp.tank.entity;

import com.lsp.tank.entity.abstractEntity.BaseTank;
import com.lsp.tank.mgr.PropertyMgr;
import com.lsp.tank.mgr.ResourceMgr;
import com.lsp.tank.strategy.FireStrategy;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.UUID;

/**
 * @author ：Lisp
 * @date： 2021/8/29
 * @version: V1.0
 * @slogan:
 * @description :坦克父类
 */
public abstract class Tank extends BaseTank {
    protected int speed = 10;

    /**
     * 碰撞检测使用  只需要new一个就行  减少内存占用
     * 每次移动后更新rect的坐标
     */
    protected Rectangle rect;
    protected FireStrategy fireStrategy;
    public Group group;

    /**
     * 当前坦克加载的图片
     */
    public BufferedImage curTankImage;

    public Tank(UUID id, int x, int y, Dir dir, int speed, Group group) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.speed = speed;
        this.group = group;
        initCurTankImage();
        this.rect = new Rectangle();
        updateRect();
        if (group == Group.SELF) {
            fireStrategy = PropertyMgr.getSelf_tank_fs();
        } else {
            fireStrategy = PropertyMgr.getEnemy_tank_fs();
        }
    }

    /**
     * 初始化坦克时的图片
     */
    private void initCurTankImage() {
        if (group == Group.SELF) {
            switch (dir) {
                case LEFT:
                    curTankImage = ResourceMgr.selfTankL;
                    break;
                case UP:
                    curTankImage = ResourceMgr.selfTankU;
                    break;
                case RIGHT:
                    curTankImage = ResourceMgr.selfTankR;
                    break;
                case DOWN:
                    curTankImage = ResourceMgr.selfTankD;
                    break;
                default:
                    break;
            }
        } else {
            switch (dir) {
                case LEFT:
                    curTankImage = ResourceMgr.enemyTankL;
                    break;
                case UP:
                    curTankImage = ResourceMgr.enemyTankU;
                    break;
                case RIGHT:
                    curTankImage = ResourceMgr.enemyTankR;
                    break;
                case DOWN:
                    curTankImage = ResourceMgr.enemyTankD;
                    break;
                default:
                    break;
            }

        }
    }

    private void updateRect() {
        rect.x = x;
        rect.y = y;
        rect.width = curTankImage.getWidth();
        rect.height = curTankImage.getHeight();
    }

    @Override
    public void setDir(Dir dir) {
        this.dir = dir;
    }

    @Override
    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    @Override
    public abstract void paint(Graphics g);

    /**
     * 坦克移动
     */
    protected void move() {
        oldX = x;
        oldY = y;

        if(!moving){
            return;
        }

        switch (dir) {
            case LEFT: {
                x -= speed;
                curTankImage = (group == Group.SELF ? ResourceMgr.selfTankL : ResourceMgr.enemyTankL);
                break;
            }
            case UP: {
                y -= speed;
                curTankImage = (group == Group.SELF ? ResourceMgr.selfTankU : ResourceMgr.enemyTankU);
                break;
            }
            case RIGHT: {
                x += speed;
                curTankImage = (group == Group.SELF ? ResourceMgr.selfTankR : ResourceMgr.enemyTankR);
                break;
            }
            case DOWN: {
                y += speed;
                curTankImage = (group == Group.SELF ? ResourceMgr.selfTankD : ResourceMgr.enemyTankD);
                break;
            }
            default:
                break;
        }
        // 添加边界检测：坦克不能走出游戏区域
        boundsCheck();

        updateRect();
    }

    /**
     * 边界检测：坦克不能走出游戏区域
     */
    private void boundsCheck() {
        if (x < 2) {
            x = 2;
        }
        if (y < 25) {
            y = 25;
        }
        if (x + curTankImage.getWidth() > GameModel.INSTANCE.gameWidth) {
            x = GameModel.INSTANCE.gameWidth - curTankImage.getWidth();
        }
        if (y + curTankImage.getHeight() > GameModel.INSTANCE.gameHeight) {
            y = GameModel.INSTANCE.gameHeight - curTankImage.getHeight();
        }
    }

    /**
     * 打出一颗子弹
     */
    @Override
    public void fire() {
        fireStrategy.fire(this);
    }

    /**
     * 获取坦克图片坐标和宽高
     * @return
     */
    @Override
    public Rectangle getRect() {
        return rect;
    }

    @Override
    public Group getGroup() {
        return group;
    }
}
