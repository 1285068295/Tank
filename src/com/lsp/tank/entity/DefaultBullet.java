package com.lsp.tank.entity;

import com.lsp.tank.entity.abstractEntity.BaseBullet;
import com.lsp.tank.mgr.ResourceMgr;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.UUID;

/**
 * @author ：Lisp
 * @date： 2021/8/21
 * @version: V1.0
 * @slogan:
 * @description :坦克子弹
 */
public class DefaultBullet extends BaseBullet {

    public BufferedImage curBulletImage;

    public DefaultBullet(UUID id, UUID tankId, int x, int y, Dir dir, Group group) {
        this.id = id;
        this.tankId = tankId;
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group = group;
        this.rect = new Rectangle();
        switch (dir) {
            case LEFT: curBulletImage = ResourceMgr.bulletL; break;
            case UP: curBulletImage = ResourceMgr.bulletU; break;
            case RIGHT: curBulletImage = ResourceMgr.bulletR; break;
            case DOWN: curBulletImage = ResourceMgr.bulletD; break;
            default:break;
        }
        updateRect(x, y);
    }


    private void updateRect(int x, int y) {
        rect.x = x;
        rect.y = y;
        rect.width = curBulletImage.getWidth();
        rect.height = curBulletImage.getHeight();
    }


    @Override
    public Rectangle getRect() {
        return rect;
    }

    @Override
    public void paint(Graphics g){
        if(!isLiving()) {
            GameModel.getInstance().remove(this);
        }
        g.drawImage(curBulletImage, x, y, null);
        // 移动子弹
        move();
    }


    @Override
    public boolean isLiving() {
        return !isOutOfScreen();
    }

    @Override
    public UUID getTankId() {
        return tankId;
    }

    /**
     * 判断子弹是否飞出屏幕
     * 考虑了子弹的大小
     * @return
     */
    private boolean isOutOfScreen() {
        int gameWidth = GameModel.INSTANCE.gameWidth;
        int gameHeight = GameModel.INSTANCE.gameHeight;
        int bulletWidth = curBulletImage.getWidth();
        int bulletHeight = curBulletImage.getHeight();
        return x + bulletWidth < 0 || y + bulletHeight < 0 || x > gameWidth || y > gameHeight;
    }


    /**
     * 移动
     */
    private void move() {
        switch (dir) {
            case LEFT:
                // 这里修改x值之后，在下次调用paint方法时 调用fillRect实现坦克的移动
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
