package com.maia_business_solutions.debug;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.MapFragment;

public abstract class MapFragmentDebug extends MapFragment
{
  protected abstract String getName();

  @Override
  public void onActivityCreated(Bundle savedInstanceState)
  {
    super.onActivityCreated(savedInstanceState);
    
    Log.i(getName(), "onActivityCreated");
  }

  @Override
  public void onAttach(Activity activity)
  {
    super.onAttach(activity);
    
    Log.i(getName(), "onAttach");
  }

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    
    Log.i(getName(), "onCreate");
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState)
  {
    final View view = super.onCreateView(inflater, container, savedInstanceState);
    
    Log.i(getName(), "onCreateView");
    
    return view;
  }

  @Override
  public void onDestroyView()
  {
    super.onDestroyView();
    
    Log.i(getName(), "onDestroyView");
  }

  @Override
  public void onPause()
  {
    super.onPause();
    
    Log.i(getName(), "onPause");
  }

  @Override
  public void onResume()
  {
    super.onResume();
    
    Log.i(getName(), "onResume");
  }

  @Override
  public void onDetach()
  {
    super.onDetach();
    
    Log.i(getName(), "onDetach");
  }

  @Override
  public void onStart()
  {
    super.onStart();
    
    Log.i(getName(), "onStart");
  }

  @Override
  public void onStop()
  {
    super.onStop();
    
    Log.i(getName(), "onStop");
  }
}