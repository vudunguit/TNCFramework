package vn.tnc.tncframework.di.modules;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by USER on 5/22/2015.
 */
@Module
public class UtilModule {
    @Singleton
    @Provides
    public Bus provideOttoBus(){
        return new Bus();
    }
}
