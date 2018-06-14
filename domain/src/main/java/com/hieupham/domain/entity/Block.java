package com.hieupham.domain.entity;

/**
 * Created by hieupham on 6/13/18.
 */

public class Block implements Entity {

    private long number;

    private String hash;

    private String createdAt;

    public long getNumber() {
        return number;
    }

    public void setNumber(long number) {
        this.number = number;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
