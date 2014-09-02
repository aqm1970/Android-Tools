package com.maia_business_solutions.support.v4.debug;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class DebugFragment extends Fragment
{
  public abstract String getLogTag();

  @Override
  public void onActivityCreated(Bundle savedInstanceState)
  {
    super.onActivityCreated(savedInstanceState);
    
    Log.i(getLogTag(), "onActivityCreated");
  }

  @Override
  public void onAttach(Activity activity)
  {
    super.onAttach(activity);
    
    Log.i(getLogTag(), "onAttach");
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState)
  {
    Log.i(getLogTag(), "onCreateView");
    
    return super.onCreateView(inflater, container, savedInstanceState);
  }

  @Override
  public void onDestroy()
  {
    super.onDestroy();
    
    Log.i(getLogTag(), "onDestroy");
  }

  @Override
  public void onDestroyView()
  {
    super.onDestroyView();
    
    Log.i(getLogTag(), "onDestroyView");
  }

  @Override
  public void onDetach()
  {
    super.onDetach();
    
    Log.i(getLogTag(), "onDetach");
  }

  @Override
  public void onPause()
  {
    super.onPause();
    
    Log.i(getLogTag(), "onPause");
  }

  @Override
  public void onResume()
  {
    super.onResume();
    
    Log.i(getLogTag(), "onResume");
  }

  @Override
  public void onStart()
  {
    super.onStart();
    
    Log.i(getLogTag(), "onStart");
  }
}