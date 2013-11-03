package com.maia_business_solutions.debug;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

abstract public class ActivityDebug extends Activity {
  public ActivityDebug()
  {
  }
  
  public abstract String getName();

  @Override
  protected void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    Log.d(getName(), "onCreate");
  }
  
  @Override
  protected void onStart()
  {
    super.onStart();
    Log.d(getName(), "onStart");
  }

  @Override
  protected void onResume()
  {
    super.onResume();
    Log.d(getName(), "onResume");
  }

  @Override
  protected void onPause()
  {
    super.onPause();
    Log.d(getName(), "onPause");
  }

  @Override
  protected void onStop()
  {
    super.onStop();
    Log.d(getName(), "onStop");
  }
  
  @Override
  protected void onDestroy()
  {
    super.onDestroy();
    Log.d(getName(), "onDestroy");
  }

  @Override
  protected void onRestart()
  {
    super.onRestart();
    Log.d(getName(), "onRestart");
  }
  
  @Override
  public void onConfigurationChanged(Configuration newConfig)
  {
    Log.d(getName(), "onConfigurationChanged");
    super.onConfigurationChanged(newConfig);
    /*
    // Checks the orientation of the screen
    if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
      Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
    }
    else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
      Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
    }*/
  }
}