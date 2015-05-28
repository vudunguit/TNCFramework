package vn.tnc.tncframework.presenter;

import android.util.Log;

import java.util.List;

import javax.inject.Inject;

import rx.Observable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
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
        Log.i(TAG, "resume");
        userListView.showLoading();
        userListView.hideRetry();

        getListUser();
    }

    private void getListUser(){
        subscription = apiService.getListUser()
            .subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .compose(RxHelper.<List<User>>applySchedulers())
            .subscribe(new rx.Observer<List<User>>() {
                @Override
                public void onCompleted() {
                    userListView.hideLoading();
                    userListView.hideRetry();
                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                    userListView.hideLoading();
                    userListView.showRetry();
                    userListView.showError("Failed load data");
                }

                @Override
                public void onNext(List<User> users) {
                    Log.i(TAG, "List size: " + users.size());
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
