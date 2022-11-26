package com.example.crypto.users.listTypes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class UserActionLogItem {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String actionType;

    private String userEmail;

    private String time;

    private Double count;
    private Double count2;

    private String itemId;
    private String itemId2;

    public UserActionLogItem() {
    }

    public UserActionLogItem(String actionType, String userEmail, String time, Double count, Double count2, String itemId, String itemId2) {
        this.actionType = actionType;
        this.userEmail = userEmail;
        this.time = time;
        this.count = count;
        this.count2 = count2;
        this.itemId = itemId;
        this.itemId2 = itemId2;
    }

    public UserActionLogItem(Long id, String actionType, String userEmail, String time, Double count, Double count2, String itemId, String itemId2) {
        this.id = id;
        this.actionType = actionType;
        this.userEmail = userEmail;
        this.time = time;
        this.count = count;
        this.count2 = count2;
        this.itemId = itemId;
        this.itemId2 = itemId2;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Double getCount2() {
        return count2;
    }

    public void setCount2(Double count2) {
        this.count2 = count2;
    }

    public String getItemId2() {
        return itemId2;
    }

    public void setItemId2(String itemId2) {
        this.itemId2 = itemId2;
    }

    @Override
    public String toString() {
        return "UserActionLogItem{" +
                "id=" + id +
                ", actionType='" + actionType + '\'' +
                ", userEmail='" + userEmail + '\'' +
                ", time='" + time + '\'' +
                ", count=" + count +
                ", count2=" + count2 +
                ", itemId='" + itemId + '\'' +
                ", itemId2='" + itemId2 + '\'' +
                '}';
    }
}
