package com.maia_business_solutions.support.v4.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

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
  
  public static RetainedDataFragment getDataFragment(final FragmentManager fm)
  {
    RetainedDataFragment retVal = null;
    
    retVal = (RetainedDataFragment) fm.findFragmentByTag(TAG);
    
    if (retVal == null) {
      retVal = new RetainedDataFragment();
      
      final FragmentTransaction ft = fm.beginTransaction();
      ft.add(retVal, TAG);
      ft.commit();
    }
    
    return retVal;
  }
}