package com.maia_business_solutions.utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.widget.TextView;

public class ViewUtils {
  private final Context context;
  private LayoutInflater inflater = null;
  
  public ViewUtils(final Context context)
  {
    this.context = context;
  }
  
  public TextView createTextView(final int id)
  {
    final TextView retVal = new TextView(context);
    
    retVal.setText(id);
    
    return retVal;
  }
  
  public TextView inflate(final int viewID, final int stringID)
  {
    final TextView retVal;
    
    retVal = (TextView) getInflater().inflate(viewID, null);
    
    retVal.setText(stringID);
    
    return retVal;
  }
  
  private LayoutInflater getInflater()
  {
    if (inflater == null) {
      inflater =
        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }
    
    return inflater;
  }
}