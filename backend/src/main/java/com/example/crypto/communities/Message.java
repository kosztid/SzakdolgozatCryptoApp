package com.example.crypto.communities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private boolean image;
    private String message;
    private String sender;
    private String senderemail;
    private String time;

    public Message() {
    }

    public Message(boolean image, String message, String sender, String senderemail, String time) {
        this.image = image;
        this.message = message;
        this.sender = sender;
        this.senderemail = senderemail;
        this.time = time;
    }

    public Message(Long id, boolean image, String message, String sender, String senderemail, String time) {
        this.id = id;
        this.image = image;
        this.message = message;
        this.sender = sender;
        this.senderemail = senderemail;
        this.time = time;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isImage() {
        return image;
    }

    public void setImage(boolean image) {
        this.image = image;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSenderemail() {
        return senderemail;
    }

    public void setSenderemail(String senderemail) {
        this.senderemail = senderemail;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id='" + id + '\'' +
                ", image=" + image +
                ", message='" + message + '\'' +
                ", sender='" + sender + '\'' +
                ", senderemail='" + senderemail + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
