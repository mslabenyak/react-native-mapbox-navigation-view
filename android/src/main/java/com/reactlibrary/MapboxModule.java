package com.reactlibrary;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Promise;

import com.mapbox.mapboxsdk.Mapbox;

import android.os.Handler;
import android.os.Looper;

public class MapboxModule extends ReactContextBaseJavaModule {

  private ReactApplicationContext reactContext;

  public MapboxModule(ReactApplicationContext reactContext) {
    super(reactContext);
    this.reactContext = reactContext;   
  }

  @Override
  public String getName() {
    return "Mapbox";
  }

  @ReactMethod
  public void setAccessToken(final String accessToken) {
    new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Mapbox.getInstance(MapboxModule.this.reactContext, accessToken);
            }
        });
  }

  @ReactMethod
    public void getAccessToken(Promise promise) {
        WritableMap map = Arguments.createMap();
        map.putString("accessToken", Mapbox.getAccessToken());
        promise.resolve(map);
    }
}