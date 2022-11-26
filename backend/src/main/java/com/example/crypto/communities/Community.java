package com.example.crypto.communities;

import com.example.crypto.users.listTypes.FavfolioItem;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "communities")
public class Community {
    @Id
    private String id;
    private String name;
    @OneToMany(targetEntity = Message.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "um_fk", referencedColumnName = "id")
    private List<Message> messages;

    @ElementCollection
    private List<String> members;

    public Community() {
    }

    public Community(String id, String name, List<Message> messages, List<String> members) {
        this.id = id;
        this.name = name;
        this.messages = messages;
        this.members = members;
    }

    public Community(String name, List<Message> messages, List<String> members) {
        this.name = name;
        this.messages = messages;
        this.members = members;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public List<String> getMembers() {
        return members;
    }

    public void setMembers(List<String> members) {
        this.members = members;
    }

    @Override
    public String toString() {
        return "Community{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", messages=" + messages +
                ", members=" + members +
                '}';
    }
}

