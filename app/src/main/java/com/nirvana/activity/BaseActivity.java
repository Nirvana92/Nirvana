package com.nirvana.activity;

import android.app.Activity;
import android.os.Bundle;

import com.nirvana.application.MyApplication;

import butterknife.ButterKnife;

/**
 * Created by Nirvana on 2016/6/24.
 */
public abstract class BaseActivity extends Activity {
    protected MyApplication app;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());

        //app = (MyApplication) getApplication();

        ButterKnife.bind(this);
    }

    protected abstract int getLayout();
}
