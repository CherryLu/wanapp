package com.chinamobile.wanapp.baen;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JobStr implements Serializable {



    @SerializedName("market")
    private String market;


    @SerializedName("marketUrl")
    private String marketUrl;


    @SerializedName("marketApk")
    private String marketApk;

    @SerializedName("appApk")
    private String appApk;

    @SerializedName("appUrl")
    private String appUrl;


    @SerializedName("share")
    private List<ShareData> shareData;


    public String getMarket() {
        return market;
    }

    public String getMarketUrl() {
        return marketUrl;
    }

    public String getMarketApk() {
        return marketApk;
    }

    public String getAppApk() {
        return appApk;
    }

    public String getAppUrl() {
        return appUrl;
    }

    public List<ShareData> getShareData() {
       /* List<ShareData> shares = new ArrayList<>();
        if (shareData!=null){
            JsonParser parser = new JsonParser();

            JsonArray jsonArray = parser.parse(shareData).getAsJsonArray();
            Gson gson = new Gson();

            for (JsonElement user : jsonArray){
                ShareData userBean = gson.fromJson(user, ShareData.class);
                shares.add(userBean);

            }
        }*/
        return shareData;
    }
}
