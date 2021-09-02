package com.lsp.tank.entity;

import java.awt.*;
import java.util.Random;
import java.util.UUID;

/**
 * @author ：Lisp
 * @date： 2021/8/23
 * @version: V1.0
 * @slogan:
 * @Description 方形风格坦克
 */
public class RectEnemyTank extends Tank {
    private int width = 60, height = 60;
    private Random random = new Random();

    public RectEnemyTank(UUID id, int x, int y, Dir dir, int speed) {
        super(id, x, y, dir, speed, Group.ENEMY);
    }

    /**
     * 敌方坦克移动的过程中会随机变向和开火
     */
    @Override
    protected void move() {
        super.move();
        // %5的机会改变方向
        if (random.nextInt(100) > 95) {
            dir = Dir.values()[random.nextInt(4)];
        }
        // %5的机会开火
        if (random.nextInt(100) > 95) {
            fire();
        }
    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.BLUE);
        g.fillRect(x, y, width, height);
        move();
    }
}
