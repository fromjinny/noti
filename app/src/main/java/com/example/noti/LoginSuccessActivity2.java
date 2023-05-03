package com.example.noti;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.noti.Retrofit2.APIUtils;
import com.example.noti.Retrofit2.DataClient;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginSuccessActivity2 extends AppCompatActivity {

    Button btdelete,btupload;
    EditText edtfingeruser,edtfingerid;
    TextView txtuser,txtpass;
    ArrayList<CCustomer> cClientArrayList;
    FirebaseDatabase database;
    DatabaseReference myRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_success2);
        mappingfunc();
        inituserinfo();
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference().child("Fingerprint_User");
        btupload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String user=edtfingeruser.getText().toString();
                String userid=edtfingerid.getText().toString();
                if(user.length()>0&&userid.length()>0){
                    HashMap<String,String> userMap = new HashMap<>();
                    userMap.put("User",user);
                    //userMap.put("id",userid);
                    myRef.child(userid).setValue(userMap);
                    Toast.makeText(LoginSuccessActivity2.this, "Successfully added!", Toast.LENGTH_LONG).show();
                } else{
                    Toast.makeText(LoginSuccessActivity2.this, "Don't leave your name or id empty!", Toast.LENGTH_LONG).show();
                }

            }
        });
        btdelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialog= new AlertDialog.Builder(LoginSuccessActivity2.this);
                dialog.setTitle("Do you want to continue? ");
                dialog.setMessage("By deleting your account, " +
                        "your data will be removed from our system " +
                        "and you will not be able to control your home!");
                dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        DataClient dataClient= APIUtils.getData();
                        Call<String> callback= dataClient.DeleteData(cClientArrayList.get(0).getId());
                        callback.enqueue(new Callback<String>() {
                            @Override
                            public void onResponse(Call<String> call, Response<String> response) {
                                String results= response.body();
                                if (results.equals("deleted")){
                                    Toast.makeText(LoginSuccessActivity2.this, "Successful deleted", Toast.LENGTH_LONG).show();
                                    finish();
                                }
                            }
                            @Override
                            public void onFailure(Call<String> call, Throwable t) {
                                Toast.makeText(LoginSuccessActivity2.this, "Failed, Please try again!", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
                //Log.d("BBB", namefolder);
                dialog.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });
                AlertDialog alertDialog= dialog.create();
                alertDialog.show();
            }
        });
    }

    private void inituserinfo() {
        Intent intent = getIntent();
        cClientArrayList=intent.getParcelableArrayListExtra("arrayclient");
        //Log.d("AAA",cClientArrayList.get(0).getUsername());
        //Log.d("AAA",cClientArrayList.get(0).getPassword());
        txtuser.setText(" " + cClientArrayList.get(0).getUsername() + " !");
        //txtpass.setText("Password: " + cClientArrayList.get(0).getPassword());

    }


    private void mappingfunc() {
        btdelete=findViewById(R.id.buttondelete);
        txtuser=findViewById(R.id.infousername);
        txtpass=findViewById(R.id.infopassword);
        btupload=findViewById(R.id.confirmfirebase);
        edtfingeruser=findViewById(R.id.fingerfirebase);
        edtfingerid=findViewById(R.id.fingerfbid);
    }

    public void back(View view){
        startActivity(new Intent(LoginSuccessActivity2.this,MainActivity.class));

    }
    public void homecontrol(View view){
        startActivity(new Intent(LoginSuccessActivity2.this,HomeSctActivity2.class));
    }
}