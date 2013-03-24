package com.maia_business_solutions.debug;

import android.app.ListActivity;
import android.os.Bundle;
import android.util.Log;

abstract public class ListActivityDebug extends ListActivity {
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
  protected void onRestoreInstanceState(Bundle state)
  {
    super.onRestoreInstanceState(state);
    Log.d(getName(), "onRestoreInstanceState");
  }

  @Override
  protected void onSaveInstanceState(Bundle outState)
  {
    super.onSaveInstanceState(outState);
    Log.d(getName(), "onSaveInstanceState");
  }
}
