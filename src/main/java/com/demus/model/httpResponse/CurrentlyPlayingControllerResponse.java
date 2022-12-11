package com.demus.model.httpResponse;

import com.demus.model.httpResponseEntity.CurrentlyPlaying;

public class CurrentlyPlayingControllerResponse extends ControllerResponse {
    private CurrentlyPlaying currentlyPlaying;

    public CurrentlyPlaying getCurrentlyPlaying() {
        return currentlyPlaying;
    }

    public void setCurrentlyPlaying(CurrentlyPlaying currentlyPlaying) {
        this.currentlyPlaying = currentlyPlaying;
    }

    public void notListeningCurently() {
        this.setMessage("Not listening right now.");
        this.setStatusCode(204);
        this.setSuccess(true);
    }
    public void currentlyListening() {
        this.setMessage("Listening Right now.");
        this.setStatusCode(200);
        this.setSuccess(true);
    }
}
