package com.maia_business_solutions;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;

public class RetainedDataFragment extends Fragment
{
  public static final String TAG = RetainedDataFragment.class.getName();
  
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
      
      Log.i(TAG, "Committed retained data fragment" + fm);
    }
    
    return retVal;
  }
}