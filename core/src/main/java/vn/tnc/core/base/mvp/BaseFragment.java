package vn.tnc.core.base.mvp;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import vn.tnc.core.di.HasComponent;

/**
 * Created by USER on 5/20/2015.
 */
public abstract class BaseFragment extends Fragment{


    protected <C> C getComponent(Class<C> componentType){
        return componentType.cast(((HasComponent<C>)getActivity()).getComponent());
    }

}
