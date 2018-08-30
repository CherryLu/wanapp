package com.chinamobile.wanapp.http;


import com.chinamobile.wanapp.baen.BaseItem;

import java.util.Map;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

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



    @FormUrlEncoded
    @POST("{act}")
    Observable<ResponseBody> getPostTestRequest(@Path(value = "act",encoded =true ) String act,
                                                @Field("uid") String uid,
                                                @Field("eid") String eid,
                                                @Field("timestamp") String timestamp,
                                                @Field("code") String code,
                                                @Field("jid") String jid,
                                                @Field("jz_gain") String jz_gain,
                                                @Field("remark") String remark,
                                                @Field("snap_url") String snap_url,
                                                @Field("status") String status,
                                                @Field("submitTimeStr") String submitTimeStr,
                                                @Field("approveTimesStr") String approveTimesStr);

    /**
     * 获取文件下载
     * @return
     */
    @Streaming
    @GET
    Call<ResponseBody> getFile(@Url String url);

}
