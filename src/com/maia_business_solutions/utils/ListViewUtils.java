package com.maia_business_solutions.utils;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

final public class ListViewUtils
{
  private static final String NAME = ListViewUtils.class.getSimpleName();
  
  private static final String POSITION_STATE = NAME + "_POSITION_STATE";
  
  private static final int FIRST_POS = 0;
  private static final int PADDING = 1;
  
  public static <T> void maintainPositionFromTop(final ListView listView,
      final ListViewUtilsAdapter<T> adapter, final T result)
  {
    try {
      final int posValues[] = getPositionValues(listView);
      
      adapter.addData(result);
      
      setSelectionFromTop(listView, posValues);
    }
    catch (Exception e) {
      Log.e(NAME, "ListViewUtils.maintainPositionFromTop", e);
    }
  }
  
  private static void setSelectionFromTop(final ListView listView,
      final int posValues[])
  {
    setSelectionFromTop(listView, posValues[FIRST_POS], posValues[PADDING]);
  }
  
  private static void setSelectionFromTop(final ListView listView,
      final int firstVisiblePosition, final int padding)
  {
    listView.setSelectionFromTop(firstVisiblePosition, padding);
  }
  
  public static void onSaveInstanceState(final ListView listView,
      final Bundle outState)
  {
    try {
      outState.putIntArray(POSITION_STATE, getPositionValues(listView));
    }
    catch (Exception e) {
      Log.e(NAME, "ListViewUtils.onSaveInstanceState: Unable to save state", e);
    }
  }
  
  public static void onRestoreInstanceState(final ListView listView,
      final Bundle state)
  {
    int posValues[] = state.getIntArray(POSITION_STATE);
    
    if (posValues != null)
      setSelectionFromTop(listView, posValues);
  }
  
  private static int[] getPositionValues(final ListView listView) throws Exception
  {
    final int firstVisiblePosition = listView.getFirstVisiblePosition();
    
    final View firstView = listView.getChildAt(0);
    
    int padding = 0;
    
    if (firstView != null)
      padding = firstView.getTop();
    
    return new int[] { firstVisiblePosition, padding };
  }
}