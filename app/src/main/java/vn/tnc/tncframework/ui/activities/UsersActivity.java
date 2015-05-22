package vn.tnc.tncframework.ui.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.squareup.otto.Bus;

import javax.inject.Inject;

import butterknife.InjectView;
import vn.tnc.core.base.mvp.BaseActivity;
import vn.tnc.core.base.navigator.FragmentNavigator;
import vn.tnc.core.di.HasComponent;
import vn.tnc.core.di.components.ApplicationComponent;
import vn.tnc.tncframework.App;
import vn.tnc.tncframework.R;
import vn.tnc.tncframework.di.components.DaggerUserComponent;
import vn.tnc.tncframework.di.components.UserComponent;
import vn.tnc.tncframework.ui.fragments.UserListFragment;

/**
 * Created by USER on 5/20/2015.
 */
public class UsersActivity extends BaseActivity implements HasComponent<UserComponent>{
    private FragmentNavigator fragmentNavigator;
    private UserComponent userComponent;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;

    @Inject
    Bus bus;

    @Override protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void injectDependencies() {
        userComponent = DaggerUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        getApplicationComponent().inject(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setTitle("List");

        fragmentNavigator = FragmentNavigator.create(this, R.id.flContent);
        if(savedInstanceState == null){
            fragmentNavigator.showScreen(new UserListFragment(), false);
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        bus.register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        bus.unregister(this);
    }

    @Override
    public UserComponent getComponent() {
        return userComponent;
    }

    protected ApplicationComponent getApplicationComponent(){
        return ((App)getApplication()).getApplicationComponent();
    }
}
