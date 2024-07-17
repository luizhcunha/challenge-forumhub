package br.com.forumhub.challenge.forumhub.domain.topic;

public enum StatusTopic {
    OPEN("aberto"),
    RESOLVED("solucionado");

    private final String value;

    StatusTopic(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
