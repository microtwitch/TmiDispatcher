package de.com.fdm.tmi;

public record TMIMessage(String name, String auth, String channel, String message) {}
