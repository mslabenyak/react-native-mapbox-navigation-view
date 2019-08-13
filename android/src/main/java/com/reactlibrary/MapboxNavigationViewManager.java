package com.reactlibrary;

import com.facebook.react.uimanager.ThemedReactContext;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.annotations.ReactProp;

import com.mapbox.services.android.navigation.ui.v5.NavigationView;
import com.mapbox.services.android.navigation.ui.v5.OnNavigationReadyCallback;
import com.mapbox.services.android.navigation.v5.navigation.NavigationRoute;
import com.mapbox.services.android.navigation.ui.v5.NavigationViewOptions;
import com.mapbox.api.directions.v5.models.DirectionsResponse;
import com.mapbox.api.directions.v5.models.DirectionsRoute;
import com.mapbox.geojson.Point;
import com.mapbox.api.matching.v5.MapboxMapMatching;
import com.mapbox.api.matching.v5.models.MapMatchingResponse;
import com.mapbox.api.directions.v5.DirectionsCriteria;

import android.os.Handler;
import android.os.Looper;

import retrofit2.Call;
import retrofit2.Response;

public class MapboxNavigationViewManager extends SimpleViewManager<NavigationView> implements retrofit2.Callback<DirectionsResponse>, 
  OnNavigationReadyCallback{
    /*

    retrofit2.Callback<MapMatchingResponse>
    */

  public static final String REACT_CLASS = "MapboxNavigationView";
  private NavigationView mNavigationView;
  private ThemedReactContext reactContext;
  private DirectionsRoute route;

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
  /*
  @ReactProp(name = "route")
  public void setRoute(NavigationView view, ReadableArray coordinates) {
    
    MapboxMapMatching.builder()
      .accessToken("pk.eyJ1IjoibWljaGFlbHNsYWJlbnlhayIsImEiOiJjanoxZWhvN3Iwa2t4M2N1b3h2ZHkwNHR1In0.TkL-kezlHSnPdkl0uIQomg")
      .coordinates(coordinates.toArrayList())
      .steps(true)
      .voiceInstructions(true)
      .bannerInstructions(true)
      .profile(DirectionsCriteria.PROFILE_DRIVING)
      .build()
      .enqueueCall(this);
      
  }

  @Override
  public void onResponse(Call<MapMatchingResponse> call, Response<MapMatchingResponse> response) {
    
    if (response.isSuccessful()) {
      this.route = response.body().matchings().get(0).toDirectionRoute();
      mNavigationView.startNavigation(this.route);
    }
    
  }
  
  @Override
  public void onFailure(Call<MapMatchingResponse> call, Throwable throwable) {
  
  }
  */
}