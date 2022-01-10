package com.example.appcandybug.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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
    Account account_extra = null;
    EditText edit_username;
    EditText edit_password;
    Button btn_login;
    TextView txt_Register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        anhXa();
        login();
        register();
        register_current();
    }

    private void anhXa(){
        edit_username = findViewById(R.id.edit_username);
        edit_password = findViewById(R.id.edit_password);
        btn_login = findViewById(R.id.btn_login);
        txt_Register = findViewById(R.id.txt_Register);
    }
    private void login(){
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Account account = new Account(edit_username.getText().toString(),
                        edit_password.getText().toString());
                IMyAPI.iMyAPI.login(account).enqueue(new Callback<Account>() {
                    @Override
                    public void onResponse(Call<Account> call, Response<Account> response) {
                        if(response.body()!=null){
                            Account accountLogin = response.body();
                            Intent intent = new Intent(MainActivity.this,Index.class);
                            intent.putExtra("Account", accountLogin);
                            startActivity(intent);
                            return;
                        }
                        Toast.makeText(getApplicationContext(), "Login Failure", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<Account> call, Throwable t) {
                        Toast.makeText(MainActivity.this,"404 HTTP not Found",Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void register(){
        txt_Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Register.class);
                startActivity(intent);
            }
        });
    }
    
    private void register_current(){
        account_extra = getIntent().getParcelableExtra("AccountRegister");
        if(account_extra!=null){
            edit_username.setText(account_extra.getUsername());
            edit_password.setText(account_extra.getPassWord());
            Toast.makeText(getApplicationContext(), "Register Successfull", Toast.LENGTH_SHORT).show();
        }
    }
}