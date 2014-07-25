package com.maia_business_solutions.utils;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

public final class ActivityUtils
{
  public static void hideKeyboard(final Activity activity)
  {
    final View currentFocus = activity.getCurrentFocus();
    
    if (currentFocus != null) {
      final InputMethodManager manager =
        (InputMethodManager) activity.getSystemService(
            Activity.INPUT_METHOD_SERVICE);
      
      if (manager != null)
        manager.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
    }
  }
}