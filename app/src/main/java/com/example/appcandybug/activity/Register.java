package com.example.appcandybug.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
import com.example.appcandybug.R;
import com.example.appcandybug.model.Account;
import com.example.appcandybug.server.IMyAPI;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Register extends AppCompatActivity {

    EditText edit_username_register,edit_password_register,edit_password_again,edit_SDT,edit_yourname,edit_email;
    Button btn_register;
    TextView txt_login;

    AwesomeValidation awesomeValidation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        anhXa();
        checkInput();
        register();
        login();
    }



    private void login() {
        txt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void register() {
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(awesomeValidation.validate()){
                    Account accountRegister = new Account(edit_username_register.getText().toString(),
                            edit_password_register.getText().toString(),edit_yourname.getText().toString(),
                            edit_email.getText().toString(),Integer.parseInt(edit_SDT.getText().toString()));
                    IMyAPI.iMyAPI.register(accountRegister).enqueue(new Callback<Account>() {
                        @Override
                        public void onResponse(Call<Account> call, Response<Account> response) {
                            if(response.body()!=null){
                                Intent intent = new Intent(Register.this,MainActivity.class);
                                intent.putExtra("AccountRegister",response.body());
                                startActivity(intent);
                            }else{
                                Toast.makeText(getApplicationContext(), "Username is used", Toast.LENGTH_SHORT).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Account> call, Throwable t) {

                        }
                    });
                    
                }

            }
        });
    }

    private void anhXa(){
        edit_username_register = findViewById(R.id.edit_username_register);
        edit_password_register = findViewById(R.id.edit_password_register);
        edit_password_again = findViewById(R.id.edit_password_again);
        edit_SDT = findViewById(R.id.edit_SDT);
        edit_yourname = findViewById(R.id.edit_yourname);
        edit_email = findViewById(R.id.edit_Email);
        btn_register = findViewById(R.id.btn_register);
        txt_login = findViewById(R.id.txt_Login);
        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);

    }

    private void checkInput(){
        awesomeValidation.addValidation(this,R.id.edit_username_register, RegexTemplate.NOT_EMPTY,R.string.invalid_name);
        awesomeValidation.addValidation(this,R.id.edit_yourname, RegexTemplate.NOT_EMPTY,R.string.invalid_displayname);
        awesomeValidation.addValidation(this,R.id.edit_SDT,"[0-9]{1}[0-9]{9}",R.string.invalid_phone);
        awesomeValidation.addValidation(this,R.id.edit_Email, Patterns.EMAIL_ADDRESS,R.string.invalid_email);
        awesomeValidation.addValidation(this,R.id.edit_password_register,".{3,}",R.string.invalid_passwordlength);
        awesomeValidation.addValidation(this,R.id.edit_password_again, R.id.edit_password_register,R.string.invalid_confirm);
    }
}