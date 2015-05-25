package vn.tnc.core.di.components;

import android.app.Application;

import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Component;
import vn.tnc.core.base.mvp.BaseActivity;
import vn.tnc.core.di.modules.ApplicationModule;
import vn.tnc.core.di.modules.UtilModule;
import vn.tnc.core.utils.OttoBus;

/**
 * Created by USER on 5/20/2015.
 */
@Singleton
@Component(
        modules = {UtilModule.class, ApplicationModule.class}
)
public interface ApplicationComponent {
    //Exposed to sub-graphs.
    Application application();
    Bus ottoBus();
}
