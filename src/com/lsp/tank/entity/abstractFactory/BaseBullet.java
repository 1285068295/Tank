package com.lsp.tank.entity.abstractFactory;

import java.awt.*;

/**
 * @author ：Lisp
 * @date： 2021/8/23
 * @version: V1.0
 * @slogan:
 * @description :这里的方法是伴随着提取BaseBullet的过程中创建的
 *              需要抽取哪个方法的时候再进行抽取
 */
public abstract class BaseBullet {

    public abstract void paint(Graphics g);

    public abstract void collideWith(BaseTank baseTank);
}
