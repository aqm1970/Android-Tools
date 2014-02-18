package com.maia_business_solutions.location;

import java.util.Vector;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class BasicAndroidLocationListener implements LocationListener
{
  // 1 minutes
  private final static long ONE_MINUTE = 1 * 60 * 1000;
  
  // In milli-seconds, use to conserve battery
  private final static Integer UPDATE_RATE = 30 * 1000;
  
  private final static Float UPDATE_DISTANCE_IN_METERS = 9.144f;
  
  final Context context;
  
  private Location bestKnownLocation = null;
  private LocationManager locationManager;
  
  private final Vector<UpdateAndroidLocationListener> listeners =
    new Vector<UpdateAndroidLocationListener>();
  
  public BasicAndroidLocationListener(final Context context)
  {
    this.context = context;
    
    locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
  }

  private void onStart()
  {
    // Request updates every 30 ft
    locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
        UPDATE_RATE, UPDATE_DISTANCE_IN_METERS, this);
    
    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
        UPDATE_RATE, UPDATE_DISTANCE_IN_METERS, this);
  }
  
  private void onStop()
  {
    locationManager.removeUpdates(this);
  }
  
  public void addUpdateLocationListener(final UpdateAndroidLocationListener listener)
  {
    if (listeners.size() == 0)
      onStart();
    
    listeners.add(listener);
  }
  
  public void removeUpdateLocationListener(final UpdateAndroidLocationListener listener)
  {
    listeners.remove(listener);
    
    if (listeners.size() == 0)
      onStop();
  }
  
  @Override
  public void onStatusChanged(String provider, int status, Bundle extras)
  {
    String value = provider;
    
    switch (status) {
      case LocationProvider.AVAILABLE :
        value += " is availabe";
        break;
      case LocationProvider.OUT_OF_SERVICE :
        value += " is out of service";
        break;
      case LocationProvider.TEMPORARILY_UNAVAILABLE :
        value += " is temporarily unavailable";
        break;
        default :
          value += " is unknown";
    }
    
    Toast.makeText(context, value, Toast.LENGTH_SHORT).show();
    
    Log.i(getName(), value);
  }
  
  @Override
  public void onProviderEnabled(String provider)
  {
    final String value = provider + " is enabled";
    
    Toast.makeText(context, value, Toast.LENGTH_SHORT).show();
    
    Log.i(getName(), value);
  }
  
  @Override
  public void onProviderDisabled(String provider)
  {
    final String value = provider + " is disabled";
    
    Toast.makeText(context, value, Toast.LENGTH_SHORT).show();
    
    Log.i(getName(), value);
  }
  
  @Override
  public void onLocationChanged(Location location)
  {
    if (isNewLocationBetter(location))
    {
      for (final UpdateAndroidLocationListener listener : listeners)
        listener.update(bestKnownLocation);
    }
  }
  
  /** Determines whether one Location reading is better than the current Location fix
   * @param newLocation  The new Location that you want to evaluate
   * @return Whether bestKnownLocation was updated
   */
  protected boolean isNewLocationBetter(final Location newLocation) {
    if (bestKnownLocation == null) {
      // A new location is always better than no location
      bestKnownLocation = newLocation;
      return true;
    }

    // Check whether the new location fix is newer or older
    long timeDelta = newLocation.getTime() - bestKnownLocation.getTime();
    
    // Will check against 45 seconds because trains can reposition quickly
    boolean isSignificantlyNewer = timeDelta > ONE_MINUTE * 0.75;
    boolean isSignificantlyOlder = timeDelta < -ONE_MINUTE * 0.75;
    boolean isNewer = timeDelta > 0;

    // If it's been more than two minutes since the current location, use the new location
    // because the user has likely moved
    if (isSignificantlyNewer) {
      bestKnownLocation = newLocation;
      return true;
      // If the new location is more than 45 seconds older, it must be worse
    }
    else if (isSignificantlyOlder) {
      return false;
    }

    // Check whether the new location fix is more or less accurate
    int accuracyDelta = (int) (newLocation.getAccuracy() - bestKnownLocation.getAccuracy());
    boolean isLessAccurate = accuracyDelta > 0;
    boolean isMoreAccurate = accuracyDelta < 0;
    boolean isSignificantlyLessAccurate = accuracyDelta > 50;

    // Check if the old and new location are from the same provider
    boolean isFromSameProvider = isSameProvider(newLocation.getProvider(),
        bestKnownLocation.getProvider());

    // Determine location quality using a combination of timeliness and accuracy
    if (isMoreAccurate) {
      bestKnownLocation = newLocation;
      return true;
    }
    else if (isNewer && !isLessAccurate) {
      bestKnownLocation = newLocation;
      return true;
    }
    else if (isNewer && !isSignificantlyLessAccurate && isFromSameProvider) {
      bestKnownLocation = newLocation;
      return true;
    }
    
    return false;
  }

  /** Checks whether two providers are the same */
  private static boolean isSameProvider(String provider1, String provider2)
  {
    if (provider1 == null)
      return provider2 == null;
    
    return provider1.equals(provider2);
  }
  
  private String getName()
  {
    return BasicAndroidLocationListener.class.getSimpleName();
  }
}
