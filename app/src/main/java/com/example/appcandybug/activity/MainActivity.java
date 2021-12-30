package com.example.appcandybug.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.appcandybug.R;
import com.example.appcandybug.model.Account;
import com.example.appcandybug.server.IMyAPI;

import io.reactivex.disposables.CompositeDisposable;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    EditText edit_username;
    EditText edit_password;
    Button btn_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        login();
    }

    public void anhXa(){
        edit_username = findViewById(R.id.edit_username);
        edit_password = findViewById(R.id.edit_password);
        btn_login = findViewById(R.id.btn_login);
    }
    public void login(){
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Account account = new Account(edit_username.getText().toString(),
                        edit_password.getText().toString());
                IMyAPI.iMyAPI.login(account).enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        Toast.makeText(MainActivity.this,response.body().toString(),Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(MainActivity.this,Index.class);
                        startActivity(intent);
                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable t) {
                        Toast.makeText(MainActivity.this,"404 HTTP not Found",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}