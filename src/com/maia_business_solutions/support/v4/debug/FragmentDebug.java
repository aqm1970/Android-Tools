package com.maia_business_solutions.support.v4.debug;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.View;

public abstract class FragmentDebug extends Fragment
{
  protected abstract String getLogTag();

  @Override
  public void onAttach(Context context)
  {
    super.onAttach(context);
    
    Log.i(getLogTag(), "onAttach");
  }

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    
    Log.i(getLogTag(), "onCreate");
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState)
  {
    super.onViewCreated(view, savedInstanceState);
    
    Log.i(getLogTag(), "onViewCreated");
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState)
  {
    super.onActivityCreated(savedInstanceState);
    
    Log.i(getLogTag(), "onActivityCreated");
  }

  @Override
  public void onViewStateRestored(Bundle savedInstanceState)
  {
    super.onViewStateRestored(savedInstanceState);
    
    Log.i(getLogTag(), "onViewStateRestored");
  }

  @Override
  public void onStart()
  {
    super.onStart();
    
    Log.i(getLogTag(), "onStart");
  }

  @Override
  public void onResume()
  {
    super.onResume();
    
    Log.i(getLogTag(), "onResume");
  }

  @Override
  public void onSaveInstanceState(Bundle outState)
  {
    super.onSaveInstanceState(outState);
    
    Log.i(getLogTag(), "onSaveInstanceState");
  }

  @Override
  public void onPause()
  {
    super.onPause();
    
    Log.i(getLogTag(), "onPause");
  }

  @Override
  public void onStop()
  {
    super.onStop();
    
    Log.i(getLogTag(), "onStop");
  }

  @Override
  public void onDestroyView()
  {
    super.onDestroyView();
    
    Log.i(getLogTag(), "onDestroyView");
  }

  @Override
  public void onDestroy()
  {
    super.onDestroy();
    
    Log.i(getLogTag(), "onDestroy");
  }

  @Override
  public void onDetach()
  {
    super.onDetach();
    
    Log.i(getLogTag(), "onDetach");
  }
}