package com.lsp.tank.entity;

import java.awt.*;
import java.util.Random;
import java.util.UUID;

/**
 * @author ：Lisp
 * @date： 2021/8/21
 * @version: V1.0
 * @slogan:
 * @description :敌方坦克
 */
public class DefaultEnemyTank extends Tank {
    /**
     * 获取随机值概率使用
     */
    public static final int r = 95;
    /**
     * 获取随机值概率使用
     */
    public static final int bound = 100;

    private Random random = new Random();

    public DefaultEnemyTank(UUID id, int x, int y, Dir dir, int speed) {
        super(id, x, y, dir, speed, Group.ENEMY);
    }

    /**
     * 敌方坦克移动的过程中会随机变向和开火
     */
    @Override
    protected void move() {
        super.move();
        // %5的机会改变方向
        if (random.nextInt(bound) > r) {
            dir = Dir.values()[random.nextInt(4)];
        }
        // %5的机会开火
        if (random.nextInt(bound) > r) {
            fire();
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(curTankImage, x, y, null);
        move();
    }
}
