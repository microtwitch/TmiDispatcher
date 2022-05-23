package de.com.fdm.service;

import de.com.fdm.tmi.SenderManager;
import de.com.fdm.tmi.TMIMessage;
import org.redisson.Redisson;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RedisService {
    @Autowired
    public RedisService(SenderManager senderManager) {
        Config config = new Config();
        config.useSingleServer().setAddress("redis://127.0.0.1:6379");
        RedissonClient redisson = Redisson.create(config);

        RTopic topic = redisson.getTopic("tmiDispatcher");
        topic.addListener(TMIMessage.class, (channel, msg) -> senderManager.send(msg));
    }
}
