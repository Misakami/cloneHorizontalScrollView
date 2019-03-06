package com.example.clonehorizontalscrollview.presenter;

import com.example.clonehorizontalscrollview.Model.Data4;
import com.example.clonehorizontalscrollview.Model.Pictureservice;
import com.example.clonehorizontalscrollview.Model.Singleclient;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class LoaderPresenter {
    private Singleclient singleclient = Singleclient.instance.getInstance();

    public  Observable<Data4> LoaderPresenter(String pn,String rn){
        return  singleclient.creat(Pictureservice.class)
                .getPicture("listjson?&tag1=美女&tag2=全部",pn,rn,"性感")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }
}
