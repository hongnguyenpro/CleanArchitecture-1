package com.hieupham.data.source.local.api.converter;

import android.arch.persistence.room.TypeConverter;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.internal.LinkedTreeMap;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

/**
 * Created by hieupham on 6/6/18.
 */

public class LinkedTreeMapConverter {

    @TypeConverter
    public static String toString(LinkedTreeMap<String, String> map) {
        return map == null || map.isEmpty() ? null
                : new Gson().toJsonTree(map).getAsJsonObject().toString();
    }

    @TypeConverter
    public static LinkedTreeMap<String, String> fromString(String str) {
        if (TextUtils.isEmpty(str)) return null;
        Type type = new TypeToken<LinkedTreeMap<String, String>>() {
        }.getType();
        return new Gson().fromJson(str, type);
    }
}
