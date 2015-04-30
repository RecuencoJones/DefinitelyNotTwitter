package org.dn.twitter.model;

/**
 * Created by david on 30/04/2015.
 */
public class Message {
    
    private String who;
    private String to;
    private String text;

    public Message() {
    }

    public Message(String who, String text) {
        this.who = who;
        this.text = text;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
