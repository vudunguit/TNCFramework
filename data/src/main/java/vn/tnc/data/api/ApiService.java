package vn.tnc.data.api;

import java.util.List;

import retrofit.http.GET;
import retrofit.http.Path;
import rx.Observable;
import vn.tnc.data.api.model.response.User;

/**
 * Created by USER on 5/25/2015.
 */
public interface ApiService {
    @GET("/users")
    Observable<List<User>> getListUser();

    @GET("/users/{username}")
    Observable<User> getUser(@Path("username") String username);
}
