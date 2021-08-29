package com.lsp.tank.entity;

import java.awt.*;
import java.util.UUID;

/**
 * @author ：Lisp
 * @date： 2021/8/28
 * @version: V1.0
 * @slogan:
 * @description :默认风格的我方坦克
 */
public class DefaultSelfTank extends Tank {

    public DefaultSelfTank(UUID id, int x, int y, Dir dir, int speed) {
        super(id, x, y, dir, speed, Group.SELF);
        moving = false;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(curTankImage, x, y, null);
        move();
    }

}
