package com.lsp.tank.entity;

import com.lsp.tank.entity.abstractFactory.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author ：Lisp
 * @date： 2021/8/26
 * @version: V1.0
 * @slogan:
 * @description :model控制层  原始的代码为view（TankFrame）层包含了坦克 子弹  爆炸  耦合性太强
 * 现在拆分出model层来控制坦克 子弹  爆炸等，而view层与model层进行打交道
 * 这就是门面模式  坦克 子弹 爆炸与model经行交互 而model与view层交互
 */
public class GameModel {
    /**
     * 游戏窗口引用 tankFrame作为view层
     */
//    public TankFrame tankFrame;

    /** 创建一个单人的坦克 */
    Tank myTank = new Tank(200, 400, Dir.RIGHT, Group.GOOD, this);




    /** 创建坦克子弹容器 */
    public List<BaseBullet> bullets = new ArrayList<>();

    /** 创建敌人坦克容器 */
    public List<BaseTank> tanks = new ArrayList<>();

    /** 爆炸集合 */
    public List<BaseExplode> explodes = new ArrayList<>();

    /**
     * 游戏工厂可以创建坦克 子弹  爆炸
     * 方形爆炸 方向炮弹 RectFactory DefaultFactory
     * 这里只用切换了factory 会切换一整套的ui
     */
    public GameFactory gameFactory = new DefaultFactory();

    public GameModel(){

        // 初始化敌方坦克
        for (int i = 0; i <10; i++) {
            this.tanks.add(this.gameFactory.createTank(100 + i * 100, 200, Dir.DOWN, Group.BAD, this));
        }

    }


    /**
     * 用来存储按下的方向四个键，当同时按下多个键时，以最后一次的按键为主
     * 每松开一个键就从栈中弹出一个数据，所以栈中最多存4个按键数据
     *
     * 注意 不能用stack来存储  释放按键时是无序释放的
     * →↓←
     */
    LinkedList<Dir> moveDir = new LinkedList<>();


    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.WHITE);
        g.drawString("坦克数量为:" + tanks.size(), 10,60);
        g.drawString("子弹数量为:" + bullets.size(), 10,80);
        g.drawString("爆炸数量为:" + explodes.size(), 10,100);
        g.setColor(c);

        myTank.paint(g);
        // 所有的子弹都一样  使用普通循环删除处理即可
        for (int i = 0; i < bullets.size(); i++) {
            bullets.get(i).paint(g);
        }

        // 画出敌人坦克
        for (int i = 0; i < tanks.size(); i++) {
            tanks.get(i).paint(g);
        }


        // 检测坦克与子弹是否碰撞了  碰撞后要移除子弹和坦克
        for (int i = 0; i < bullets.size(); i++) {
            for (int j = 0; j < tanks.size(); j++) {
                // 检测子弹是否与坦克碰撞了 添加爆炸
                bullets.get(i).collideWith(tanks.get(j));
            }
        }

        // 画出爆炸图
        for (int i = 0; i < explodes.size(); i++) {
            explodes.get(i).paint(g);

        }
    }

    /**
     * 获取我方坦克
     */
    public BaseTank getMainTank() {
        return myTank;
    }
}
