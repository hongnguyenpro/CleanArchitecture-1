package com.hieupham.data.source.local.api;

import android.content.Context;
import android.content.SharedPreferences;
import com.google.gson.Gson;
import javax.inject.Inject;

/**
 * Created by hieupham on 6/5/18.
 */

public class SharedPrefApi {

    public static final String LAST_KNOWN_BLOCK_HEIGHT = "last_known_block_height";

    private SharedPreferences sharedPreferences;
    private Gson gson;

    @Inject
    public SharedPrefApi(Context context, Gson gson) {
        this.sharedPreferences =
                context.getSharedPreferences("bitmark_explorer", Context.MODE_PRIVATE);
        this.gson = gson;
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key, Class<T> clazz) {
        if (clazz == String.class) {
            return (T) sharedPreferences.getString(key, "");
        } else if (clazz == Boolean.class) {
            return (T) Boolean.valueOf(sharedPreferences.getBoolean(key, false));
        } else if (clazz == Float.class) {
            return (T) Float.valueOf(sharedPreferences.getFloat(key, 0));
        } else if (clazz == Integer.class) {
            return (T) Integer.valueOf(sharedPreferences.getInt(key, 0));
        } else if (clazz == Long.class) {
            return (T) Long.valueOf(sharedPreferences.getLong(key, 0));
        } else {
            return gson.fromJson(sharedPreferences.getString(key, ""), clazz);
        }
    }

    public <T> void put(String key, T data) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        if (data instanceof String) {
            editor.putString(key, (String) data);
        } else if (data instanceof Boolean) {
            editor.putBoolean(key, (Boolean) data);
        } else if (data instanceof Float) {
            editor.putFloat(key, (Float) data);
        } else if (data instanceof Integer) {
            editor.putInt(key, (Integer) data);
        } else if (data instanceof Long) {
            editor.putLong(key, (Long) data);
        } else {
            editor.putString(key, gson.toJson(data));
        }
        editor.apply();
    }

    public void clear() {
        sharedPreferences.edit().clear().apply();
    }
}
