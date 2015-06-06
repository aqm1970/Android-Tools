package com.maia_business_solutions;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetworkBroadcastReceiver extends BroadcastReceiver
{
  private static final String LOG_TAG = NetworkBroadcastReceiver.class.getSimpleName();
  
  private boolean connected = false;
  private ConnectionListener listener = null;
  
  public NetworkBroadcastReceiver()
  {
    this(null);
  }
  
  public NetworkBroadcastReceiver(final ConnectionListener listener)
  {
    this.listener = listener;
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
     * Connection established
     * @param context The Context in which the receiver is running.
     * @param intent The Intent being received.
     */
    public void connected(final Context context, final Intent intent);
    
    /**
     * Not connected.
     * @param context The Context in which the receiver is running.
     * @param intent The Intent being received.
     */
    public void notConnected(final Context context, final Intent intent);
  }
}