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
    Log.i(getName(), "onCreate");
  }
  
  @Override
  protected void onStart()
  {
    super.onStart();
    Log.i(getName(), "onStart");
  }

  @Override
  protected void onResume()
  {
    super.onResume();
    Log.i(getName(), "onResume");
  }

  @Override
  protected void onPause()
  {
    super.onPause();
    Log.i(getName(), "onPause");
  }

  @Override
  protected void onStop()
  {
    super.onStop();
    Log.i(getName(), "onStop");
  }
  
  @Override
  protected void onDestroy()
  {
    super.onDestroy();
    Log.i(getName(), "onDestroy");
  }

  @Override
  protected void onRestart()
  {
    super.onRestart();
    Log.i(getName(), "onRestart");
  }
  
  @Override
  public void onConfigurationChanged(Configuration newConfig)
  {
    Log.i(getName(), "onConfigurationChanged");
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