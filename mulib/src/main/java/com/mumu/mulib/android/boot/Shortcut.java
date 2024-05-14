package com.mumu.mulib.android.boot;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Parcelable;

import com.mumu.prj.android.mulib.R;

/**
 * @Author: MuMu
 * @Date: 2024/5/10 15:26
 */
public class Shortcut {
    public void createShortcut(Context context, Class<?> c) {
        Intent shortcutIntent = new Intent(context.getApplicationContext(), c);
        shortcutIntent.setAction(Intent.ACTION_MAIN);

        Intent addIntent = new Intent();
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, "应用名称2");
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                Intent.ShortcutIconResource.fromContext(context.getApplicationContext(),
                        R.drawable.img_icon_tips));

        addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        context.getApplicationContext().sendBroadcast(addIntent);
    }



    public void CreateShotCut(final Context context, final Class<?> clazz, final String name, final String image) {

        Intent shortcutIntent = new Intent(Intent.ACTION_MAIN);
        // 加入action,和category之后，程序卸载的时候才会主动将该快捷方式也卸载
        shortcutIntent.addCategory(Intent.CATEGORY_LAUNCHER);
        shortcutIntent.setClass(context, clazz);
        /**
         * 创建一个Bundle对象让其保存将要传递的值
         */
        Bundle bundle = new Bundle();
        bundle.putString("userId", "test--123");
        shortcutIntent.putExtras(bundle);
        /**
         * 设置这条属性，可以使点击快捷方式后关闭其他的任务栈的其他activity，然后创建指定的acticity
         */
        shortcutIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // 创建快捷方式的Intent
        Intent shortcut = new Intent(Intent.ACTION_CREATE_SHORTCUT);
        // 不允许重复创建
        shortcut.putExtra("duplicate", false);
        // 点击快捷图片，运行的程序主入口
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        // 需要现实的名称
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_NAME, name);

        // 快捷图片
        Parcelable icon = Intent.ShortcutIconResource.fromContext(context.getApplicationContext(), R.drawable.img_icon_tips);
        shortcut.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE, icon);
        shortcut.setAction("com.android.launcher.action.INSTALL_SHORTCUT");
        context.sendBroadcast(shortcut);
    }


    public  void addShortcutToHomeScreen(Context context, String shortcutName, int iconResId, Class<?> targetClass) {
        // 创建指向目标Activity的Intent
        Intent shortcutIntent = new Intent(context, targetClass);
        shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        shortcutIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        // 创建添加快捷方式的Intent
        Intent addIntent = new Intent();
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_INTENT, shortcutIntent);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_NAME, shortcutName);
        addIntent.putExtra(Intent.EXTRA_SHORTCUT_ICON_RESOURCE,
                Intent.ShortcutIconResource.fromContext(context, R.drawable.img_icon_tips));

        // 设置动作，但请注意，不是所有启动器都支持这个操作
        addIntent.setAction("com.android.launcher.action.INSTALL_SHORTCUT");

        // 发送广播
        context.sendBroadcast(addIntent);

        // 可选：你可以检查是否发送成功，但这通常取决于启动器的实现
        // ...
    }
}
