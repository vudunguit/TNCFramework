package vn.tnc.core.base.mvp;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import butterknife.ButterKnife;
import vn.tnc.core.di.HasComponent;

/**
 * Created by USER on 5/20/2015.
 */
public abstract class BaseFragment extends Fragment{

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        injectDependencies();
        injectViews(view);
        onInjected();

    }

    protected abstract void onInjected();

    protected abstract void injectDependencies();

    protected void injectViews(View view){
        ButterKnife.inject(this, view);
    }


}
