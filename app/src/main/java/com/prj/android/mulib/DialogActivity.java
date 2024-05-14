package com.prj.android.mulib;

import android.os.Bundle;
import android.view.View;

import com.mumu.mulib.android.window.MuDialog;
import com.mumu.mulib.android.window.MuToast;
import com.prj.mulib.R;

public class DialogActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dialog);
        setTopTitle("对话框测试");
    }

    public void test(View view) {
        MuDialog showDialog = new MuDialog(this);
        showDialog.setTitle("对话框测试");
        showDialog.setMessage("v和GIF吧v开几波v快来吧vv编辑v发表， 贝多芬v播放的");
        showDialog.show();
        showDialog.setBtnClose("关闭", null);
        showDialog.setBtnOk("好的", view1 -> {
            MuToast.show(DialogActivity.this, "你确认了");
            showDialog.dismiss();
        });
    }
}