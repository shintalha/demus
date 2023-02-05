package com.demus.model.httpResponseEntity;

import com.demus.model.spotify.Actions;
import com.demus.model.spotify.Context;
import com.demus.model.spotify.Item;

public class CurrentlyPlaying {
    private long timestamp;
    private Context context;
    private int progress_ms;
    private Item item;
    private String currently_playing_type;
    private Actions actions;
    private boolean is_playing;

    public CurrentlyPlaying(long timestamp, Context context, int progress_ms, Item item, String currently_playing_type, Actions actions, boolean is_playing) {
        this.timestamp = timestamp;
        this.context = context;
        this.progress_ms = progress_ms;
        this.item = item;
        this.currently_playing_type = currently_playing_type;
        this.actions = actions;
        this.is_playing = is_playing;
    }

    public CurrentlyPlaying() {

    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    public int getProgress_ms() {
        return progress_ms;
    }

    public void setProgress_ms(int progress_ms) {
        this.progress_ms = progress_ms;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String getCurrently_playing_type() {
        return currently_playing_type;
    }

    public void setCurrently_playing_type(String currently_playing_type) {
        this.currently_playing_type = currently_playing_type;
    }

    public Actions getActions() {
        return actions;
    }

    public void setActions(Actions actions) {
        this.actions = actions;
    }

    public boolean isIs_playing() {
        return is_playing;
    }

    public void setIs_playing(boolean is_playing) {
        this.is_playing = is_playing;
    }
}
