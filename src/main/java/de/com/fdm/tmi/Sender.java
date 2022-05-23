package de.com.fdm.tmi;

import com.github.philippheuer.credentialmanager.domain.OAuth2Credential;
import com.github.twitch4j.TwitchClient;
import com.github.twitch4j.TwitchClientBuilder;

public class Sender {
    private final TwitchClient client;

    public Sender(String auth) {
        OAuth2Credential credentials = new OAuth2Credential("twitch", auth);
        this.client = TwitchClientBuilder.builder()
                .withEnableChat(true)
                .withChatAccount(credentials)
                .build();
    }

    public void send(TMIMessage msg) {
        this.client.getChat().sendMessage(msg.channel(), msg.message());
    }
}
