package com.lsp.tank.entity;

import com.lsp.tank.collider.ColliderChain;
import com.lsp.tank.entity.abstractEntity.BaseTank;
import com.lsp.tank.factory.DefaultFactory;
import com.lsp.tank.factory.abstractFactory.GameFactory;
import com.lsp.tank.mgr.PropertyMgr;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author ：Lisp
 * @date： 2021/8/26
 * @version: V1.0
 * @slogan:
 * @description :model控制层  原始的代码为view（TankFrame）层包含了坦克 子弹  爆炸  耦合性太强
 * 现在拆分出model层来控制坦克 子弹  爆炸等，而view层与model层进行打交道
 * 这就是门面模式  坦克 子弹 爆炸与model经行交互 而model与view层交互
 *
 * gameModel相对于tankframe相当于门面模式
 * gameMode相对于tank explode wall bullet 等 对内是调停者角色
 *
 */
public class GameModel {

    /**
     * 游戏区域宽高
     */
    public int gameWidth, gameHeight;

    /**
     * 单例模式
     */
    public static final GameModel INSTANCE = new GameModel();

    public static GameModel getInstance(){
       return INSTANCE;
    }

    private GameModel(){ }
    

    static {
        INSTANCE.init();
    }
    /**
     * 游戏物体容器包括 坦克 子弹 爆炸 墙等
     */
    public List<GameObject> gameObjects = new ArrayList<>();

    DefaultSelfTank myTank;

    /**
     * 初始化方法
     */
    private void init() {
        gameWidth = PropertyMgr.getGameWidth();
        gameHeight = PropertyMgr.getGameHeight();

        /** 创建一个单人的坦克 */
        myTank = new DefaultSelfTank(UUID.randomUUID(),200, 400,   Dir.UP,20);
        add(myTank);
        // 初始化敌方坦克
//        for (int i = 0; i <10; i++) {
//            add(gameFactory.createEnemyTank(UUID.randomUUID(),100 + i * 100, 100, Dir.DOWN, 10));
//        }

        // 初始化墙
//        gameObjects.add(gameFactory.createWall(100, 400, 60, 200));
//        gameObjects.add(gameFactory.createWall(820, 400, 60, 200));
        gameObjects.add(gameFactory.createWall(200, 200, 200, 50));
//        gameObjects.add(gameFactory.createWall(600, 200, 200, 50));
    }


    /**
     * 游戏窗口引用 tankFrame作为view层
     */
//    public TankFrame tankFrame;

    /**
     * 游戏工厂可以创建坦克 子弹  爆炸
     * 方形爆炸 方向炮弹 RectFactory DefaultFactory
     * 这里只用切换了factory 会切换一整套的ui
     */
    public GameFactory gameFactory = new DefaultFactory();


    /**
     * 碰撞器链条
     */
    public ColliderChain colliderChain = new ColliderChain();


    /**
     * 添加游戏物体
     */
    public void add(GameObject go){
        gameObjects.add(go);
    }

    /**
     * 移除游戏物体
     */
    public void remove(GameObject go){
        gameObjects.remove(go);
    }


    public void paint(Graphics g){
        Color c = g.getColor();
        g.setColor(Color.WHITE);

//        g.drawString("坦克数量为:" + tanks.size(), 10,60);
//        g.drawString("子弹数量为:" + bullets.size(), 10,80);
//        g.drawString("爆炸数量为:" + explodes.size(), 10,100);
        g.setColor(c);

        // 绘制所有游戏物体：坦克、子弹、爆炸
        for (int i = 0; i < gameObjects.size(); i++) {
            gameObjects.get(i).paint(g);
        }

        // 处理游戏物体间的碰撞
        for (int i = 0; i < gameObjects.size(); i++) {
            for (int j = i + 1; j < gameObjects.size(); j++) {
                colliderChain.collide(gameObjects.get(i), gameObjects.get(j));
            }
        }

    }

    /**
     * 获取我方坦克
     */
    public BaseTank getMainTank() {
        return myTank;
    }
}
