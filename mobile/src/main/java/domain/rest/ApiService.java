package domain.rest;

import domain.model.QueryResult;
import domain.model.Token;
import domain.model.User;
import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Query;

/**
 * Created by gabriel.goncalves on 29/01/2016.
 */
public interface ApiService {

    /**
     *  ===========================================
     *  === User Api
     *  =========================================
     */

    @POST("/rest/auth/1/session")
    public Token Authenticate(User user);

    @POST("/rest/auth/1/session")
    User basicLogin();

    @GET("/rest/api/2/search")
    void GetIssues(@Query("jql") String query, @Query("maxResults") int maxResults, Callback<QueryResult> callback);

    @GET("/rest/api/2/search")
    void GetIssues(@Query("jql") String query, @Query("maxResults") int maxResults, @Query("orderBy") String orderBy, Callback<QueryResult> callback);

}
