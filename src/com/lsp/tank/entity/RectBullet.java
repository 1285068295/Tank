package com.lsp.tank.entity;

import com.lsp.tank.entity.abstractEntity.BaseBullet;

import java.awt.*;
import java.util.UUID;

/**
 * @author ：Lisp
 * @date： 2021/8/21
 * @version: V1.0
 * @slogan:
 * @description :方形的坦克子弹
 */
public class RectBullet extends BaseBullet {

    private int bulletWidth = 20, bulletHeight = 20;

    public RectBullet(UUID id, UUID tankId, int x, int y, Dir dir, Group group) {
        this.id = id;
        this.tankId = tankId;
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.rect = new Rectangle(x,y,bulletWidth,bulletHeight);
    }

    @Override
    public Rectangle getRect() {
        return rect;
    }

    @Override
    public void paint(Graphics g){
        if (!isLiving()) {
            GameModel.INSTANCE.remove(this);
        }
        Color c = g.getColor();
        g.setColor(Color.YELLOW);
        g.fillRect(x,y,20,20);
        g.setColor(c);
        // 移动子弹
        move();
    }

    @Override
    public boolean isLiving() {
        return !isOutOfScreen();
    }

    /**
     * 判断子弹是否飞出屏幕
     * 考虑了子弹的大小
     * @return
     */
    private boolean isOutOfScreen() {
        int tankWidth = GameModel.INSTANCE.gameWidth;
        int tankHeight = GameModel.INSTANCE.gameHeight;
        return x + bulletWidth < 0 || y + bulletHeight < 0 || x > tankWidth || y > tankHeight;
    }



    @Override
    public UUID getTankId() {
        return tankId;
    }

    /**
     * 移动
     * 修改x值之后，在下次调用paint方法时 调用fillRect实现坦克的移动
     */
    private void move() {
        switch (dir) {
            case LEFT:
                x -= speed;
                break;
            case UP:
                y -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case DOWN:
                y += speed;
                break;
            default:
                break;
        }

        // 更新碰撞检测的矩形
        rect.x = this.x;
        rect.y = this.y;
    }
}
