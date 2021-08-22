package com.lsp.tank;

import javax.sound.sampled.*;

/**
 * @author ：Lisp
 * @date： 2021/8/22
 * @version: V1.0
 * @slogan:
 * @description :
 */
public class Audio extends Thread {

    private SourceDataLine sourceDataLine = null;
    private AudioFormat audioFormat = null;
    private AudioInputStream audioInputStream = null;
    private DataLine.Info dataLine_info = null;

    @Override
    public void run() {

        try {
            byte[] b = new byte[1024];
            int len = 0;
            sourceDataLine.open(audioFormat, 1024 * 1024 * 15);
            sourceDataLine.start();
            while ((len = audioInputStream.read(b)) > 0) {
                sourceDataLine.write(b, 0, len);
            }
//            audioInputStream.reset();

            sourceDataLine.drain();
            sourceDataLine.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Audio(String fileName) {
        try {
            audioInputStream = AudioSystem.getAudioInputStream(Audio.class.getClassLoader().getResource(fileName));
            audioFormat = audioInputStream.getFormat();
            dataLine_info = new DataLine.Info(SourceDataLine.class, audioFormat);
            sourceDataLine = (SourceDataLine) AudioSystem.getLine(dataLine_info);
            //FloatControl volctrl=(FloatControl)sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN);
            //volctrl.setValue(-40);//
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
