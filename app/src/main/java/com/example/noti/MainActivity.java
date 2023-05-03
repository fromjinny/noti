package com.example.noti;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.noti.Retrofit2.APIUtils;
import com.example.noti.Retrofit2.DataClient;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    Button btnreg, btnlog;
    EditText edtusername,edtpassword;
    String username;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mappingfuction();
        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(task -> {
                    if (!task.isSuccessful()) {
                        Log.d("mytoken","Fetching FCM registration token failed");
                        return;
                    }
                    // Get new FCM registration token
                    String token = task.getResult();
                    Log.d("mytoken", token);
                    // create a varible get database
                    // implemnet fiel token to database before push
                    Toast.makeText(MainActivity.this, token, Toast.LENGTH_SHORT).show();
                });
        btnreg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,SignUpActivity2.class);
                startActivity(intent);
            }
        });

        btnlog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                username=edtusername.getText().toString();
                password=edtpassword.getText().toString();
                if(username.length()>0 &&password.length()>0){
                    DataClient dataClient = APIUtils.getData();
                    Call<List<CCustomer>> callback=dataClient.Logindata(username,password);
                    callback.enqueue(new Callback<List<CCustomer>>() {
                        @Override
                        public void onResponse(Call<List<CCustomer>> call, Response<List<CCustomer>> response) {
                            ArrayList<CCustomer> arrayclient= (ArrayList<CCustomer>) response.body();
                            if(arrayclient.size()>0){

                                /*Log.d("BBB",arrayclient.get(0).getUsername());
                                Log.d("BBB",arrayclient.get(0).getPassword());
                                Log.d("BBB",arrayclient.get(0).getImagepath());*/
                                Intent intent = new Intent(MainActivity.this,LoginSuccessActivity2.class);
                                intent.putExtra("arrayclient",arrayclient);
                                startActivity(intent);

                            }

                        }

                        @Override
                        public void onFailure(Call<List<CCustomer>> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "This user is not available. Please try again!", Toast.LENGTH_LONG).show();

                        }
                    });



                }

            }
        });
    }

    private void mappingfuction() {
        btnlog=findViewById(R.id.buttonlogin);
        btnreg=findViewById(R.id.buttonregister);
        edtpassword=findViewById(R.id.edittextpassword);
        edtusername=findViewById(R.id.edittextusername);
    }
}