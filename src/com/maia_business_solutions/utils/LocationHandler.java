package com.maia_business_solutions.utils;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;

public class LocationHandler implements LocationListener {
  private final LocationManager locationManager;
  private Location lastKnown = null;
  
  public LocationHandler(final Context context)
  {
    locationManager =
      (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
    
    lastKnown = getBestLocation(
        locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER),
        locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER));
  }

  @Override
  public void onLocationChanged(Location location)
  {
    lastKnown = getBestLocation(lastKnown, location);
  }

  @Override
  public void onStatusChanged(String provider, int status, Bundle extras)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void onProviderEnabled(String provider)
  {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void onProviderDisabled(String provider)
  {
    // TODO Auto-generated method stub
    
  }
  
  private static Location getBestLocation(final Location oldLocation,
      final Location newLocation)
  {
    Location retVal = newLocation;
    
    if (oldLocation != null) {
      
    }
    
    return retVal;
  }
}