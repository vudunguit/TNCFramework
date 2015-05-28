package vn.tnc.core.di.modules;

import android.app.Activity;
import android.view.LayoutInflater;

import dagger.Module;
import dagger.Provides;
import vn.tnc.core.di.PerActivity;

/**
 * Created by USER on 5/20/2015.
 */
@Module
public class ActivityModule {

    private final Activity activity;

    public ActivityModule (Activity activity){
        this.activity = activity;
    }

    @Provides
    @PerActivity
    Activity provideActivity(){
        return this.activity;
    }

    @Provides
    @PerActivity
    public LayoutInflater provideLayoutInflater() {
        return activity.getLayoutInflater();
    }
}
