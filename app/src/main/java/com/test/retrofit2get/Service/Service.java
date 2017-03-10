package com.test.retrofit2get.Service;

import com.test.retrofit2get.Model.Model;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Victorious on 19/09/2016.
 */
public interface Service {
    @GET("retrofit/{version}/get.php")
    Call<Model> get(@Path("version") String version, @Query("test_name") String test_name);
}