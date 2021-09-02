package music;

import com.lsp.tank.entity.Audio;

/**
 * @author ：Lisp
 * @date： 2021/9/1
 * @version: V1.0
 * @slogan:
 * @description :坦克开火音效处理
 */
public class TankFire implements Runnable {

    @Override
    public void run() {
        new Audio("audio/tank_fire.wav").play();
    }
}
