package com.lsp.tank.mgr;

import com.lsp.tank.factory.abstractfactory.GameFactory;
import com.lsp.tank.strategy.FireStrategy;
import com.lsp.tank.collider.Collider;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Properties;

public class PropertyMgr {
	private static Properties props = null;
	static {
		try {
			props = new Properties();
			props.load(PropertyMgr.class.getResourceAsStream("/config.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static String get(String key) {
		return props.getProperty(key);
	}

	/**
	 * 获取游戏区域宽度
	 * @return
	 */
	public static int getGameWidth() {
		return Integer.parseInt(get("gameWidth"));
	}

	/**
	 * 获取游戏区域高度
	 * @return
	 */
	public static int getGameHeight() {
		return Integer.parseInt(get("gameHeight"));
	}

	/**
	 * 获取敌方坦克数量
	 */
	public static int getEnemy_tank_count() {
		return Integer.parseInt(get("enemy_tank_count"));
	}

	/**
	 * 获取敌方坦克开火策略
	 * @return
	 */
	public static FireStrategy getEnemy_tank_fs() {
		try {
			Class<?> clazz = Class.forName("com.lsp.tank.strategy." + get("enemy_tank_fs"));
			return (FireStrategy) clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("敌方坦克开火策略读取失败！");
		}
	}

	/**
	 * 获取己方坦克开火策略
	 * @return
	 */
	public static FireStrategy getSelf_tank_fs() {
		try {
			Class<?> clazz = Class.forName("com.lsp.tank.strategy." + get("self_tank_fs"));
			return (FireStrategy) clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("己方坦克开火策略读取失败！");
		}
	}

	/**
	 * 获取生产坦克、炮弹和爆炸效果的工厂类
	 * @return
	 */
	public static GameFactory getFactory() {
		try {
			Class<?> clazz = Class.forName("com.lsp.tank.factory." + get("factory_style"));
			return (GameFactory) clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("工厂模式读取失败！");
		}
	}

	/**
	 * 获取碰撞检测规则
	 * @return
	 */
	public static LinkedList<Collider> getColliders() {
		try {
			String[] colliderNames = get("colliders").split(",");
			LinkedList<Collider> colliders = new LinkedList<>();
			for (String colliderName : colliderNames) {
				Class<?> clazz = Class.forName("com.lsp.tank.collider." + colliderName);
				Collider collider = (Collider) clazz.newInstance();
				colliders.add(collider);
			}
			return colliders;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("读取碰撞策略读取失败！");
		}
	}
}
