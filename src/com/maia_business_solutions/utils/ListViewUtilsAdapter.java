package com.maia_business_solutions.utils;

import android.widget.ListAdapter;

public interface ListViewUtilsAdapter <T> extends ListAdapter
{
  /* Method that add a new element and call the version appropriate addAll
   * or add methods on ListAdapter 
   */
  public void addData(final T value);
}