package com.mgumussoy.humanresourcesbackend.enums;

public enum MilitaryStatus {
    COMPLETED("COMPLETED"),
    EXEMPT("EXEMPT"),
    DEFERRED("DEFERRED");

    private final String militaryStatus;

    private MilitaryStatus(String status) {
        militaryStatus = status;
    }

    public String getStatus() {
        return militaryStatus;
    }
}
