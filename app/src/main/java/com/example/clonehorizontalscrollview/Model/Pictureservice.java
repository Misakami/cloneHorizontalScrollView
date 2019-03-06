package com.example.clonehorizontalscrollview.Model;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;
import retrofit2.http.Url;

public interface Pictureservice {
    @Headers("contentType:utf-8")
    @GET
    Observable<Data4> getPicture(@Url String url,@Query("pn") String pn,@Query("rn")String rn , @Query("ftags") String title);
}
