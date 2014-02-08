package com.andremanuelbarbosa.redis.cache.loader;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.daemon.Daemon;
import org.apache.commons.daemon.DaemonContext;
import org.apache.commons.daemon.DaemonInitException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RedisCacheLoader implements Daemon {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisCacheLoader.class);

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    public void destroy() {

        LOGGER.info("Destroying daemon.");
    }

    @Override
    public void init(DaemonContext daemonContext) throws DaemonInitException, Exception {

        LOGGER.info("Daemon initialized...");
    }

    @Override
    public void start() throws Exception {

        LOGGER.info("Starting up daemon.");

        this.executorService.execute(new Runnable() {

            boolean running = true;

            CountDownLatch latch = new CountDownLatch(1);

            public void run() {
                try {
                    while (running) {
                        LOGGER.info("I'M ALIVE");
                        Thread.sleep(1000);
                    }
                    latch.await();
                } catch (InterruptedException e) {
                    LOGGER.info("Thread interrupted, probably means we're shutting down now.");
                }
            }
        });
    }

    @Override
    public void stop() throws Exception {

        LOGGER.info("Stopping daemon.");

        this.executorService.shutdown();
    }

    public static void main(String[] args) throws Exception {

        LOGGER.info("RUNNING MAIN");

        new RedisCacheLoader().start();
    }
}
