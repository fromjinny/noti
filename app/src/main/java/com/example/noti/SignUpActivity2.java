package com.example.noti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.noti.Retrofit2.APIUtils;
import com.example.noti.Retrofit2.DataClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpActivity2 extends AppCompatActivity {
    EditText edtusername,edtpassword,edtfullname,edtemail;
    Button btcancel, btconfirm;
    String username;
    String password;
    String fullname;
    String email;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up2);
        mapping();
        btconfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username=edtusername.getText().toString();
                password=edtpassword.getText().toString();
                fullname=edtfullname.getText().toString();
                email=edtemail.getText().toString();
                if(username.length()>0 && password.length()>0 && fullname.length()>0 && email.length()>0){
                    DataClient insertdata = APIUtils.getData();
                    retrofit2.Call<String> callback= insertdata.InsertData(username,password,fullname,email);
                    callback.enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {
                            String result= response.body();
                            if(result.equals("SUCCESSFUL")){
                                Toast.makeText(SignUpActivity2.this, "Successful registered", Toast.LENGTH_LONG).show();
                                finish();
                            }
                        }
                        @Override
                        public void onFailure(Call<String> call, Throwable t) {

                        }
                    });

                }else{
                    Toast.makeText(SignUpActivity2.this, "Don't leave any field empty!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void mapping() {
        edtusername=findViewById(R.id.edittextreguser);
        edtpassword=findViewById(R.id.edittextregpass);
        edtfullname=findViewById(R.id.editfullname);
        edtemail=findViewById(R.id.editemailaddress);
        btcancel=findViewById(R.id.buttoncancel);
        btconfirm=findViewById(R.id.buttonconfirm);
    }
    public void cancel(View view){
        startActivity(new Intent(SignUpActivity2.this,MainActivity.class));
    }
}