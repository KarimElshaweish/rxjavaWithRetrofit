package com.example.karim.rxjava.Retrofit;

import com.example.karim.rxjava.Model.Post;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface IMyAPI {
    @GET("posts")
    Observable<List<Post>> getPosts();
    @POST("Post")
    Observable<List<Post>>sendData(@Body Post post);
}
