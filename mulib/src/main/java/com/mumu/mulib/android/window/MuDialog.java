package com.mumu.mulib.android.window;

import android.content.Context;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.mumu.prj.android.mulib.R;

/**
 * @Author: MuMu
 * @Date: 2024/5/7 16:54
 */
public class MuDialog {
    private final AlertDialog dialog;
    private final AlertDialog.Builder builder;
    private final TextView tvDialogTitle, tvDialogMessage;
    private final Button btnDialogClose, btnDialogOk;


    public MuDialog(Context context) {
        builder = new AlertDialog.Builder(context, R.style.CustomDialog);

        View dialogView = LayoutInflater.from(context).inflate(R.layout.layout_device_scan_dialog, null);
        builder.setView(dialogView);
        tvDialogTitle = dialogView.findViewById(R.id.tv_dialogTitle);
        tvDialogMessage = dialogView.findViewById(R.id.tv_dialogMessage);
        btnDialogClose = dialogView.findViewById(R.id.btnDialogClose);
        btnDialogOk = dialogView.findViewById(R.id.btnDialogOk);

        dialog = builder.create();
    }

    public boolean show(){
        dialog.show();
        Window window = dialog.getWindow();
        //背景和圆角
        window.setBackgroundDrawableResource(android.R.color.transparent);
        //弹窗时隐藏软键盘
        window.clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        return dialog.isShowing();
    }

    public void dismiss(){
        dialog.dismiss();
    }

    public void setCancelable(boolean state){
        dialog.setCancelable(state);
    }


    public void setTitle(String title){
        tvDialogTitle.setText(title);
    }


    public void setMessage(String msg){
        tvDialogMessage.setText(msg);
    }


    /**
     * @param text: 取消按钮文字
     * @param l: 取消按钮事件
     */
    public void setBtnClose(String text, View.OnClickListener l){
        btnDialogClose.setVisibility(View.VISIBLE);
        if (text.length()>0){
            btnDialogClose.setText(text);
        } else {
            btnDialogClose.setText("Close");
        }
        btnDialogClose.setOnClickListener(l==null? view -> dismiss() : l);
    }


    /**
     * @param text: 确认按钮文字
     * @param l: 取消按钮事件
     */
    public void setBtnOk(String text, View.OnClickListener l){
        btnDialogOk.setVisibility(View.VISIBLE);
        if (text.length()>0){
            btnDialogOk.setText(text);
        } else {
            btnDialogOk.setText("OK");
        }
        btnDialogOk.setOnClickListener(l==null? view -> dismiss() : l);
    }


    /**
     * 设置对话框宽度和高度比例，要在show方法之前使用
     * @param width: 对话框宽度比例（value：0.1~1）
     * @param height: 对话框高度比例（value：0.1~1）
     */
    public void setDialogSize(float width, float height){
        Window window = dialog.getWindow();
        WindowManager.LayoutParams params= window.getAttributes();
        WindowManager manager= window.getWindowManager();
        Display display= manager.getDefaultDisplay();
        if (width>0f && width<1.01f){
            params.width= (int) (display.getWidth()* width);
        }
        if (height>0f && height<1.01f){
            params.height= (int) (display.getHeight()* height);
        }
        window.setAttributes(params);
    }
}
