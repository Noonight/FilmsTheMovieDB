package com.example.admin.filmsthemoviedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.filmsthemoviedb.api.MovieResponse;
import com.example.admin.filmsthemoviedb.api.NetworkManager;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
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
        mNetworkManager.getApiService().getPopularMovie().enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                mRequestRecycler.setAdapter(new MovieAdapter(response.body()));
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Log.e(TAG, t.getMessage());
            }
        });
    }
}
