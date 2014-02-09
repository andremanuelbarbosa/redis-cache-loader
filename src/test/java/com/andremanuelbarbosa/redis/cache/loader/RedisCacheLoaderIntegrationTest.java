package com.andremanuelbarbosa.redis.cache.loader;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import redis.embedded.RedisServer;

public class RedisCacheLoaderIntegrationTest {

    private static RedisServer redisServer;

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {

        redisServer = new RedisServer(6379);
        redisServer.start();
    }

    @AfterClass
    public static void tearDownBeforeClass() {

        if (redisServer != null) {

            redisServer.stop();
        }
    }

    @Test
    public void test() {

    }
}
