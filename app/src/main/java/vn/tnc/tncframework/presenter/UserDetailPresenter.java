package vn.tnc.tncframework.presenter;

import javax.inject.Inject;

import rx.Observer;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import timber.log.Timber;
import vn.tnc.core.base.mvp.BasePresenter;
import vn.tnc.data.api.ApiService;
import vn.tnc.data.api.model.response.User;
import vn.tnc.tncframework.ui.view.UserDetailView;

/**
 * Created by USER on 6/3/2015.
 */
public class UserDetailPresenter implements BasePresenter{
    private UserDetailView view;
    private ApiService apiService;
    private String mUsername;
    private Subscription subscription;
    @Inject
    public UserDetailPresenter(ApiService apiService){
        this.apiService = apiService;
    }

    public void setView(UserDetailView view){
        this.view = view;
    }

    public void setUsername(String username){
        this.mUsername = username;
    }
    @Override
    public void resume() {
        view.showLoading();
        view.hideRetry();
        subscription = apiService.getUser(mUsername)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<User>() {
                    @Override
                    public void onCompleted() {
                        view.hideLoading();
                        view.hideRetry();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.e(e, "got error when load user detail");
                        view.hideLoading();
                        view.showRetry();
                        view.showError("load user detail failed");
                    }

                    @Override
                    public void onNext(User user) {
                        Timber.i("user info", user);
                    }
                });
    }

    @Override
    public void pause() {
        subscription.unsubscribe();
    }

    @Override
    public void destroy() {

    }
}
