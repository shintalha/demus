package com.demus.model.spotify;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ExplicitContent {
    private boolean filter_enabled;
    private boolean filter_locked;
}
