package com.hieupham.data.utils.common;

import android.support.annotation.NonNull;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.List;

/**
 * Created by hieupham on 6/13/18.
 */

public class CommonUtils {

    public static long[] toLongArray(@NonNull List<Long> array) {
        long[] result = new long[array.size()];
        for (int i = 0; i < array.size(); i++) {
            result[i] = array.get(i);
        }
        return result;
    }

    public static String[] toStringArray(@NonNull List<String> array) {
        String[] result = new String[array.size()];
        for (int i = 0; i < array.size(); i++) {
            result[i] = array.get(i);
        }
        return result;
    }

    public static boolean isNetworkError(Throwable throwable) {
        return throwable instanceof SocketTimeoutException
                || throwable instanceof UnknownHostException;
    }
}
