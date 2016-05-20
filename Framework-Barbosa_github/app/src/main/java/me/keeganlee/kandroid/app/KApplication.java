package me.keeganlee.kandroid.app;

import android.app.Application;

import me.keeganlee.kandroid.core.AppAction;
import me.keeganlee.kandroid.core.AppActionImpl;

/**
 * Application类，应用级别的操作放在这里
 * Created by Tian on 2016/5/3.
 */
public class KApplication extends Application {
    private AppAction appAction;

    @Override
    public void onCreate() {
        super.onCreate();
        appAction = new AppActionImpl(this);
    }

    public AppAction getAppAction() {
        return appAction;
    }
}
