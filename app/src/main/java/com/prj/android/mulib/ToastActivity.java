package com.prj.android.mulib;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioGroup;

import com.mumu.mulib.android.window.MuToast;
import com.prj.mulib.R;

public class ToastActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_toast);
        setTopTitle("toast测试");

        setTypes();
        setAlign();

    }

    public void defaultToast(View view) {
        MuToast.show(this, "toast默认显示测试...");
    }

    MuToast.Type type = MuToast.Type.TIPS;
    private void setTypes(){
        RadioGroup toastType  = findViewById(R.id.toastType);
        toastType.setOnCheckedChangeListener((group, checkedId)->{
            if (checkedId==R.id.toastTips) type = MuToast.Type.TIPS;
            else if (checkedId==R.id.toastSucceed) type = MuToast.Type.SUCCEED;
            else if (checkedId==R.id.toastWarning) type = MuToast.Type.WARNING;
            else if (checkedId==R.id.toastError) type = MuToast.Type.ERROR;
        });
    }

    private void setAlign(){
        RadioGroup toastAlign = findViewById(R.id.toastAlign);
        toastAlign.setOnCheckedChangeListener((group, checkedId)->{
            if (checkedId==R.id.toastLeft) align = MuToast.Align.LEFT;
            if (checkedId==R.id.toastTop) align = MuToast.Align.TOP;
            if (checkedId==R.id.toastCenter) align = MuToast.Align.CENTER;
            if (checkedId==R.id.toastTBottom) align = MuToast.Align.BOTTOM;
            if (checkedId==R.id.toastRight) align = MuToast.Align.RIGHT;
        });
    }


    MuToast.Align align = MuToast.Align.CENTER;

    public void customToast(View view) {
        MuToast.show(this, "自定义toast显示测试...", type, align);
    }
}