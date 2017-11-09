package com.example.mpipini.tutorial.Interfaces;

import com.example.mpipini.tutorial.Objects.GitHubRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by Maria Spai on 09/11/2017.
 */
public interface GitHubClient {
    @GET("/users/{user}/repos")
    Call<List<GitHubRepo>> reposForUser(
            @Path("user") String user
    );
}
