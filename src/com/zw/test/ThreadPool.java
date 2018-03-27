package com.zw.test;

import java.util.concurrent.LinkedBlockingQueue;

public class ThreadPool {
    private volatile boolean isTerminated = false;

    private volatile boolean isRunning = false;

    private LinkedBlockingQueue<Runnable> tasks = new LinkedBlockingQueue<Runnable>();

    public void execute(Runnable runnable) {
        if (null != runnable && !isTerminated) {
            tasks.add(runnable);
        }
        if (!isRunning) {
            synchronized (tasks) {
                if (!isRunning) {
                    isRunning = true;
                    loop();
                }
            }
        }
    }

    public void shutdown() {
        isTerminated = true;
        isRunning = false;
    }

    private void loop() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!isTerminated) {
                    try {
                        Runnable task = tasks.take();
                        task.run();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
