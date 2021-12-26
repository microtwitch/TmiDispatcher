package de.com.fdm.tmi;

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;
import de.com.fdm.grpc.dispatcher.lib.OutboundMessage;

public class Sender {
    private final TwitchClient client;

    public Sender(String auth) {
        OAuth2Credential credentials = new OAuth2Credential("twitch", auth);
        this.client = TwitchClientBuilder.builder()
                .withEnableChat(true)
                .withChatAccount(credentials)
                .build();
    }

    public void send(OutboundMessage msg) {
        this.client.getChat().sendMessage(msg.getChannel(), msg.getText());
    }
}
