package com.example.CofeeOrder.model;


public enum EventType {

    REGISTERED("registered"),
    PROCESSED("processed"),
    CANCELED("canceled"),
    BEEN_READING("been_readying"),
    DELIVERED("delivered");

    private final String action;

    EventType(String action) {
        this.action = action;
    }

    public String getAction() {
        return this.action;
    }

    public static class Values {
        public static final String REGISTERED = "registered";
        public static final String PROCESSED = "processed";
        public static final String CANCELED = "canceled";
        public static final String BEEN_READING = "been_readying";
        public static final String DELIVERED = "delivered";
    }

    @Override
    public String toString() {
        return "EventType{" +
                "action='" + action + '\'' +
                '}';
    }
}
