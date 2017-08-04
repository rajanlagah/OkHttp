package com.exapmle.rajan.okhttp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
private final OkHttpClient mOkHttpClient=new OkHttpClient();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Request request=new Request.Builder().url("http://publicobject.com/helloworld.txt").build();

        mOkHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if(!response.isSuccessful()){
                    throw new IOException("unexpected code "+response);
                }
                else{
                    Headers responseHeader=response.headers();
                    for(int i=0,size=responseHeader.size();i<size;i++){
                        System.out.println(responseHeader.name(i)+" this "+responseHeader.value(i));
                    }
                    System.out.println(response.body().string()+"that");
                }
            }
        });
    }
}
