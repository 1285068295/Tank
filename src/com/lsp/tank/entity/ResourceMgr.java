package com.lsp.tank.entity;

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
	public static BufferedImage goodTankL, goodTankU, goodTankR, goodTankD;
	/**
	 * 敌人坦克的四个方向
	 */
	public static BufferedImage badTankL, badTankU, badTankR, badTankD;
	/**
	 * 炮弹的四个方向
	 */
	public static BufferedImage bulletL, bulletU, bulletR, bulletD;
	/**
	 * 爆炸的16张图片 爆炸处理显示原理是 将16张图片 按顺序输出到屏幕上
	 */
	public static BufferedImage[] explodes = new BufferedImage[16];
	
 	
	static {
		try {
			goodTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/GoodTank1.png"));
			goodTankL = ImageUtil.rotateImage(goodTankU, -90);
			goodTankR = ImageUtil.rotateImage(goodTankU, 90);
			goodTankD = ImageUtil.rotateImage(goodTankU, 180);
			
			badTankU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/BadTank1.png"));
			badTankL = ImageUtil.rotateImage(badTankU, -90);
			badTankR = ImageUtil.rotateImage(badTankU, 90);
			badTankD = ImageUtil.rotateImage(badTankU, 180);
			
			bulletU = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/bulletU.png"));
			bulletL = ImageUtil.rotateImage(bulletU, -90);
			bulletR = ImageUtil.rotateImage(bulletU, 90);
			bulletD = ImageUtil.rotateImage(bulletU, 180);

			// 加载爆炸时需要的图片
			for(int i=0; i<16; i++){
                explodes[i] = ImageIO.read(ResourceMgr.class.getClassLoader().getResourceAsStream("images/e" + (i+1) + ".gif"));
            }
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
