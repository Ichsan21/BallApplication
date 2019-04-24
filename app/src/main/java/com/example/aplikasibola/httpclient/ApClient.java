package com.example.aplikasibola.httpclient;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public class ApClient {

    public static final String BASE_URL="https://www.thesportsdb.com/api/v1/json/1/";

    public static Retrofit retrofit=null;

    public static Retrofit getApiClient(){
        if(retrofit==null){
          retrofit = new Retrofit.Builder()
          .baseUrl(BASE_URL)
          .addConverterFactory(GsonConverterFactory.create())
          .build();
        }
        return retrofit;
    }

}
