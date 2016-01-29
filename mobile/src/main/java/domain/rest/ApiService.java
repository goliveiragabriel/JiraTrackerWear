package domain.rest;

import domain.model.Token;
import domain.model.User;
import retrofit.http.POST;

/**
 * Created by gabriel.goncalves on 29/01/2016.
 */
public interface ApiService {

    /**
     *  ===========================================
     *  === User Api
     *  =========================================
     */

    @POST("/jira/rest/auth/1/session")
    public Token Authenticate(User user);
}
