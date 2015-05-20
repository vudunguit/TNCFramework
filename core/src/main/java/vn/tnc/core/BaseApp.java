package vn.tnc.core;

import android.app.Application;

import vn.tnc.core.strictmode.PlatformImplementStrictMode;

/**
 * Created by USER on 5/19/2015.
 */
public abstract class BaseApp extends Application{

    protected abstract boolean isDebug();

    @Override public void onCreate() {
        super.onCreate();
        if(isDebug()){
            PlatformImplementStrictMode.getStrictMode().enableStrictMode();
        }
    }
}
