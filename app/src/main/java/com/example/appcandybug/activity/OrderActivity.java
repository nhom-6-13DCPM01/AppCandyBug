package com.example.appcandybug.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.appcandybug.R;

public class OrderActivity extends AppCompatActivity {
    Button btnOrder, btnConfirm, btnCancel;
    EditText edtPhone, edtAdress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        anhXaOrderActivity();
        thucHienOrderActivity();


    }

    private void anhXaOrderActivity(){
        btnOrder = (Button) findViewById(R.id.buttonPickOrder);
    }

    private void thucHienOrderActivity(){
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderDialog();
            }
        });
    }

    private void anhXaDialog(Dialog dialog){
        btnConfirm = (Button) dialog.findViewById(R.id.buttonConfirm);
        btnCancel = (Button) dialog.findViewById(R.id.buttonCancel);
        edtPhone = (EditText) dialog.findViewById(R.id.editTextPhone);
        edtAdress = (EditText) dialog.findViewById(R.id.editTextAdress);
    }

    private void orderDialog(){
        Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setContentView(R.layout.dialog_order);

        anhXaDialog(dialog);
        thucHienDialog(dialog);

        dialog.show();
    }

    private void thucHienDialog(Dialog dialog){
        btnConfirm.setOnClickListener(v -> {
            if(edtPhone.getText().toString().isEmpty() && edtAdress.getText().toString().isEmpty())
                Toast.makeText(OrderActivity.this, "Xin bạn hãy nhập thông tin", Toast.LENGTH_SHORT).show();
            else{
                int phone = new Integer(edtPhone.getText().toString());
                String adress = edtAdress.getText().toString();
                Toast.makeText(OrderActivity.this, adress + " " + phone, Toast.LENGTH_SHORT).show();
            }
        });

        btnCancel.setOnClickListener(v -> {
            dialog.dismiss();
        });
    }
}