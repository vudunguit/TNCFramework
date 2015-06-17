package vn.tnc.tncframework.ui.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
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
import vn.tnc.tncframework.ui.utils.OnFragmentInteractionListener;

/**
 * Created by USER on 5/20/2015.
 */
public class UsersActivity extends BaseActivity implements HasComponent<UserComponent>, OnFragmentInteractionListener{
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

        setUpActionBarToolbar();

        fragmentNavigator = FragmentNavigator.create(this, R.id.flContent);
        if(savedInstanceState == null){
            fragmentNavigator.showScreen(new UserListFragment(), false);
            setTitle("List");
        }
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        setUpNavigationDrawer();
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
                setTitle(user.login);
                break;
        }
    }

    @Override
    public void showDrawerToggle(boolean isShow) {

        if(isShow) {
            toolbar.setNavigationIcon(R.drawable.menu_icon);
        }else{
            toolbar.setNavigationIcon(R.drawable.ic_up);
        }

    }

    private void setUpActionBarToolbar(){
        if(toolbar != null) {
            setSupportActionBar(toolbar);
        }
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setUpNavigationDrawer() {

        toolbar.setNavigationIcon(R.drawable.menu_icon);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(fragmentNavigator.isEmptyStack()) {
                    drawerLayout.openDrawer(Gravity.START);
                }else{
                    onBackPressed();
                }
            }
        });

        drawerLayout.setDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

            }

            @Override
            public void onDrawerOpened(View drawerView) {

            }

            @Override
            public void onDrawerClosed(View drawerView) {

            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });
        //populate the nav drawer with the correct items
        populateNavDrawer();


    }

    private void populateNavDrawer(){
        // setup item list nav drawer
        //...

        createNavDrawerItems();

    }

    private void createNavDrawerItems(){
        // bind data nav drawer to drawer view
        // ...
    }

    private void setTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void onBackPressed() {
        if(isNavDrawerOpen()){
            closeNavDrawer();
        }else if(!fragmentNavigator.navigateBack()) {
            super.onBackPressed();
        }
    }

    private boolean isNavDrawerOpen(){
        return drawerLayout != null && drawerLayout.isDrawerOpen(Gravity.START);
    }

    private void closeNavDrawer(){
        if(drawerLayout != null){
            drawerLayout.closeDrawer(Gravity.START);
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
