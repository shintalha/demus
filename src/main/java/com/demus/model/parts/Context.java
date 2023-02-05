package com.demus.model.parts;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Context{
    private ExternalUrls external_urls;
    private String href;
    private String type;
    private String uri;
}
