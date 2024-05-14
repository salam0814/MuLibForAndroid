package com.prj.android.mulib;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.prj.mulib.R;

/**
 * @Author: MuMu
 * @Date: 2024/5/7 14:01
 */
public class BaseActivity extends AppCompatActivity {

    @Override
    protected void onStart() {
        super.onStart();
        setBackListener(null);
    }

    protected void setTopTitle(String title){
        TextView tvTitle = findViewById(R.id.tvTitle);
        if (tvTitle!=null){
            tvTitle.setText(title);
        }
    }

    // 顶部返回按钮事件
    protected void setBackListener(View.OnClickListener listener){
        Button btnBack = findViewById(R.id.btnBack);
        if (btnBack==null) return;
        if (listener!=null) btnBack.setOnClickListener(listener);
        else btnBack.setOnClickListener(view -> onBackPressed());
    }

    protected void hindBack(boolean vis){
        Button btnBack = findViewById(R.id.btnBack);
        if (btnBack==null) return;
        if (vis) btnBack.setVisibility(View.GONE);
        else btnBack.setVisibility(View.VISIBLE);
    }

}
