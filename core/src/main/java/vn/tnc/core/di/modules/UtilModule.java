package vn.tnc.core.di.modules;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import vn.tnc.core.di.PerActivity;
import vn.tnc.core.utils.OttoBus;

/**
 * Created by USER on 5/22/2015.
 */

@Module
public class UtilModule {
    public UtilModule(){

    }
    @Singleton
    @Provides
    public Bus provideOttoBus(){
        return new Bus();
    }
}
