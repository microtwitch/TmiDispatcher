package de.com.fdm.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.com.fdm.tmi.SenderManager;
import de.com.fdm.tmi.TMIMessage;
import org.redisson.Redisson;
import org.redisson.api.RTopic;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class RedisService {
    private static final Logger LOG = LoggerFactory.getLogger(RedisService.class);

    @Autowired
    public RedisService(SenderManager senderManager) throws IOException {
        Config config = Config.fromYAML(new File("src/main/resources/redisson_config.yaml"));
        RedissonClient redisson = Redisson.create(config);

        RTopic topic = redisson.getTopic("tmiDispatcher");
        ObjectMapper mapper = new ObjectMapper();
        topic.addListener(String.class, ((channel, msg) -> {
            try {
                TMIMessage tmiMessage = mapper.readValue(msg, TMIMessage.class);
                senderManager.send(tmiMessage);
            } catch (JsonProcessingException e) {
                LOG.error(e.getMessage());
            }
        }));
    }
}
