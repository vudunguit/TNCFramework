package vn.tnc.tncframework.ui.activities;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Toast;


import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import butterknife.InjectView;
import timber.log.Timber;
import vn.tnc.core.base.mvp.BaseActivity;
import vn.tnc.core.base.navigator.FragmentNavigator;
import vn.tnc.core.di.HasComponent;
import vn.tnc.core.di.components.ApplicationComponent;
import vn.tnc.data.api.model.response.User;
import vn.tnc.tncframework.App;
import vn.tnc.tncframework.R;
import vn.tnc.core.utils.OttoBus;
import vn.tnc.tncframework.bus.Event;
import vn.tnc.tncframework.di.components.DaggerUserComponent;
import vn.tnc.tncframework.di.components.UserComponent;
import vn.tnc.tncframework.ui.fragments.UserDetailFragment;
import vn.tnc.tncframework.ui.fragments.UserListFragment;

/**
 * Created by USER on 5/20/2015.
 */
public class UsersActivity extends BaseActivity implements HasComponent<UserComponent>{
    private FragmentNavigator fragmentNavigator;
    private UserComponent userComponent;
    @InjectView(R.id.toolbar)
    Toolbar toolbar;
    @InjectView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @Inject
    Bus bus;

    private static final String TAG = UsersActivity.class.getSimpleName();

    @Override protected int layoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void injectDependencies() {
        userComponent = DaggerUserComponent.builder()
                .applicationComponent(getApplicationComponent())
                .activityModule(getActivityModule())
                .build();
        userComponent.inject(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.menu_icon);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(Gravity.START);
            }
        });
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

    @Subscribe
    public void onEvent(Event event){
        Timber.i("onEvent", event);
        switch (event){
            case USER_DETAIL:
                User user = (User)event.extras;
                fragmentNavigator.showScreen(UserDetailFragment.newInstance(user.login), true);
                break;
        }
    }

    @Override
    public void onBackPressed() {
        if(!fragmentNavigator.navigateBack()) {
            super.onBackPressed();
        }
    }

    @Override
    public UserComponent getComponent() {
        return userComponent;
    }

    protected ApplicationComponent getApplicationComponent(){
        return ((App)getApplication()).getApplicationComponent();
    }
}
