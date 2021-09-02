package com.lsp.tank.factory.abstractfactory;

import com.lsp.tank.entity.Dir;
import com.lsp.tank.entity.GameObject;
import com.lsp.tank.entity.Group;
import com.lsp.tank.entity.abstractEntity.BaseBullet;
import com.lsp.tank.entity.abstractEntity.BaseExplode;
import com.lsp.tank.entity.abstractEntity.BaseTank;

import java.awt.*;
import java.util.UUID;

/**
 * @author ：Lisp
 * @date： 2021/8/23
 * @version: V1.0
 * @slogan:
 * @description :父类的抽象工厂要尽可能的小，这样子类需要的实现就会少
 */
public abstract class GameFactory {

    /**
     *  创建己方坦克
     * @param id
     * @param x
     * @param y
     * @param dir
     * @param speed
     * @return
     */
    public abstract BaseTank createSelfTank(UUID id, int x, int y, Dir dir, int speed);

    /**
     *
     * 创建敌方坦克
     * @param id
     * @param x
     * @param y
     * @param dir
     * @param speed
     * @return
     */
    public abstract BaseTank createEnemyTank(UUID id, int x, int y, Dir dir, int speed);

    /**
     * 创建子弹
     * @param id
     * @param tankId
     * @param x
     * @param y
     * @param dir
     * @param group
     * @return
     */
    public abstract BaseBullet createBullet(UUID id, UUID tankId, int x, int y, Dir dir, Group group);

    /**
     * 创建爆炸
     * @param tankRect
     * @return
     */
    public abstract BaseExplode createExplode(Rectangle tankRect);

    /**
     * 创建墙
     * @param x
     * @param y
     * @param width
     * @param height
     * @return
     */
    public abstract GameObject createWall(int x, int y, int width, int height);

}
