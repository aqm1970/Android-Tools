package com.maia_business_solutions.debug;

import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public abstract class ListFragmentDebug extends ListFragment
{
  protected abstract String getLogTag();
  
  @Override
  public void onDestroyView()
  {
    Log.i(getLogTag(), "ListFragment.onDestroyView");
    
    super.onDestroyView();
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState)
  {
    Log.i(getLogTag(), "ListFragment.onViewCreated");
    super.onViewCreated(view, savedInstanceState);
  }

  @Override
  public void onActivityCreated(Bundle savedInstanceState)
  {
    Log.i(getLogTag(), "ListFragment.onActivityCreated");
    super.onActivityCreated(savedInstanceState);
  }

  @Override
  public void onAttach(Context context)
  {
    Log.i(getLogTag(), "ListFragment.onAttach");
    super.onAttach(context);
  }

  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    Log.i(getLogTag(), "ListFragment.onCreate");
    super.onCreate(savedInstanceState);
  }

  @Override
  public void onDestroy()
  {
    Log.i(getLogTag(), "ListFragment.onDestroy");
    super.onDestroy();
  }

  @Override
  public void onDetach()
  {
    Log.i(getLogTag(), "ListFragment.onDetach");
    super.onDetach();
  }

  @Override
  public void onPause()
  {
    Log.i(getLogTag(), "ListFragment.onPause");
    super.onPause();
  }

  @Override
  public void onResume()
  {
    Log.i(getLogTag(), "ListFragment.onResume");
    super.onResume();
  }

  @Override
  public void onStart()
  {
    Log.i(getLogTag(), "ListFragment.onStart");
    super.onStart();
  }

  @Override
  public void onStop()
  {
    Log.i(getLogTag(), "ListFragment.onStop");
    
    super.onStop();
  }
  
  @Override
  public void onPrepareOptionsMenu(Menu menu)
  {
    super.onPrepareOptionsMenu(menu);
    
    Log.i(getLogTag(), "ListFragment.onPrepareOptionsMenu");
  }
}