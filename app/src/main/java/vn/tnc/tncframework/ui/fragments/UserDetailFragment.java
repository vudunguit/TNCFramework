package vn.tnc.tncframework.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.InjectView;
import vn.tnc.core.base.mvp.BaseFragment;
import vn.tnc.data.api.model.response.User;
import vn.tnc.tncframework.R;
import vn.tnc.tncframework.presenter.UserDetailPresenter;
import vn.tnc.tncframework.ui.activities.UsersActivity;
import vn.tnc.tncframework.ui.utils.OnFragmentInteractionListener;
import vn.tnc.tncframework.ui.view.UserDetailView;

/**
 * Created by USER on 5/20/2015.
 */
public class UserDetailFragment extends BaseFragment implements UserDetailView{

    @InjectView(R.id.pbLoadingDetail)
    ProgressBar pbLoadingDetail;
    @InjectView(R.id.llErrorLoadDetail)
    LinearLayout llErrorLoadDetail;
    @InjectView(R.id.btnRetryLoadDetail)
    Button btnRetryLoadDetail;
    @InjectView(R.id.tvErrorLoadDetail)
    TextView tvErrorLoadDetail;

    @Inject
    UserDetailPresenter detailPresenter;

    private String mUsername;
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

    private OnFragmentInteractionListener mOnInterationListener;
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mOnInterationListener = (OnFragmentInteractionListener) activity;
        }catch (ClassCastException e){
            throw new ClassCastException(activity.toString() + "must implement OnFragmentInteractionListener");
        }
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_detail, container, false);
        return view;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle args = getArguments();
        if(args != null) {
            mUsername = args.getString(KEY_USERNAME);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        mOnInterationListener.showDrawerToggle(false);
    }

    @Override
    protected void onInjected() {
        detailPresenter.setView(this);
        detailPresenter.setUsername(mUsername);
        detailPresenter.resume();
    }

    @Override
    public void render(User user) {

    }

    @Override
    public void showLoading() {
        pbLoadingDetail.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pbLoadingDetail.setVisibility(View.GONE);
    }

    @Override
    public void showRetry() {
        llErrorLoadDetail.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        llErrorLoadDetail.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        tvErrorLoadDetail.setText(error);
    }

    @Override
    protected void injectDependencies() {
        ((UsersActivity)getActivity()).getComponent().inject(this);
    }
}
