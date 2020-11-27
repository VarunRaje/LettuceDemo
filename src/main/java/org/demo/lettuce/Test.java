package org.demo.lettuce;

import io.lettuce.core.RedisClient;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {

        RedisClient redisClient = RedisClient.create("redis://password@localhost:6379/0");
        StatefulRedisConnection<String, String> connection = redisClient.connect();
        RedisCommands<String, String> syncCommands = connection.sync();

        syncCommands.set("key", "Hello, Redis!");
        List<String> cities  = new ArrayList<>();
        cities.add("Mumbai");
        cities.add("Pune");

        syncCommands.set("Cities", cities.toString());
        System.out.println(syncCommands.get("key"));
        System.out.println(syncCommands.get("Cities"));

        connection.close();
        redisClient.shutdown();
    }


}
