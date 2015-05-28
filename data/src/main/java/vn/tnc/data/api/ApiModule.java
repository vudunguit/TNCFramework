package vn.tnc.data.api;

import com.google.gson.Gson;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by USER on 5/25/2015.
 */
@Module
public class ApiModule {
    public static final String GITHUB_API_BASE_URL = "https://api.github.com";
    /*
    @Provides
    @Singleton
    OkHttpClient provideClient(OkHttpClient client){
        return client.clone();
    }
    */

    @Provides
    @Singleton
    Endpoint provideEndpoint(){
        return Endpoints.newFixedEndpoint(GITHUB_API_BASE_URL);
    }

    @Provides
    @Singleton

    RestAdapter provideRestAdapter(Endpoint endpoint, OkHttpClient client, Gson gson){
        return new RestAdapter.Builder()
                .setClient(new OkClient(client))
                .setEndpoint(endpoint)
                .setLogLevel(RestAdapter.LogLevel.FULL)
                .setConverter(new GsonConverter(gson))
                .build();
    }

    @Provides
    @Singleton
    ApiService provideApiServer(RestAdapter restAdapter){
        return restAdapter.create(ApiService.class);
    }
}
