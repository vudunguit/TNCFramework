package vn.tnc.core.di.components;

import android.app.Application;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.otto.Bus;
import com.squareup.picasso.Picasso;

import javax.inject.Singleton;

import dagger.Component;
import vn.tnc.core.di.modules.ApplicationModule;
import vn.tnc.core.di.modules.UtilModule;
import vn.tnc.data.DataModule;
import vn.tnc.data.api.ApiService;

/**
 * Created by USER on 5/20/2015.
 */
@Singleton
@Component(
        modules = {UtilModule.class, DataModule.class, ApplicationModule.class}
)
public interface ApplicationComponent {
    //Exposed to sub-graphs.
    Application application();
    Bus ottoBus();

    Gson gson();
    OkHttpClient okHttpClient();
    Picasso picasso();
    ApiService apiService();

}
