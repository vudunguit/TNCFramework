package vn.tnc.tncframework;

import android.content.Context;

import vn.tnc.core.BaseApp;
import vn.tnc.core.di.components.ApplicationComponent;
import vn.tnc.core.di.components.DaggerApplicationComponent;
import vn.tnc.core.di.modules.ApplicationModule;
import vn.tnc.core.di.modules.UtilModule;
import vn.tnc.data.DataModule;
import vn.tnc.data.api.ApiModule;

/**
 * Created by USER on 5/20/2015.
 */
public class App extends BaseApp{

    private ApplicationComponent applicationComponent;
    @Override protected boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    @Override public void onCreate() {
        super.onCreate();

        initInjector();
    }

    private void initInjector(){
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .utilModule(new UtilModule())
                .dataModule(new DataModule())
                .apiModule(new ApiModule())
                .build();
    }

    public ApplicationComponent getApplicationComponent(){
        return applicationComponent;
    }

    public static App get(Context context) {
        return (App) context.getApplicationContext();
    }
}
