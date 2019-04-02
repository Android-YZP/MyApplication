package com.com1075.myapplication;

import android.view.View;
import android.widget.TextView;

import com.com1075.library.base.BaseActivity;
import com.tencent.bugly.beta.Beta;

public class MainActivity extends BaseActivity implements ITestContract {
    private TestPresenter mTestPresenter = new TestPresenter(this, this);
    private TextView textView;

    @Override
    protected int getContentViewId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        textView = findViewById(R.id.tv);
    }

    @Override
    protected void initData() {
        mTestPresenter.HttpLogin("");
        textView.setText("2222222");
    }

    @Override
    protected void setListener() {
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Beta.checkUpgrade(true,false);
            }
        });
    }

    @Override
    public void LoadingData() {

    }

    @Override
    public void LoadingDataFail(String result) {

    }

    @Override
    public void LoadingDataSuccess(String result) {
//        ToastUtils.showLongToast(this,result);
    }
}
