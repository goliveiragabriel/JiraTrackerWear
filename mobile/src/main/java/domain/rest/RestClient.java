package domain.rest;

import android.support.annotation.Nullable;
import android.util.Base64;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.concurrent.TimeUnit;

import domain.model.QueryResult;
import domain.model.Token;
import domain.model.User;
import retrofit.Callback;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

import static java.util.concurrent.TimeUnit.*;

/**
 * Created by gabriel.goncalves on 29/01/2016.
 */
public class RestClient {

    private static final String BASE_URL = "http://jira.metaora.com.br:8090";
    public ApiService apiService;
    private static RestAdapter.Builder builder = new RestAdapter.Builder()
                                                        .setEndpoint(BASE_URL);

    public static <S> S createService(Class<S> serviceClass) {
        return createService(serviceClass, null, null);
    }

    public static <S> S createService(Class<S> serviceClass, String username, String password) {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss")
                .create();

        if (username != null && password != null) {
            // concatenate username and password with colon for authentication
            String credentials = username + ":" + password;
            // create Base64 encodet string
            final String basic =
                    "Basic " + Base64.encodeToString(credentials.getBytes(), Base64.NO_WRAP);

            builder.setRequestInterceptor(new RequestInterceptor() {
                @Override
                public void intercept(RequestInterceptor.RequestFacade request) {
                    request.addHeader("Authorization", basic);
                    request.addHeader("Content-Type", "application/json");
                }
            });
        }
        OkHttpClient okHttpClient = new OkHttpClient();
        okHttpClient.setConnectTimeout(120*1000, TimeUnit.MILLISECONDS);
        okHttpClient.setReadTimeout(60 * 1000, TimeUnit.MILLISECONDS);
        okHttpClient.setWriteTimeout(60*1000, TimeUnit.MILLISECONDS);

        RestAdapter adapter = builder
                                    .setConverter(new GsonConverter(gson))
                                    .setClient(new OkClient(okHttpClient))
                                    .build();

        return adapter.create(serviceClass);
    }



    public ApiService getApi() {
        return apiService;
    }

    public Token Authenticate(User user) {
        return getApi().Authenticate(user);
    }

    public void GetIssues(String userName, String orderBy, boolean ascending,  Callback<QueryResult> callback){
        GetIssues(userName, 9999, orderBy, ascending, callback);
    }
    public void GetIssues(String userName, int maxResult,  Callback<QueryResult> callback){
        GetIssues(userName, maxResult, "", true, callback);
    }
    public void GetIssues(String userName, String orderBy,  Callback<QueryResult> callback){
        GetIssues(userName, 9999, orderBy, true, callback);
    }
    public void GetIssues(String userName, int maxResult, String orderBy, boolean ascending, Callback<QueryResult> callback){
        String query = "";
//        try {
            query = "assignee="+ userName;
            if (orderBy != "") {
            query += " order by " + orderBy;
                query += " " + (ascending ? "asc" : "desc");
            }
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
        this.apiService.GetIssues(query, maxResult, callback );
    }

}
