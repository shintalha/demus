package com.demus.model.parts;

public class Actions{

    public Actions(Disallows disallows) {
        this.disallows = disallows;
    }

    private Disallows disallows;

    public Disallows getDisallows() {
        return disallows;
    }

    public void setDisallows(Disallows disallows) {
        this.disallows = disallows;
    }
}
