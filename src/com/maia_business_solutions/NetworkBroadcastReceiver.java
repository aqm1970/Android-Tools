package com.maia_business_solutions;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetworkBroadcastReceiver extends BroadcastReceiver
{
  private static final String LOG_TAG = NetworkBroadcastReceiver.class.getSimpleName();
  
  private boolean connected = false;
  private ConnectionListener listener = null;
  
  public NetworkBroadcastReceiver(final ConnectionListener listener)
  {
    this.listener = listener;
  }
  
  public void registerReceiver(final Context context)
  {
    registerReceiver(context, false);
  }
  
  /**
   * Register a BroadcastReceiver to be run in the main activity thread. The
   * receiver will be called with any broadcast Intent that matches filter
   * for {@link ConnectivityManager#CONNECTIVITY_ACTION}, in the main
   * application thread. 
   * @param context the context to register the filter
   * @param fire fire the {@link ConnectionListener}
   */
  public void registerReceiver(final Context context, final boolean fire)
  {
    context.registerReceiver(this, new IntentFilter(
        ConnectivityManager.CONNECTIVITY_ACTION));
    
    final ConnectivityManager manager =
        (ConnectivityManager) context.getSystemService(
            Context.CONNECTIVITY_SERVICE);
    
    final NetworkInfo info = manager.getActiveNetworkInfo();
    
    if (info != null)
      connected = info.isConnected();
    else
      connected = false;
    
    if (fire && listener != null) {
      if (connected)
        listener.connected(context, null);
      else
        listener.notConnected(context, null);
    }
  }
  
  public void unregisterReceiver(final Context context)
  {
    context.unregisterReceiver(this);
    
    connected = false;
  }

  @Override
  public void onReceive(final Context context, final Intent intent)
  {
    if (intent.getAction().equals(ConnectivityManager.CONNECTIVITY_ACTION)) {
      final ConnectivityManager manager =
          (ConnectivityManager) context.getSystemService(
              Context.CONNECTIVITY_SERVICE);
      
      final NetworkInfo networkInfo = manager.getActiveNetworkInfo();
      
      if (networkInfo != null && networkInfo.isConnected()) {
        if (connected == false) {
          Log.d(LOG_TAG, "Connection to network: " + networkInfo.getTypeName());

          connected = true;

          if (listener != null)
            listener.connected(context, intent);
        }
      }
      else if (connected) {
        connected = false;
        
        if (listener != null)
          listener.notConnected(context, intent);
        
        Log.d(LOG_TAG, "No active network is connected");
      }
    }
  }
  
  public void setListener(final ConnectionListener listener)
  {
    this.listener = listener;
  }

  public boolean isConnected()
  {
    return connected;
  }
  
  public static interface ConnectionListener
  {
    /**
     * Fires when a network connection is established
     * @param context The Context in which the receiver is running.
     * @param intent The Intent being received; or null if this is an
     * initialization action
     */
    public void connected(final Context context, final Intent intent);
    
    /**
     * Fires when a network connection is not established.
     * @param context The Context in which the receiver is running.
     * @param intent The Intent being received; or null if this is an
     * initialization action.
     */
    public void notConnected(final Context context, final Intent intent);
  }
}