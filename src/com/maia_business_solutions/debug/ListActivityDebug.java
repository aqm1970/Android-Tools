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
  protected void onRestoreInstanceState(Bundle state)
  {
    super.onRestoreInstanceState(state);
    Log.i(getName(), "onRestoreInstanceState");
  }

  @Override
  protected void onSaveInstanceState(Bundle outState)
  {
    super.onSaveInstanceState(outState);
    Log.i(getName(), "onSaveInstanceState");
  }
}
