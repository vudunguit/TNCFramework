package vn.tnc.tncframework.ui.fragments;

import android.os.Bundle;
import android.text.TextUtils;

import vn.tnc.core.base.mvp.BaseFragment;
import vn.tnc.tncframework.ui.activities.UsersActivity;

/**
 * Created by USER on 5/20/2015.
 */
public class UserDetailFragment extends BaseFragment{

    private static final String KEY_USERNAME = "user_detail_fragment_username";
    public static UserDetailFragment newInstance(String username){
        final UserDetailFragment fragment = new UserDetailFragment();
        if(TextUtils.isEmpty(username)){
            throw new NullPointerException("username must not be null or empty");
        }
        final Bundle args = new Bundle();
        args.putString(KEY_USERNAME, username);
        fragment.setArguments(args);
        return fragment;
    }
    @Override
    protected void onInjected() {

    }

    @Override
    protected void injectDependencies() {
        ((UsersActivity)getActivity()).getComponent().inject(this);
    }
}
