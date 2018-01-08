
package jco.goldenrule.API;


import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;

public class GoldenRuleApi {
     public static final String BASE_URL = "https://golden-rule-node-back-end.herokuapp.com/api/";
    public static final String ENV_SERVEUR = "goldenruleapi-development";
    GoldenRuleApiEndpointInterface apiService;

    private Context mContext = null;
    private String mAuthenticationToken;

    public GoldenRuleApi() {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(GoldenRuleApiEndpointInterface.class);

    }


    public interface GoldenRuleApiEndpointInterface {


        // Authentication #####################################################
        @GET("messages/{organization_id}")
        Call<JsonObject> getMessages(
                @Path("organization_id") String organization);


    }


    public void  getMessages(String organizationName) throws IOException {

        Call<JsonObject> getMessagesCall = apiService.getMessages(organizationName);
        System.out.println("Messages "+getMessagesCall.request().url());
        System.out.println("Messages "+getMessagesCall.request().body());
        System.out.println("Messages "+getMessagesCall.request().headers());
        Response<JsonObject> getMessagesResponse = getMessagesCall.execute();
        int responseCode = getMessagesResponse.code();
        if (responseCode == 200) {
            System.out.println("Messages recieved" +getMessagesResponse.body().toString());
            System.out.println("Messages recieved");
        } else {
            if (getMessagesResponse.errorBody() != null) {
                /**
                System.out.println("R! " + authenticationResponse.errorBody().toString() + authenticationResponseCode);
                System.out.println("R! " + authenticationResponse.errorBody().string() + authenticationResponseCode);
            */
                 }
            throw new RuntimeException("Unable to retrieve messages");

        }
    }

}

