package com.example.demo.jsonpersoncat.util;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentCallbacks;
import android.content.res.Configuration;
import android.util.DisplayMetrics;

import androidx.annotation.NonNull;

public class ScreenUtil {

    /** 設計稿標準 */
    private static final float width = 720f;
    private static final float high = 1280f;


    private static float textDensity = 0;
    private static float textScaledDensity = 0;


    /**
     * 根據當前裝置物理尺寸和解析度去動態計算density、densityDpi、scaledDensity
     * 同時也解決了使用者修改系統字型的情況
     * @param activity Context
     */
    public static void setCustomDensity(@NonNull Activity activity) {
        setCustomDensity(activity, false);
    }


    /**
     * @param activity Context
     * @param isLandscape 是否是橫屏
     */
    public static void setCustomDensity(@NonNull final Activity activity, boolean isLandscape) {
        final Application application = activity.getApplication();
        final DisplayMetrics displayMetrics = application.getResources().getDisplayMetrics();
        if (textDensity == 0) {
            textDensity = displayMetrics.density;
            textScaledDensity = displayMetrics.scaledDensity;
            application.registerComponentCallbacks(new ComponentCallbacks() {
                @Override
                public void onConfigurationChanged(Configuration configuration) {
                    if (configuration != null && configuration.fontScale > 0) {
                        textScaledDensity = application.getResources().getDisplayMetrics().scaledDensity;
                    }
                }

                @Override
                public void onLowMemory() {

                }
            });
        }

        final float targetDensity;
        if (isLandscape) { //橫屏
            targetDensity = displayMetrics.widthPixels / (high / 2); //當前UI標準720*1280
        } else {
            targetDensity = displayMetrics.widthPixels / (width / 2); //當前UI標準720*1280
        }


        final float targetScaledDensity = targetDensity * (textScaledDensity / textDensity);
        final int targetDpi = (int) (160 * targetDensity);

        displayMetrics.density = targetDensity;
        displayMetrics.scaledDensity = targetScaledDensity;
        displayMetrics.densityDpi = targetDpi;

        final DisplayMetrics activityDisplayMetrics = activity.getResources().getDisplayMetrics();
        activityDisplayMetrics.density = targetDensity;
        activityDisplayMetrics.scaledDensity = targetScaledDensity;
        activityDisplayMetrics.densityDpi = targetDpi;
    }

}
