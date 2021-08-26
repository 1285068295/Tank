package com.lsp.tank.entity.abstractFactory;

import com.lsp.tank.entity.*;

/**
 * @author ：Lisp
 * @date： 2021/8/23
 * @version: V1.0
 * @slogan:
 * @description :默认的生产坦克、子弹、爆炸的工厂
 */
public class DefaultFactory extends GameFactory {
    @Override
    public BaseTank createTank(int x, int y, Dir dir, Group group, GameModel gameModel) {
        return new Tank(x, y, dir, group, gameModel);
    }

    @Override
    public BaseBullet createBullet(int x, int y, Dir dir, Group group, GameModel gameModel) {
        return new Bullet(x, y, dir, group, gameModel);
    }

    @Override
    public BaseExplode createExplode(int x, int y, GameModel gameModel) {
        return new Explode(x, y,gameModel);
    }
}
