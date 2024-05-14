package com.mumu.mulib.android;

import static androidx.core.app.ActivityCompat.requestPermissions;
import static androidx.core.app.ActivityCompat.shouldShowRequestPermissionRationale;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

/**
 * @Author: MuMu
 * @Date: 2024/5/9 14:31
 */
public class MuPermission {
    public static boolean havePermission(Context context, String permission){
        return  (PackageManager.PERMISSION_GRANTED == ActivityCompat
                .checkSelfPermission(context, permission));
    }


    public static boolean t(Activity activity, String permission){
        return shouldShowRequestPermissionRationale (activity, permission);
    }

    public static boolean ttt(Activity activity, String permission){
        return !havePermission(activity, permission) && !t(activity, permission);
    }


    public static void requestPer (Activity activity, String[] permissions, int requestCode){
        requestPermissions(activity, permissions, requestCode);
    }
}
