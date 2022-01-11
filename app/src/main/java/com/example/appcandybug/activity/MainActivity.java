package com.example.appcandybug.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
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
    CheckBox remember_me;
    SharedPreferences preferences;
    AwesomeValidation awesomeValidation;
    private static final String PREFS_NAME = "PrefsFile";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferences = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        anhXa();
        checkInput();
        login();
        register_current();
        rememberme();
        getPreferentData();
        register();

    }

    private void getPreferentData() {
        SharedPreferences sp = getSharedPreferences(PREFS_NAME,MODE_PRIVATE);
        if(sp.contains("remember_user")){
            String u = sp.getString("remember_user","not found");
            edit_username.setText(u);
        }
        if(sp.contains("remember_pass")){
            String u = sp.getString("remember_pass","not found");
            edit_password.setText(u);
        }
        if(sp.contains("remember_check")){
            Boolean u = sp.getBoolean("remember_check",false);
            remember_me.setChecked(u);
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

    }

    private void rememberme() {
        remember_me.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (buttonView.isChecked()) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString("remember_user",edit_username.getText().toString());
                    editor.putString("remember_pass",edit_password.getText().toString());
                    editor.putBoolean("remember_check",true);
                    editor.apply();
                }else {
                    preferences.edit().clear().apply();
                }
            }
        });

    }

    private void anhXa(){
        edit_username = findViewById(R.id.edit_username);
        edit_password = findViewById(R.id.edit_password);
        btn_login = findViewById(R.id.btn_login);
        txt_Register = findViewById(R.id.txt_Register);
        remember_me = findViewById(R.id.check_remember_me);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);
    }
    private void login(){
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(awesomeValidation.validate()){
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
                        Toast.makeText(MainActivity.this,"404 HTTP not Found" + t.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
                }
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
            preferences.edit().clear().apply();
            edit_username.setText(account_extra.getUsername());
            edit_password.setText(account_extra.getPassWord());
            remember_me.setChecked(false);
            Toast.makeText(getApplicationContext(), "Register Successfull", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkInput(){
        awesomeValidation.addValidation(this,R.id.edit_username, RegexTemplate.NOT_EMPTY,R.string.invalid_username);
        awesomeValidation.addValidation(this,R.id.edit_password, RegexTemplate.NOT_EMPTY,R.string.invalid_password);

    }
}