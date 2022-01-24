package com.example.demo.jsonpersoncat.mvvmtest1;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Test1MainModel {
    private static Test1MainModel test1MainModel;
    private Retrofit retrofit = new Retrofit.Builder().
            baseUrl(HttpConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .build();

    public Test1MainModel() {
    }

    public static Test1MainModel getInstance(){
        if (test1MainModel == null){
            synchronized (Test1MainModel.class){
                if(test1MainModel == null){
                    test1MainModel = new Test1MainModel();
                }
            }
        }
        return test1MainModel;
    }

    /**
     * 1-2.代理物件 :
     * 有了介面(MpSerivce.java)並不能直接使用，我們需要獲取代理物件，在 Test1MainModel.java中通過Retrofit的create方法傳入介面就可以了。
     * 1-3.建立訂閱 :
     * 我們的介面(MpSerivce.java)返回的是一個Observable，那麼我們就需要傳入一個Observer，然後建立訂閱關係。
     * 其中，getListData是用來獲取Observable，subscribe建立訂閱關係，並指定工作執行緒。
     * get the proxy object of MpService
     * get the observable of proxy object
     * build a subscription relationship
     * @param observer observer
     */
    public void subscribe(Observer<BaseData<List<MpBean>>> observer) {
        retrofit.create(MpSerivce.class).getListData()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
