package music;

import com.lsp.tank.entity.Audio;

/**
 * @author ：Lisp
 * @date： 2021/9/1
 * @version: V1.0
 * @slogan:
 * @description :坦克爆炸音效处理
 */
public class TankExplode implements Runnable {
    @Override
    public void run() {
        new Audio("audio/tank_explode.wav").play();
    }
}
