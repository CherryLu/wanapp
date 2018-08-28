package com.chinamobile.wanapp.http;


import com.chinamobile.wanapp.baen.BaseItem;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;

/**
 * Created by Administrator on 2018/8/6.
 */

public interface RetrofitService {

    @GET("User/register")
    Observable<BaseItem> getGetRequest(@Query("data") String data, @Query("key") String key);


    @GET("{act}")
    Observable<ResponseBody> getGetRequest(@Path(value = "act",encoded =true ) String act, @QueryMap Map<String, String> map);

    @FormUrlEncoded
    @POST("{act}")
    Observable<ResponseBody> getPostRequest(@Path(value = "act",encoded =true ) String act, @FieldMap Map<String, String> map);


    /**
     * 获取文件下载
     * @return
     */
    @Streaming
    @GET("{path}")
    Call<ResponseBody> getFile(@Path("path") String path);

}
