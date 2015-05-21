package vn.tnc.tncframework.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import vn.tnc.core.base.mvp.BaseFragment;
import vn.tnc.tncframework.R;
import vn.tnc.tncframework.ui.activities.UsersActivity;

/**
 * Created by USER on 5/20/2015.
 */
public class UserListFragment extends BaseFragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        return view;
    }

    @Override
    protected void onInjected() {

    }

    @Override
    protected void injectDependencies() {
        ((UsersActivity)getActivity()).getComponent().inject(this);
    }
}
