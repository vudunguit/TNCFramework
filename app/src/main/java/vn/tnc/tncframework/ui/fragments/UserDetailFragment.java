package vn.tnc.tncframework.ui.fragments;

import vn.tnc.core.base.mvp.BaseFragment;
import vn.tnc.tncframework.ui.activities.UsersActivity;

/**
 * Created by USER on 5/20/2015.
 */
public class UserDetailFragment extends BaseFragment{

    @Override
    protected void onInjected() {

    }

    @Override
    protected void injectDependencies() {
        ((UsersActivity)getActivity()).getComponent().inject(this);
    }
}
