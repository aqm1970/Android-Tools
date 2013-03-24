package com.maia_business_solutions.service;

import android.app.Service;
import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.util.Log;

public class GenericServiceConnection
    <S extends Service, B extends GenericBinder<S>> implements ServiceConnection
{
  S service = null;
  boolean bounded = false;
  
  public S getService()
  {
    return service;
  }

  public boolean isBounded()
  {
    return bounded;
  }

  @Override
  public void onServiceConnected(ComponentName arg0, IBinder arg1)
  {
    @SuppressWarnings("unchecked")
    final B binder = (B)arg1;
    Log.wtf(this.getClass().getSimpleName(), "Trying to connect to service " + arg0);
    if (binder != null) {
      service = binder.getService();
      bounded = true;
      Log.d(this.getClass().getName(), "Connected to service " + arg0);
    }
  }

  @Override
  public void onServiceDisconnected(ComponentName name)
  {
    bounded = false;
    service = null;
    Log.d(this.getClass().getSimpleName(), "Disconnected from service " + name);
  }
}