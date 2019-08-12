package com.reactlibrary;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.SimpleViewManager;

import com.mapbox.services.android.navigation.ui.v5.NavigationView;
import com.mapbox.services.android.navigation.ui.v5.OnNavigationReadyCallback;
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute;
import com.mapbox.services.android.navigation.ui.v5.NavigationViewOptions;
import com.mapbox.api.directions.v5.models.DirectionsResponse;
import com.mapbox.geojson.Point;

import android.os.Handler;
import android.os.Looper;

import retrofit2.Call;
import retrofit2.Response;

public class MapboxNavigationViewManager extends SimpleViewManager<NavigationView> implements retrofit2.Callback<DirectionsResponse>, OnNavigationReadyCallback{

  public static final String REACT_CLASS = "MapboxNavigationView";
  private NavigationView mNavigationView;
  private ThemedReactContext reactContext;

  private static final double ORIGIN_LONGITUDE = -3.714873;
  private static final double ORIGIN_LATITUDE = 40.397389;
  private static final double DESTINATION_LONGITUDE = -3.712331;
  private static final double DESTINATION_LATITUDE = 40.401686;

  public NavigationView createViewInstance(ThemedReactContext context) {
    this.reactContext = context;
    mNavigationView = new NavigationView(context.getCurrentActivity());
    mNavigationView.onCreate(null);
    mNavigationView.initialize(this);
    return mNavigationView;
  }

  @Override
  public void onNavigationReady(boolean isRunning) {
    Point origin = Point.fromLngLat(ORIGIN_LONGITUDE, ORIGIN_LATITUDE);
    Point destination = Point.fromLngLat(DESTINATION_LONGITUDE, DESTINATION_LATITUDE);

    NavigationRoute.builder(reactContext)
            .accessToken("pk.eyJ1IjoibWljaGFlbHNsYWJlbnlhayIsImEiOiJjanoxZWhvN3Iwa2t4M2N1b3h2ZHkwNHR1In0.TkL-kezlHSnPdkl0uIQomg")
            .origin(origin)
            .destination(destination)
            .build()
            .getRoute(this);
  }

  @Override
  public void onResponse(Call<DirectionsResponse> call, final Response<DirectionsResponse> response) {
    System.out.println(response);
    new Handler(Looper.getMainLooper()).post(new Runnable() {
        @Override
        public void run() {
            NavigationViewOptions options = NavigationViewOptions.builder()
                    .directionsRoute(response.body().routes().get(0))
                    .shouldSimulateRoute(true)
                    .build();

            mNavigationView.startNavigation(options);
          }
      });
  }

  @Override
  public void onFailure(Call<DirectionsResponse> call, Throwable t) {

  }

  @Override
  public String getName() {
    return REACT_CLASS;
  }

}