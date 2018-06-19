package com.hieupham.domain.entity;

import android.support.annotation.VisibleForTesting;

/**
 * Created by hieupham on 6/13/18.
 */

public class Block implements Entity {

    private long number;

    private String hash;

    private String createdAt;

    public Block() {
    }

    @VisibleForTesting
    public Block(long number, String hash, String createdAt) {
        this.number = number;
        this.hash = hash;
        this.createdAt = createdAt;
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Block)) return false;

        Block block = (Block) o;

        if (number != block.number) return false;
        if (hash != null ? !hash.equals(block.hash) : block.hash != null) return false;
        return createdAt != null ? createdAt.equals(block.createdAt) : block.createdAt == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (number ^ (number >>> 32));
        result = 31 * result + (hash != null ? hash.hashCode() : 0);
        result = 31 * result + (createdAt != null ? createdAt.hashCode() : 0);
        return result;
    }
}
