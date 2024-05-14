package com.mumu.mulib.android.window;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mumu.prj.android.mulib.R;

/**
 * @Author: MuMu
 * @Date: 2024/5/7 11:07
 */
public class MuToast {
    private MuToast(){}

    public enum Type{
        TIPS, SUCCEED, WARNING, ERROR
    }

    public enum Align{
        CENTER, TOP, RIGHT, BOTTOM, LEFT
    }




    // 默认toast显示
    public static void show(Context context, String msg){
        // 获取自定义的布局
        View view = LayoutInflater.from(context).inflate(R.layout.layout_toast_default, null);
        // 获取自定义布局中的的TextView，用来显示文字内容
        TextView tv = view.findViewById(R.id.tvToast);
        tv.setText(msg);  // 设置提示内容
        // 声明并设置Toast的相关属性
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


    // 不同类型toast显示
    public static void show(Context context, String msg, Type type){
        View view = null;
        // 获取自定义的布局
        switch (type) {
            case TIPS:
                view = LayoutInflater.from(context).inflate(R.layout.layout_toast_tips, null);
                break;

            case SUCCEED:
                view = LayoutInflater.from(context).inflate(R.layout.layout_toast_succeed, null);
                break;

            case WARNING:
                view = LayoutInflater.from(context).inflate(R.layout.layout_toast_warning, null);
                break;

            case ERROR:
                view = LayoutInflater.from(context).inflate(R.layout.layout_toast_error, null);
                break;
        }


        // 获取自定义布局中的的TextView，用来显示文字内容
        TextView tv = view.findViewById(R.id.tvToast);
        tv.setText(msg);  // 设置提示内容
        // 声明并设置Toast的相关属性
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }


    // 不同类型toast显示, 设置显示位置
    public static void show(Context context, String msg, Type type, Align align){
        View view = null;
        // 获取自定义的布局
        switch (type) {
            case TIPS:
                view = LayoutInflater.from(context).inflate(R.layout.layout_toast_tips, null);
                break;

            case SUCCEED:
                view = LayoutInflater.from(context).inflate(R.layout.layout_toast_succeed, null);
                break;

            case WARNING:
                view = LayoutInflater.from(context).inflate(R.layout.layout_toast_warning, null);
                break;

            case ERROR:
                view = LayoutInflater.from(context).inflate(R.layout.layout_toast_error, null);
                break;
        }


        // 获取自定义布局中的的TextView，用来显示文字内容
        TextView tv = view.findViewById(R.id.tvToast);
        tv.setText(msg);  // 设置提示内容
        // 声明并设置Toast的相关属性
        Toast toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setView(view);

        // 设置显示位置
        switch (align) {
            case CENTER:
                toast.setGravity(Gravity.CENTER, 0, 0);
                break;

            case TOP:
                toast.setGravity(Gravity.TOP, 0, 0);
                break;

            case RIGHT:
                toast.setGravity(Gravity.RIGHT, 0, 0);
                break;

            case BOTTOM:
                toast.setGravity(Gravity.BOTTOM, 0, 0);
                break;

            case LEFT:
                toast.setGravity(Gravity.LEFT, 0, 0);
                break;
        }

        toast.show();
    }

}
