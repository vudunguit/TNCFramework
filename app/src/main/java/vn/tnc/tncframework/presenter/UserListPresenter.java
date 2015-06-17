package vn.tnc.tncframework.presenter;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;
import vn.tnc.core.base.mvp.BasePresenter;
import vn.tnc.data.api.ApiService;
import vn.tnc.data.api.model.response.User;
import vn.tnc.data.rx.RxHelper;
import vn.tnc.tncframework.ui.view.UserListView;

/**
 * Created by USER on 5/21/2015.
 */
public class UserListPresenter implements BasePresenter{

    private ApiService apiService;
    private UserListView userListView;
    private Subscription subscription;
    private final static String TAG = UserListPresenter.class.getSimpleName();

    @Inject
    public UserListPresenter(ApiService service){
        this.apiService = service;
    }

    public void setView(UserListView userListView){
        this.userListView = userListView;
    }

    @Override
    public void resume() {
        getListUser();
    }

    public void retry(){
        getListUser();
    }

    private void getListUser(){
        userListView.showLoading();
        userListView.hideRetry();
        subscription = apiService.getListUser()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(new rx.Observer<List<User>>() {
                @Override
                public void onCompleted() {
                    userListView.hideLoading();
                }

                @Override
                public void onError(Throwable e) {
                    Timber.e(e, "got error when load list user");
                    userListView.hideLoading();
                    userListView.showRetry();
                    userListView.showError("load data failed");
                }

                @Override
                public void onNext(List<User> users) {
                    Timber.i("List user", users);
                    userListView.renderUserList(users);

                }
            });
    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        subscription.unsubscribe();
    }
}
