package com.example.android.moodindigo;


import com.example.android.moodindigo.data.RegistrationRequest;
import com.example.android.moodindigo.data.RegistrationResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by mrunz on 12/6/17.
 */

public interface SearchInterface {

//    @GET("events/")
//    Call<List<E>> getEvents();
//
//    @GET("news/")
//    Call<List<E>> getNews();
//
//
    @GET("user/{id}")
    Call<RegistrationResponse> checkUserDetails(@Path("id") long id);

    @POST("user/create")
    Call<RegistrationResponse> fillRegistrationForm(RegistrationRequest registrationRequest);




}
