package vn.tnc.tncframework.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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
import vn.tnc.tncframework.R;
import vn.tnc.tncframework.presenter.UserListPresenter;
import vn.tnc.tncframework.ui.activities.UsersActivity;
import vn.tnc.tncframework.ui.view.UserListView;

/**
 * Created by USER on 5/20/2015.
 */
public class UserListFragment extends BaseFragment implements UserListView{
    @InjectView(R.id.pbLoading)
    ProgressBar pbLoading;
    @InjectView(R.id.llErrorLoadUsers)
    LinearLayout llErrorLoadUsers;
    @InjectView(R.id.btnRetryLoadUsers)
    Button btnRetryLoadUsers;
    @InjectView(R.id.tvErrorLoadUsers)
    TextView tvErrorLoadUsers;
    @InjectView(R.id.rvUsers)
    RecyclerView rvUsers;

    @Inject
    UserListPresenter userListPresenter;
    @Override
    public void showLoading() {
        pbLoading.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        pbLoading.setVisibility(View.GONE);
    }

    @Override
    public void showRetry() {
        llErrorLoadUsers.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideRetry() {
        llErrorLoadUsers.setVisibility(View.GONE);
    }

    @Override
    public void showError(String error) {
        tvErrorLoadUsers.setText(error);
    }

    @Override
    public void renderUserList() {

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_users, container, false);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        userListPresenter.resume();
    }

    @Override
    protected void onInjected() {
        userListPresenter.setView(this);

        final StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvUsers.setLayoutManager(staggeredGridLayoutManager);

    }

    @Override
    protected void injectDependencies() {
        ((UsersActivity)getActivity()).getComponent().inject(this);
    }
}
