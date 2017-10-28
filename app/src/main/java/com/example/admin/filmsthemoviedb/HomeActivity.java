package com.example.admin.filmsthemoviedb;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.OnClick;

public class HomeActivity extends AppCompatActivity {

    @BindView(R.id.api_key_et)
    EditText mApiKeyEt;
    @BindView(R.id.send_btn)
    Button mSendBtn;

    @OnClick(R.id.send_btn)
    void send() {

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
    }

    private void init() {

    }
}
