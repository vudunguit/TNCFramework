package vn.tnc.core.base.mvp;

/**
 * Created by USER on 5/20/2015.
 */
public interface BaseView {

    void showLoading();

    void hideLoading();

    void showRetry();

    void hideRetry();

    void showError(String error);
}
