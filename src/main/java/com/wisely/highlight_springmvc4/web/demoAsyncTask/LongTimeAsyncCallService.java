package com.wisely.highlight_springmvc4.web.demoAsyncTask;

import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 *
 * @author: jhc
 * @create: 2016/12/8
 * @version: 1.0
 */
@Service
public class LongTimeAsyncCallService {

    private final int CorePoolSize = 4;
    private final int NeedSeconds = 1;
    private Random random = new Random();
    private ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(CorePoolSize);
    public void makeRemoteCallAndUnknownWhenFinish(final LongTermTaskCallback callback) {
        System.out.println("完成此任务需要：" + NeedSeconds + "秒");
        scheduler.schedule(new Runnable() {
            @Override
            public void run() {
                callback.callback(new Date().toLocaleString());
            }
        },NeedSeconds , TimeUnit.SECONDS);
    }
}
