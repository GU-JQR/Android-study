package com.example.jqr.utils;

import android.content.Context;
import android.widget.Toast;

public class CommonUtils {
    public static void MyToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
