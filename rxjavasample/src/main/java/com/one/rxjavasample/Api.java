package com.one.rxjavasample;

import java.util.List;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * @author diaokaibin@gmail.com on 2019-11-08.
 */
public interface Api {

    @GET("users/{user}/repos")
    Single<List<Repo>> listRepos(@Path("user") String user);
}
