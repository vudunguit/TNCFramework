package vn.tnc.data.api;

import com.squareup.okhttp.OkHttpClient;

import dagger.Module;
import dagger.Provides;
import retrofit.Endpoint;
import retrofit.Endpoints;
import retrofit.RestAdapter;
import retrofit.client.Client;
import retrofit.client.OkClient;

/**
 * Created by USER on 5/25/2015.
 */
@Module
public class ApiModule {
    public static final String GITHUB_API_BASE_URL = "https://api.github.com";
    private static final String CLIENT_ID = "07111988";

    @Provides
    @ClientId
    String provideClientId(){
        return CLIENT_ID;
    }

    @Provides
    Client provideClient(OkHttpClient client){
        return new OkClient(client);
    }

    @Provides
    Endpoint provideEndpoint(){
        return Endpoints.newFixedEndpoint(GITHUB_API_BASE_URL);
    }

    @Provides
    RestAdapter provideRestAdapter(Endpoint endpoint, Client client, ApiHeader header){
        return new RestAdapter.Builder()
                .setClient(client)
                .setEndpoint(endpoint)
                .setRequestInterceptor(header)
                .build();
    }
}
