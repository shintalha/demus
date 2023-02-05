package com.demus.model.spotify;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Followers {
    private String href;
    private Integer total;
}
