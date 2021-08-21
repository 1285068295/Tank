import org.junit.Assert;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * @author ：Lisp
 * @date： 2021/8/21
 * @version: V1.0
 * @slogan:
 * @description :图片测试类
 */
public class ImageTest {

    @Test
    public void test()  {
        try {
            // 硬盘读取一个文件
            BufferedImage image = ImageIO.read(ImageTest.class.getClassLoader().getResourceAsStream("../src/images/bulletD.gif"));


            Assert.assertNotNull(image);
        }catch (Exception e){

        }


    }

}
