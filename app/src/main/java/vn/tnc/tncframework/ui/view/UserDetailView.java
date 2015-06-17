package vn.tnc.tncframework.ui.view;

import vn.tnc.core.base.mvp.BaseView;
import vn.tnc.data.api.model.response.User;

/**
 * Created by USER on 6/3/2015.
 */
public interface UserDetailView extends BaseView{

    void render(User user);
}
