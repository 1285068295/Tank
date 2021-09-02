package music;

import com.lsp.tank.entity.Audio;

/**
 * @author ：Lisp
 * @date： 2021/9/1
 * @version: V1.0
 * @slogan:
 * @description :
 */
public class War implements Runnable {

    private final static Audio warAudio = new Audio("audio/war.wav");

    @Override
    public void run() {
        warAudio.loop();
    }
}
