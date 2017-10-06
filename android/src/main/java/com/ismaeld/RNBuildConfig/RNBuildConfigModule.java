package com.ismaeld.RNBuildConfig;

import android.util.Log;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class RNBuildConfigModule extends ReactContextBaseJavaModule {
    private Class buildConfigClass;
    private String NAME = "RNBuildConfig";

    public RNBuildConfigModule(ReactApplicationContext reactContext, Class buildConfigClass) {
        super(reactContext);
        this.buildConfigClass = buildConfigClass;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public Map<String, Object> getConstants() {
        final Map<String, Object> constants = new HashMap<>();

        Field[] fields = buildConfigClass.getDeclaredFields();
        for (Field f : fields) {
            try {
                constants.put(f.getName(), f.get(null));
            } catch (IllegalAccessException e) {
                Log.d(NAME, "Could not access BuildConfig field " + f.getName());
            }
        }

        return constants;
    }
}