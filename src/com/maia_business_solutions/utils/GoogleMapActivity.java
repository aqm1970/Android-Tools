package com.maia_business_solutions.utils;

import android.os.Bundle;

import com.google.android.gms.maps.MapView;
import com.maia_business_solutions.debug.ActivityDebug;

public abstract class GoogleMapActivity extends ActivityDebug {
  private MapView mapView;
  
  @Override
  abstract public String getName();
  
  protected MapView getMapView()
  {
    return mapView;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    
    mapView = new MapView(this);
    mapView.onCreate(savedInstanceState);
  }

  @Override
  protected void onResume()
  {
    super.onResume();
    mapView.onResume();
  }

  @Override
  protected void onPause()
  {
    super.onPause();
    mapView.onPause();
  }

  @Override
  protected void onDestroy()
  {
    super.onDestroy();
    mapView.onDestroy();
  }

  @Override
  protected void onSaveInstanceState(Bundle outState)
  {
    super.onSaveInstanceState(outState);
    mapView.onSaveInstanceState(outState);
  }

  @Override
  public void onLowMemory()
  {
    super.onLowMemory();
    mapView.onLowMemory();
  }
}