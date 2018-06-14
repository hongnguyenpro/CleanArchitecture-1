package com.hieupham.data.source.remote.api.response;

import com.google.gson.annotations.Expose;

/**
 * Created by hieupham on 6/4/18.
 */

public class InfoResponse implements Response {

    @Expose
    private long height;

    public long getHeight() {
        return height;
    }
}
