package com.example.admin.filmsthemoviedb.view.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.example.admin.filmsthemoviedb.R;
import com.example.admin.filmsthemoviedb.api.model.MoviePopularResponse;
import com.example.admin.filmsthemoviedb.api.NetworkManager;
import com.example.admin.filmsthemoviedb.view.movie.MovieActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeActivity extends AppCompatActivity {

    public static final String TAG = HomeActivity.class.getName();

    @BindView(R.id.request_recycler)
    RecyclerView mRequestRecycler;

    private NetworkManager mNetworkManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        mRequestRecycler.setLayoutManager(new LinearLayoutManager(this));
        mNetworkManager = new NetworkManager(this);
        showData();
    }

    private void showData() {
        if (!mNetworkManager.getApiKey().isEmpty()) {
            getData();
        } else {
            Toast.makeText(this, "Not found api_key", Toast.LENGTH_SHORT).show();
        }
    }

    private void getData() {
        mNetworkManager.getApiService().getPopularMovieByPage(1).enqueue(new Callback<MoviePopularResponse>() {
            @Override
            public void onResponse(Call<MoviePopularResponse> call, Response<MoviePopularResponse> response) {
                HomeMovieAdapter adapter = new HomeMovieAdapter(response.body());
                subscAdapter(adapter);
                mRequestRecycler.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<MoviePopularResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }

    public void startMovieActivity(Bundle bundle) {
        Intent intent = new Intent(this, MovieActivity.class);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    void subscAdapter(HomeMovieAdapter adater) {
        adater.subscribeActivity(this);
    }
}
