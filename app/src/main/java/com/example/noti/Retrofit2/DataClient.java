package com.example.noti.Retrofit2;
import com.example.noti.CCustomer;
import java.util.List;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;
public interface DataClient {
    @FormUrlEncoded // send data string format
    @POST("insert.php")
    Call<String> InsertData(@Field("username") String username
            ,@Field("password") String password
            ,@Field("fullname") String fullname
            ,@Field("email") String email);

    @FormUrlEncoded
    @POST("login.php")
    Call<List<CCustomer>> Logindata(@Field("username") String username
            , @Field("password") String password);

    @GET("delete.php")
    Call<String> DeleteData (@Query("id") String id);

}
