package com.lsp.tank.entity;

import com.lsp.tank.entity.abstractEntity.BaseWall;

import java.awt.*;

/**
 * @author ：Lisp
 * @date： 2021/8/28
 * @version: V1.0
 * @slogan:
 * @description :游戏中的墙
 */
public class DefaultWall extends BaseWall {

    public DefaultWall(int x, int y, int width, int height) {
        super(x, y, width, height);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(new Color(0xdba989));
        g.fillRect(this.x, this.y, this.width, this.height);
        g.setColor(c);
    }







}
