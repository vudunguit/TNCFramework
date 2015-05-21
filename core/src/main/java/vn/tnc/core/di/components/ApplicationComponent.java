package vn.tnc.core.di.components;

import javax.inject.Singleton;

import dagger.Component;
import vn.tnc.core.base.mvp.BaseActivity;
import vn.tnc.core.di.modules.ApplicationModule;

/**
 * Created by USER on 5/20/2015.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(BaseActivity baseActivity);
}
