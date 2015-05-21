package vn.tnc.tncframework.di.components;

import dagger.Component;
import vn.tnc.core.base.mvp.BaseActivity;
import vn.tnc.core.di.PerActivity;
import vn.tnc.core.di.components.ActivityComponent;
import vn.tnc.core.di.components.ApplicationComponent;
import vn.tnc.core.di.modules.ActivityModule;
import vn.tnc.tncframework.ui.fragments.UserDetailFragment;
import vn.tnc.tncframework.ui.fragments.UserListFragment;

/**
 * Created by USER on 5/20/2015.
 */
@PerActivity
@Component(
        dependencies = ApplicationComponent.class,
        modules = ActivityModule.class
)
public interface UserComponent extends ActivityComponent{

    void inject(UserListFragment userListFragment);
    void inject(UserDetailFragment userDetailFrament);
}
