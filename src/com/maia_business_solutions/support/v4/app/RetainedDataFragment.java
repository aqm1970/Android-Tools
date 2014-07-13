package com.maia_business_solutions.support.v4.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;

public class RetainedDataFragment extends Fragment
{
  public static final String TAG = RetainedDataFragment.class.getCanonicalName();
  
  private final Bundle dataBundle = new Bundle();
  
  @Override
  public void onCreate(Bundle savedInstanceState)
  {
    super.onCreate(savedInstanceState);
    
    setRetainInstance(true);
  }
  
  public Bundle getDataBundle()
  {
    return dataBundle;
  }
}