package vn.tnc.data.api;

import javax.inject.Inject;

import retrofit.RequestInterceptor;

/**
 * Created by USER on 5/25/2015.
 */
public class ApiHeader implements RequestInterceptor{
    private static final String AUTHORIZATION_PREFIX = "Client-ID";

    private final String authorzationValue;

    @Inject
    public ApiHeader(@ClientId String clientId){
        authorzationValue = AUTHORIZATION_PREFIX + " " + clientId;
    }
    @Override
    public void intercept(RequestFacade request) {
        request.addHeader("Authorization", authorzationValue);
    }
}
