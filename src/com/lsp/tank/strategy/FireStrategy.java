package com.lsp.tank.strategy;

import com.lsp.tank.entity.Tank;

/**
 * @author ：Lisp
 * @date： 2021/8/22
 * @version: V1.0
 * @slogan:
 * @description :坦克开火策略  使用策略模式实现不同的开火模式
 */
public interface FireStrategy {

    /**
     * 坦克的开火方式
     * @param tank
     */
    void fire(Tank tank);

}
