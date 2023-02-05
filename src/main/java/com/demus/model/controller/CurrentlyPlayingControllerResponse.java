package com.demus.model.controller;

import com.demus.model.httpResponseEntity.CurrentlyPlaying;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CurrentlyPlayingControllerResponse extends ControllerResponse {
    private CurrentlyPlaying currentlyPlaying;
}
