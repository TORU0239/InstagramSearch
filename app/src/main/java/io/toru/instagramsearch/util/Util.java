package io.toru.instagramsearch.util;

import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wonyoung on 2017. 5. 22..
 */

public class Util {
    private static final String TAG = Util.class.getSimpleName();

    public static <T> ArrayList<T> convertArrayToList(T[] array) {
        if (array == null) {
            Log.e(TAG, "array is null.");
            return null;
        }

        if (array.length > 0)
            return new ArrayList<>(Arrays.asList(array));

        return new ArrayList<>();
    }
}
