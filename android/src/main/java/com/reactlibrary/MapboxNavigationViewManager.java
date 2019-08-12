package com.reactlibrary;

import android.os.Handler;
import android.os.Looper;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.SimpleViewManager;

import com.mapbox.services.android.navigation.ui.v5.NavigationView;

import com.mapbox.mapboxsdk.Mapbox;

public class MapboxNavigationViewManager extends SimpleViewManager<NavigationView> {

  public static final String REACT_CLASS = "MapboxNavigationView";
  private ThemedReactContext reactContext;

  public NavigationView createViewInstance(ThemedReactContext context) {
    reactContext = context;
    new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Mapbox.getInstance(reactContext, "pk.eyJ1IjoibWljaGFlbHNsYWJlbnlhayIsImEiOiJjanoxZWhvN3Iwa2t4M2N1b3h2ZHkwNHR1In0.TkL-kezlHSnPdkl0uIQomg");
            }
        });
    return new NavigationView(context);
  }
  @Override
  public String getName() {
    return REACT_CLASS;
  }
}