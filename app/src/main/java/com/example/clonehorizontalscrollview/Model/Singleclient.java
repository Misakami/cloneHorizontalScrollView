package com.example.clonehorizontalscrollview.Model;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author misaka
 */
public enum  Singleclient {
    //单例
    instance;
    private int defaultTime = 2;
    private OkHttpClient client;
    private Retrofit retrofit;

    Singleclient(){
        client = new OkHttpClient
                .Builder()
                .callTimeout(defaultTime, TimeUnit.SECONDS)
                .connectTimeout(defaultTime, TimeUnit.SECONDS)
                .writeTimeout(defaultTime, TimeUnit.SECONDS)
                .readTimeout(defaultTime, TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit
                .Builder()
                .baseUrl("http://image.baidu.com/channel/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }
    public Singleclient getInstance(){
        return this;
    }

    public <T> T creat(Class<T> service){
        return retrofit.create(service);
    }
}
