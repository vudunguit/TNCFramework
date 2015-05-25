package vn.tnc.core.di.modules;

import android.app.Application;
import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by USER on 5/20/2015.
 */
@Module
public class ApplicationModule {

    private final Application application;
    public ApplicationModule(Application application){
        this.application = application;
    }

    @Provides
    @Singleton
    Application provideApplicationContext(){
        return application;
    }





}
