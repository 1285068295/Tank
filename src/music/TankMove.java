package music;

import com.lsp.tank.entity.Audio;

/**
 * @author ：Lisp
 * @date： 2021/9/1
 * @version: V1.0
 * @slogan:
 * @description :坦克移动时的音效
 */
public class TankMove implements Runnable {

    @Override
    public void run() {
        new Audio("audio/tank_move.wav").play();
    }
}
