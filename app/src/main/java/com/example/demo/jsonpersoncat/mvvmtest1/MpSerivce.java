package com.example.demo.jsonpersoncat.mvvmtest1;

import io.reactivex.rxjava3.core.Observable;

import java.util.List;

import retrofit2.http.GET;

/**
 *
 * 1-1.建立介面 :
 * 首先，建立公眾號列表資料的介面MpService.java，然後宣告這個geListData()
 * @author : StarryRivers
 * Project  : MVVM
 * Desc     : request Media Platform data api
 */
public interface MpSerivce {
    /**
     * request MP data
     * @return MP list
     */
    @GET("wxarticle/chapters/json")// API URL : https://wanandroid.com//wxarticle/chapters/json
    Observable<BaseData<List<MpBean>>> getListData();

}
