package de.com.fdm.tmi;

import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class SenderManager {
    private final HashMap<String, Sender> senders;

    public SenderManager() {
        this.senders = new HashMap<>();
    }

    public void send(TMIMessage msg) {
        if (!this.senders.containsKey(msg.name())) {
            Sender sender = new Sender(msg.auth());
            this.senders.put(msg.name(), sender);
        }

        this.senders.get(msg.name()).send(msg);
    }
}
