package com.reactlibrary;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.SimpleViewManager;

import com.mapbox.services.android.navigation.ui.v5.NavigationView;

public class MapboxNavigationViewManager extends SimpleViewManager<NavigationView> {

  public static final String REACT_CLASS = "MapboxNavigationView";

  public NavigationView createViewInstance(ThemedReactContext context) {
    return new NavigationView(context.getCurrentActivity());
  }

  @Override
  public String getName() {
    return REACT_CLASS;
  }
}