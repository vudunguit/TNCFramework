package vn.tnc.core.di.components;

import android.app.Activity;

import dagger.Component;
import vn.tnc.core.di.PerActivity;
import vn.tnc.core.di.modules.ActivityModule;
import vn.tnc.core.di.modules.ApplicationModule;

/**
 * Created by USER on 5/20/2015.
 */
@PerActivity
@Component(
        dependencies = ApplicationComponent.class,
        modules = ActivityModule.class
)
public interface ActivityComponent {
    Activity activity();
}
