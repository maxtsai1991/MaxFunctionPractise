package com.example.demo.jsonpersoncat.util;

import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Configuration;
import android.content.res.Resources;

public class MyContextWrapper extends ContextWrapper {
    public MyContextWrapper(Context base) {
        super(base);
    }
    public static ContextWrapper wrap(Context context){
        Context overrideContext = context;
        Resources res = context.getResources();
        Configuration configuration = res.getConfiguration();
        configuration.fontScale = 1.0f;
        overrideContext = overrideContext.createConfigurationContext(configuration);
        return new ContextWrapper(overrideContext);
    }
}
