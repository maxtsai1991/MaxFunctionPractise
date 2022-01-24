package com.example.demo.jsonpersoncat.mvvmtest1;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

/**
 * 請求資料:
 * 該範例以玩Android為資料來源(https://www.wanandroid.com/blog/show/2)
 * 我們使用OkHttp實現網路請求，Retrofit以介面的形式回撥，通過RxAndroid更好的處理業務和展示工作。
 * @author : StarryRivers
 * Project  : MVVM
 * Desc     : ViewModel MVVM
 * @date : 2020/11/6 12:37
 */
public class Test1MainViewModel extends AndroidViewModel {
    private Test1MainModel test1MainViewModel = Test1MainModel.getInstance();
    private Context mContext;
    public Test1MainAdapter test1MainAdapter;


    public Test1MainViewModel(@NonNull Application application) {
        super(application);
        mContext = application;
        test1MainAdapter = new Test1MainAdapter(application);
    }

    public void getMpData(){
        /**
         * 處理邏輯
         * 建立ViewModel、Adapter及Adapter佈局檔案。
         * 1.建立Observer響應事件
         */
        Observer<BaseData<List<MpBean>>> observer = new Observer<BaseData<List<MpBean>>>() {

            @Override
            public void onSubscribe(@io.reactivex.rxjava3.annotations.NonNull Disposable d) {

            }

            @Override
            public void onNext(@io.reactivex.rxjava3.annotations.NonNull BaseData<List<MpBean>> listBaseData) {
                if (listBaseData.getErrorCode() == 0) {
                    test1MainAdapter.setList(listBaseData.getData());
                }
            }

            @Override
            public void onError(@io.reactivex.rxjava3.annotations.NonNull Throwable e) {
                // error
            }

            @Override
            public void onComplete() {
                Toast.makeText(mContext, "get data completed.", Toast.LENGTH_SHORT).show();
            }
        };
        test1MainViewModel.subscribe(observer);
    }
}
