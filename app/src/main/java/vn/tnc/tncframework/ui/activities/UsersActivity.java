package vn.tnc.tncframework.ui.activities;

import vn.tnc.core.base.mvp.BaseActivity;
import vn.tnc.core.di.HasComponent;
import vn.tnc.tncframework.App;
import vn.tnc.tncframework.R;
import vn.tnc.tncframework.di.components.UserComponent;

/**
 * Created by USER on 5/20/2015.
 */
public class UsersActivity extends BaseActivity implements HasComponent<UserComponent>{
    private UserComponent userComponent;
    @Override protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void injectDependencies() {
//        userComponent = Dagger
    }

    @Override
    public UserComponent getComponent() {
        return userComponent;
    }
}
