package vn.tnc.tncframework.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.otto.Bus;

import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.OnClick;
import vn.tnc.core.base.mvp.BaseFragment;
import vn.tnc.data.api.model.response.User;
import vn.tnc.tncframework.R;
import vn.tnc.tncframework.bus.Event;
import vn.tnc.tncframework.presenter.UserListPresenter;
import vn.tnc.tncframework.ui.activities.UsersActivity;
import vn.tnc.tncframework.ui.adapters.UsersAdapter;
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
    @InjectView(R.id.srlUsers)
    SwipeRefreshLayout srlUsers;
    @Inject
    UserListPresenter userListPresenter;

    @Inject
    UsersAdapter usersAdapter;
    @Inject
    Bus bus;
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
    public void renderUserList(List<User> users) {
        usersAdapter.changeDataSet(users);
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
        bus.register(this);
        userListPresenter.resume();
    }

    @Override
    public void onPause() {
        super.onPause();
        bus.unregister(this);
    }

    @Override
    protected void onInjected() {
        userListPresenter.setView(this);

        final StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvUsers.setLayoutManager(staggeredGridLayoutManager);
        rvUsers.setAdapter(usersAdapter);
        srlUsers.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                setRefreshed();
            }
        });

        usersAdapter.setOnItemClickListener(new UsersAdapter.OnItemClickListener() {
            @Override
            public void onItemClicked(User user, ImageView imgView) {

                bus.post(Event.USER_DETAIL.withExtras(user));
            }
        });
    }

    @OnClick(R.id.btnRetryLoadUsers)
    public void onClickButtonRetry(){
        userListPresenter.resume();
    }

    private void setRefreshed(){
        if(srlUsers.isRefreshing()){
            srlUsers.setRefreshing(false);
        }
    }

    @Override
    protected void injectDependencies() {
        ((UsersActivity)getActivity()).getComponent().inject(this);
    }
}
