package com.lsp.tank.entity;

import com.lsp.tank.entity.abstractFactory.BaseTank;

/**
 * @author ：Lisp
 * @date： 2021/8/22
 * @version: V1.0
 * @slogan:
 * @description :坦克开火策略  使用策略模式实现不同的开火模式
 */
public interface FireStrategy {

    void fire(BaseTank tank);

}
