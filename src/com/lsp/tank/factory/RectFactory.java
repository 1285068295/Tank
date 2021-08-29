package com.lsp.tank.factory;

import com.lsp.tank.entity.Dir;
import com.lsp.tank.entity.GameObject;
import com.lsp.tank.entity.Group;
import com.lsp.tank.entity.abstractEntity.BaseBullet;
import com.lsp.tank.entity.abstractEntity.BaseExplode;
import com.lsp.tank.entity.abstractEntity.BaseTank;
import com.lsp.tank.factory.abstractFactory.GameFactory;
import com.lsp.tank.entity.RectEnemyTank;
import com.lsp.tank.entity.RectSelfTank;

import java.awt.*;
import java.util.UUID;

/**
 * @author ：Lisp
 * @date： 2021/8/23
 * @version: V1.0
 * @slogan:
 * @Description 生产方形风格的坦克、子弹和爆炸效果的工厂类
 *              抽象工厂可以创建一套的产品
 */
public class RectFactory extends GameFactory {

    @Override
    public BaseTank createSelfTank(UUID id, int x, int y, Dir dir, int speed) {
        return new RectSelfTank(id, x, y, dir, speed);
    }

    @Override
    public BaseTank createEnemyTank(UUID id, int x, int y, Dir dir, int speed) {
        return new RectEnemyTank(id, x, y, dir, speed);
    }

    @Override
    public BaseBullet createBullet(UUID id, UUID tankId, int x, int y, Dir dir, Group group) {
        return null;
    }

    @Override
    public BaseExplode createExplode(Rectangle tankRect) {
        return null;
    }

    @Override
    public GameObject createWall(int x, int y, int width, int height) {
        return null;
    }


}
