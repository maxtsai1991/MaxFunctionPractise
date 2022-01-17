package com.example.demo.jsonpersoncat.util;

import static android.view.MotionEvent.ACTION_UP;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.example.demo.jsonpersoncat.MainActivity;


public class CommonUtil {

    public CommonUtil() {
        throw new UnsupportedOperationException("Should not create instance of Util class. Please use as static.");
    }

    /**
     * 傳入的 View（及其 子View）被點擊時，若非 EditText 則隱藏鍵盤
     *
     * @param view 要覆寫 onTouch() 的 View
     */
    @SuppressLint("ClickableViewAccessibility")
    public static void initializeTouchEvent(View view) {
        if (!(view instanceof EditText)) {
            view.setOnTouchListener((v, event) -> {
                if (event.getAction() == ACTION_UP) {
                    hideSoftKeyboard(v);
                }
                return false;
            });
        }

        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View childView = viewGroup.getChildAt(i);
                initializeTouchEvent(childView);
            }
        }
    }

    /**
     * 傳入的 View（及其 子View）被點擊時，自定義 click 事件
     *
     * @param view     要覆寫 onTouch() 的 View
     * @param listener 傳入 OnClickListener
     */
    @SuppressLint("ClickableViewAccessibility")
    public static void initializeTouchEvent(View view, View.OnClickListener listener) {
        if (view instanceof ViewGroup) {
            ViewGroup viewGroup = (ViewGroup) view;
            view.setOnClickListener(listener);
            for (int i = 0; i < viewGroup.getChildCount(); i++) {
                View childView = viewGroup.getChildAt(i);
                initializeTouchEvent(childView, listener);
            }
        }
    }

    /**
     * 隱藏鍵盤
     */
    public static void hideSoftKeyboard(View v) {
        InputMethodManager inputMethodManager = (InputMethodManager) MainActivity.getInstance().getSystemService(Context.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
    }
}
