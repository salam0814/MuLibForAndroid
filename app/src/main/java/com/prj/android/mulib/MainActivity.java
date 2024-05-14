package com.prj.android.mulib;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Icon;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mumu.mulib.android.boot.Shortcut;
import com.mumu.mulib.android.window.MuToast;
import com.prj.mulib.R;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hindBack(true);
    }

    public void toToast(View view) {
        startActivity(new Intent(this, ToastActivity.class));
    }

    public void toDialig(View view) {
        startActivity(new Intent(this, DialogActivity.class));
    }

    public void toRecoding(View view) {
        startActivity(new Intent(this, AudioRecoderActivity.class));

    }

    public void toShotCut(View view) {
        Shortcut shortcut = new Shortcut();
        //shortcut.addShortcutToHomeScreen(MainActivity.this, "测试",0 ,ShotCutActivity.class);
        //shortcut.createShortcut(this, ShotCutActivity.class);
        //shortcut.CreateShotCut(this, ShotCutActivity.class, "测试快捷方式", "");

        //startActivity(new Intent(this, ShotCutActivity.class));

        //addShortcutToDesktop2(this, "ggg", null, ToastActivity.class);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            test();
        }
    }

    public  void addShortcutToDesktop(Context context, String shortcutName, Drawable iconDrawable, Class<?> launchActivity) {
        Intent shortcutIntent = new Intent(context, launchActivity);
        shortcutIntent.setAction(Intent.ACTION_MAIN);

        Intent addIntent = new Intent();
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, shortcutName);
        addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        context.sendBroadcast(addIntent);
    }


    private Context context = this;
    public static void addShortcutToDesktop2(Context context, String shortcutName, Drawable iconDrawable, Class<?> launchActivity) {
        Intent shortcutIntent = new Intent(context, launchActivity);
        shortcutIntent.setAction(Intent.ACTION_MAIN);

        Intent addIntent = new Intent();
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, shortcutName);
        //addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, iconDrawable);
        addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        context.sendBroadcast(addIntent);

        // 注册广播接收器来监听是否成功创建快捷方式
        BroadcastReceiver receiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                // 处理广播接收到的信息，判断是否成功创建快捷方式
                MuToast.show(context, "创建");
            }
        };
        context.registerReceiver(receiver, new IntentFilter("com.android.launcher.action.INSTALL_SHORTCUT"));
    }

    public void toPer(View view) {
        startActivity(new Intent(this, PermissionActivity.class));
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void test() {
        ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);
        if (shortcutManager.isRequestPinShortcutSupported()) {
            Intent intent = new Intent(this, ShotCutActivity.class);
            intent.setAction("com.example.shortcutstest.NAVIGATION");
            ShortcutInfo pinShortcutInfo = new ShortcutInfo.Builder(this, "navigation")
                    .setShortLabel("导航")
                    .setLongLabel("导航")
                    .setIcon(Icon.createWithResource(this, R.drawable.img_icon_test))
                    .setIntent(intent)
                    .build();
// 注册固定快捷方式成功广播
            IntentFilter intentFilter = new IntentFilter();
            intentFilter.addAction("com.example.shortcutstest.PINNED_BROADCAST");
            PinnedReceiver receiver = new PinnedReceiver();
            registerReceiver(receiver, intentFilter);

            Intent pinnedShortcutCallbackIntent = new Intent("com.example.shortcutstest.PINNED_BROADCAST");
            PendingIntent successCallback = PendingIntent.getBroadcast(this, 0,
                    pinnedShortcutCallbackIntent, 0);
            shortcutManager.requestPinShortcut(pinShortcutInfo, successCallback.getIntentSender());

        }

    }

    public class PinnedReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            Toast.makeText(context, "固定快捷方式成功", Toast.LENGTH_SHORT).show();
        }
    }

}