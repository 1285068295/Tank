package com.lsp.tank.factory;

import com.lsp.tank.entity.*;
import com.lsp.tank.entity.abstractEntity.BaseBullet;
import com.lsp.tank.entity.abstractEntity.BaseExplode;
import com.lsp.tank.entity.abstractEntity.BaseTank;
import com.lsp.tank.factory.abstractFactory.GameFactory;

import java.awt.*;
import java.util.UUID;

/**
 * @author ：Lisp
 * @date： 2021/8/23
 * @version: V1.0
 * @slogan:
 * @description :默认的生产坦克、子弹、爆炸的工厂
 */
public class DefaultFactory extends GameFactory {

    @Override
    public BaseTank createSelfTank(UUID id, int x, int y, Dir dir, int speed) {
        return new DefaultSelfTank(id, x, y, dir, speed);
    }

    @Override
    public BaseTank createEnemyTank(UUID id, int x, int y, Dir dir, int speed) {
        return new DefaultEnemyTank(id, x, y, dir, speed);
    }

    @Override
    public BaseBullet createBullet(UUID id, UUID tankId, int x, int y, Dir dir, Group group) {
        return new DefaultBullet(id, tankId, x, y, dir, group);
    }

    @Override
    public BaseExplode createExplode(Rectangle tankRect) {
        return new DefaultExplode(tankRect);
    }

    @Override
    public GameObject createWall(int x, int y, int width, int height) {
        return new DefaultWall(x, y, width, height);
    }
}
