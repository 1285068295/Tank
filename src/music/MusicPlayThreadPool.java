package music;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author ：Lisp
 * @date： 2021/9/1
 * @version: V1.0
 * @slogan:
 * @description :
 */
public class MusicPlayThreadPool {

    /**
     * 开启一个音乐播放线程池  参数自己指定
     * 因为每次播放音乐的时间至少要3秒  为了尽可能少的在队列中挤压线程任务  将线程池的线程数量设置的很大
     * 拒绝策略为忽略最早的任务  因为用的LinkedBlockingQueue 应该不会达到最大容量
     */
    private static final ThreadPoolExecutor musicPlayThreadPool = new ThreadPoolExecutor(250,
            250,
            60,
            TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.DiscardOldestPolicy());

    public static void playMusic(Runnable task){
        musicPlayThreadPool.submit(task);
        System.out.println("queue size " + musicPlayThreadPool.getQueue().size());
    }



}
