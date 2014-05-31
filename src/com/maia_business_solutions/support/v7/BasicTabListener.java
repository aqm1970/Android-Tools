package com.maia_business_solutions.support.v7;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar.Tab;
import android.support.v7.app.ActionBar.TabListener;
import android.util.Log;

public class BasicTabListener<T extends Fragment> implements TabListener
{
  private final static String LOG_TAG = BasicTabListener.class.getName();
  
  private Activity activity = null;
  private String tag = null;
  private Fragment fragment = null;
  private Class<T> clazz = null;
  private int contentID = 0;
  
  public BasicTabListener(final Activity activity, final String tag,
      final Class<T> clazz, final int id)
  {
    this.activity = activity;
    this.tag = tag;
    this.clazz = clazz;
    this.contentID = id;
  }
  
  @Override
  public void onTabReselected(Tab arg0, FragmentTransaction arg1)
  {
    logEvent("reselected");
  }

  @Override
  public void onTabSelected(Tab tab, FragmentTransaction ft)
  {
    logEvent("selected");
    
    if (fragment == null)
      fragment = createFragment();
    
    ft.replace(contentID, fragment, tag);
  }

  protected Fragment createFragment()
  {
    Log.i(LOG_TAG, "Creating fragment " + clazz.getName());
    
    return Fragment.instantiate(activity, clazz.getName());
  }

  @Override
  public void onTabUnselected(Tab tab, FragmentTransaction ft)
  {
    logEvent("unselected");
  }
  
  private void logEvent(final String event)
  {
    final StringBuffer buffer = new StringBuffer();
    
    buffer.append("Tab (");
    buffer.append(tag);
    buffer.append(") was ");
    buffer.append(event);
    
    Log.i(LOG_TAG, buffer.toString());
  }
}