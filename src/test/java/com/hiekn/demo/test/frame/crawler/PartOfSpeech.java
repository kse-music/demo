package com.hiekn.demo.test.frame.crawler;

public class PartOfSpeech {

    private String tag;
    private String description;

    public PartOfSpeech(String tag) {
        this(tag,null);
    }

    public PartOfSpeech(String tag, String description) {
        this.tag = tag;
        this.description = description;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
