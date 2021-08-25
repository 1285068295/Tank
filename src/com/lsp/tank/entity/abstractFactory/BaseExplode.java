package com.lsp.tank.entity.abstractFactory;

import java.awt.*;

/**
 * @author ：Lisp
 * @date： 2021/8/23
 * @version: V1.0
 * @slogan:
 * @description :
 * 在抽取父类的时候 用到哪个就抽取哪个 不要一下子全抽出来
 */
public abstract class BaseExplode {
    /**
     * 画图方法
     */
    public abstract void paint(Graphics g);
}
