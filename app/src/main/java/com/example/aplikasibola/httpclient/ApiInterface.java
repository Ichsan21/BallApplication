package com.example.aplikasibola.httpclient;

import android.telecom.CallScreeningService;

import com.example.aplikasibola.model.ResponseAllLeague;
import com.example.aplikasibola.model.ResponseDetailLeague;
import com.example.aplikasibola.model.ResponseLookupTeam;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {
    @GET("search_all_leagues.php?s=Soccer")
    Call<ResponseAllLeague> getAllLeague();

    @GET("eventspastleague.php?")
    Call<ResponseDetailLeague> getAllEvent(@Query("id") String id);

    @GET("lookupteam.php?")
    Call<ResponseLookupTeam> getLookupTeam(@Query("id") String id);
}
