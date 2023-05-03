package com.example.noti.Retrofit2;

public class APIUtils {
    public static final String Base_Url="http://10.79.232.251/alldata/";
    public static DataClient getData(){
        return RetrofitClient.getClient(Base_Url).create(DataClient.class);
    }
}
//public static final String Base_Url="http://10.3.18.212/userdatabase/";
//public static final String Base_Url="http://10.79.232.251/alldata/"; LAN
//public static final String Base_Url="http://172.20.10.2/alldata/"; //FromJinny wifi