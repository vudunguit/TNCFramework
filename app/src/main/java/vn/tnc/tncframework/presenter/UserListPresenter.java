package vn.tnc.tncframework.presenter;

import vn.tnc.core.base.mvp.BasePresenter;
import vn.tnc.tncframework.ui.view.UserListView;

/**
 * Created by USER on 5/21/2015.
 */
public class UserListPresenter implements BasePresenter{

    private UserListView userListView;
    public void setView(UserListView userListView){
        this.userListView = userListView;
    }
    @Override
    public void resume() {
        userListView.showLoading();
        userListView.hideRetry();
    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {

    }
}
