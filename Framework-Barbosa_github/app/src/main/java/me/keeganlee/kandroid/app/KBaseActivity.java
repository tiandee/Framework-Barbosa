package me.keeganlee.kandroid.app;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import me.keeganlee.kandroid.core.AppAction;

/**
 *
 * Created by Tian on 2016/5/3.
 */
public abstract class KBaseActivity extends FragmentActivity {
    public Context context;
    //应用全局实例
    public KApplication application;
    //核心层的appAction
    public AppAction appAction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        application = (KApplication) this.getApplication();
        appAction = application.getAppAction();
    }
}
