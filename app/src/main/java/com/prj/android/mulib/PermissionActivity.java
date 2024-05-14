package com.prj.android.mulib;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;

import com.mumu.mulib.android.MuPermission;
import com.mumu.mulib.android.window.MuDialog;
import com.mumu.mulib.android.window.MuToast;
import com.prj.mulib.R;

public class PermissionActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission);
        setTopTitle("申请权限");
    }

    public void defaultP(View view) {

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA}, 200);
        } else {
            MuToast.show(this, "有权限");
        }

    }

    private void showPermissionSettingDialog() {
        MuDialog muDialog = new MuDialog(this);
        muDialog.setMessage("请设置摄像头使用权限\n\n在跳转的页面中选择”应用权限“，再选择麦克风设置权限");
        muDialog.setBtnClose("关闭", view1 -> {
            muDialog.dismiss();
        });
        muDialog.setBtnOk("设置", view1 -> {
            Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", getPackageName(), null);
            intent.setData(uri);
            startActivityForResult(intent, 200);
            muDialog.dismiss();
        });
        muDialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 200) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // 用户授予了权限
                // Your code here
            } else {
                // 如果权限被拒绝，检查用户是否选择了不再询问
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    if (!shouldShowRequestPermissionRationale(Manifest.permission.CAMERA)) {
                        // 用户选择了不再询问，需要引导用户前往设置界面开启权限
                        showPermissionSettingDialog();
                    } else {
                        // 用户拒绝了权限请求
                        // 提示用户权限对于应用的重要性，并再次请求权限
                    }
                }
            }
        }
    }

}