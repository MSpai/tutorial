package com.example.mpipini.tutorial.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.mpipini.tutorial.Adapters.GitHubRepoAdapter;
import com.example.mpipini.tutorial.Interfaces.ClickListener;
import com.example.mpipini.tutorial.Interfaces.GitHubClient;
import com.example.mpipini.tutorial.Listeners.RecyclerTouchListener;
import com.example.mpipini.tutorial.Objects.GitHubRepo;
import com.example.mpipini.tutorial.R;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private List<GitHubRepo> responseList = new ArrayList<>();
    private RecyclerView recyclerView;
    private GitHubRepoAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        setRetrofitCall();

        //set divider
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(recyclerView.getContext(),
                layoutManager.getOrientation());
        recyclerView.addItemDecoration(dividerItemDecoration);

        recyclerView.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), recyclerView, new ClickListener() {
            @Override
            public void onClick(View view, int position) {
                GitHubRepo gitHubRepo = responseList.get(position);
                Toast.makeText(getApplicationContext(), gitHubRepo.getName() + " is selected!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("name",gitHubRepo.getName());
                startActivity(intent);

            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

    }

    private void setRetrofitCall() {
        String API_BASE_URL = "https://api.github.com/";

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

        Retrofit.Builder builder =
                new Retrofit.Builder()
                        .baseUrl(API_BASE_URL)
                        .addConverterFactory(
                                GsonConverterFactory.create()
                        );

        Retrofit retrofit =
                builder
                        .client(
                                httpClient.build()
                        )
                        .build();

        // Create a very simple REST adapter which points the GitHub API endpoint.
        GitHubClient client = retrofit.create(GitHubClient.class);


        // Fetch a list of the Github repositories.
        Call<List<GitHubRepo>> call =
                client.reposForUser("fs-opensource");

        // Execute the call asynchronously. Get a positive or negative callback.
        call.enqueue(new Callback<List<GitHubRepo>>() {
            @Override
            public void onResponse(Call<List<GitHubRepo>> call, Response<List<GitHubRepo>> response) {
                // The network call was a success and we got a response
                if(response.isSuccessful()) {
                    responseList = response.body();
                    mAdapter = new GitHubRepoAdapter(responseList);
                    RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
                    recyclerView.setLayoutManager(mLayoutManager);
                    recyclerView.setItemAnimator(new DefaultItemAnimator());
                    recyclerView.setAdapter(mAdapter);
                }else{
                    Toast.makeText(getApplicationContext(), "Unsuccesfull response", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<GitHubRepo>> call, Throwable t) {
                // the network call was a failure
                Toast.makeText(getApplicationContext(), "The network call was a failure", Toast.LENGTH_SHORT).show();
            }
        });

    }

}
