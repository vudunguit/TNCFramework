package vn.tnc.core.base.mvp;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import butterknife.ButterKnife;
import vn.tnc.core.BaseApp;
import vn.tnc.core.R;
import vn.tnc.core.di.components.ApplicationComponent;


public abstract class BaseActivity extends AppCompatActivity {

    protected abstract @LayoutRes int layoutId();

    protected abstract void injectDependencies();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle params = getIntent().getExtras();
        if(params != null){
            onExtrasParams(params);
        }
        super.onCreate(savedInstanceState);

        injectDependencies();

        final int layoutId = layoutId();
        if(layoutId == 0){
            final View contentView = getContentView();
            if(contentView != null){
                setContentView(contentView);
                injectViews();
            }
        }else{
            setContentView(layoutId);
            injectViews();
        }

    }

    protected View getContentView(){
        return null;
    }

    protected void onExtrasParams(@NonNull Bundle params){
        //default no implementation
    }

    protected void injectViews(){
        ButterKnife.inject(this);
    }



}
