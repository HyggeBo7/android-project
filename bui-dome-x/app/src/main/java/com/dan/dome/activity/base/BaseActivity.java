package com.dan.dome.activity.base;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.WindowManager;

import com.dan.dome.R;
import com.dan.library.util.DialogUtils;
import com.dan.library.util.StatusBarUtils;

import androidx.annotation.Nullable;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Dan on 2018/10/17 13:06
 */
@SuppressLint("Registered")
public class BaseActivity extends Activity {

    public Dialog mLoading;
    protected Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtils.setWindowStatusBarColor(this, R.color.head_background_back_all);
        ActivityCollector.addActivity(this);

        mLoading = DialogUtils.createLoadingDialog(this);
        //软件盘自动打开
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
    }

    /**
     * setContentView 这个方法后执行此方法
     */
    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        //绑定当前视图
        unbinder = ButterKnife.bind(this);
    }

    /**
     * 解除绑定
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (unbinder != null) {
            unbinder.unbind();
            unbinder = null;
        }
        ActivityCollector.removeActivity(this);
    }

}
