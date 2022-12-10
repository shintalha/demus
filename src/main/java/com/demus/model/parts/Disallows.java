package com.demus.model.parts;

public class Disallows{
    private boolean resuming;

    public Disallows(boolean resuming) {
        this.resuming = resuming;
    }

    public boolean isResuming() {
        return resuming;
    }

    public void setResuming(boolean resuming) {
        this.resuming = resuming;
    }
}
