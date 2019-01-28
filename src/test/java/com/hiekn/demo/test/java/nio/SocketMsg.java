package com.hiekn.demo.test.java.nio;

import com.hiekn.demo.util.JsonUtils;

import java.io.Serializable;

/**
 * describe about this class
 *
 * @author DingHao
 * @date 2019/1/28 15:28
 */
public class SocketMsg implements Serializable {

    private String content;
    private String from;
    private String to;
    private Long sendTime = System.nanoTime();

    public SocketMsg(String content, String from, String to) {
        this.content = content;
        this.from = from;
        this.to = to;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Long getSendTime() {
        return sendTime;
    }

    public void setSendTime(Long sendTime) {
        this.sendTime = sendTime;
    }

    @Override
    public String toString() {
        return JsonUtils.toJson(this);
    }
}
