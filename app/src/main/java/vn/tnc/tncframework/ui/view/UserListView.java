package vn.tnc.tncframework.ui.view;

import java.util.List;

import vn.tnc.core.base.mvp.BaseView;
import vn.tnc.data.api.model.response.User;

/**
 * Created by USER on 5/21/2015.
 */
public interface UserListView extends BaseView{

    void renderUserList(List<User> users);
}
