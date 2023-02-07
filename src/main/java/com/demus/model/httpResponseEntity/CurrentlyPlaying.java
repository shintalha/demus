package com.demus.model.httpResponseEntity;

import com.demus.model.spotify.Actions;
import com.demus.model.spotify.Context;
import com.demus.model.spotify.Track;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CurrentlyPlaying {
    private long timestamp;
    private Context context;
    private int progress_ms;
    private Track item;
    private String currently_playing_type;
    private Actions actions;
    private boolean is_playing;
}
