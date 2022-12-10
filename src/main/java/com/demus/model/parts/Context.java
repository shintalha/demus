package com.demus.model.parts;

public class Context{
    private ExternalUrls external_urls;
    private String href;
    private String type;
    private String uri;

    public Context(ExternalUrls external_urls, String href, String type, String uri) {
        this.external_urls = external_urls;
        this.href = href;
        this.type = type;
        this.uri = uri;
    }

    public ExternalUrls getExternal_urls() {
        return external_urls;
    }

    public void setExternal_urls(ExternalUrls external_urls) {
        this.external_urls = external_urls;
    }

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
