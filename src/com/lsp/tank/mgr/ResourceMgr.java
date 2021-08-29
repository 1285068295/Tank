package com.lsp.tank.mgr;

import com.lsp.tank.entity.ImageUtil;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

/**
 * 管理资源 把图片加入到内存中 使用时直接用不需要再从硬盘读取了
 */
public class ResourceMgr {
	/**
	 * 我的坦克的四个方向
	 */
	public static BufferedImage selfTankL, selfTankU, selfTankR, selfTankD;
	/**
	 * 敌人坦克的四个方向
	 */
	public static BufferedImage enemyTankL, enemyTankU, enemyTankR, enemyTankD;
	/**
	 * 炮弹的四个方向
	 */
	public static BufferedImage bulletL, bulletU, bulletR, bulletD;
	/**
	 * 爆炸的16张图片 爆炸处理显示原理是 将16张图片 按顺序输出到屏幕上
	 */
	public static BufferedImage[] explodes = new BufferedImage[16];

	public static BufferedImage gameOver;

	static {
		// 加载四个方向的我方坦克图片
		selfTankU = readImage("GoodTank1.png");
		selfTankL = ImageUtil.rotateImage(selfTankU, -90);
		selfTankR = ImageUtil.rotateImage(selfTankU, 90);
		selfTankD = ImageUtil.rotateImage(selfTankU, 180);
		// 加载四个方向的敌方坦克图片
		enemyTankU = readImage("BadTank1.png");
		enemyTankL = ImageUtil.rotateImage(enemyTankU, -90);
		enemyTankR = ImageUtil.rotateImage(enemyTankU, 90);
		enemyTankD = ImageUtil.rotateImage(enemyTankU, 180);
		// 加载四个方向的子弹图片
		bulletU = readImage("bulletU.png");
		bulletL = ImageUtil.rotateImage(bulletU, -90);
		bulletR = ImageUtil.rotateImage(bulletU, 90);
		bulletD = ImageUtil.rotateImage(bulletU, 180);
		// 加载坦克爆炸的图片
		for (int i = 0; i < explodes.length; i++) {
			explodes[i] = readImage("e" + (i + 1) + ".gif");
		}
		gameOver = readImage("GameOver.png");
	}

	private static BufferedImage readImage(String filename) {
		try {
			return ImageIO.read(ResourceMgr.class.getResource("/images/" + filename));
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("读取图片资源失败！");
		}
	}
}
