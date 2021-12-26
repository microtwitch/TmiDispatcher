package de.com.fdm.tmi;

import de.com.fdm.grpc.dispatcher.lib.OutboundMessage;
import org.springframework.stereotype.Component;

import java.util.HashMap;

@Component
public class SenderManager {
    private final HashMap<String, Sender> senders;

    public SenderManager() {
        this.senders = new HashMap<>();
    }

    public void send(OutboundMessage msg) {
        if (!this.senders.containsKey(msg.getName())) {
            this.senders.put(msg.getName(), new Sender(msg.getAuth()));
        }

        this.senders.get(msg.getName()).send(msg);
    }
}
