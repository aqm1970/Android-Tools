package com.maia_business_solutions.support.v7;

import java.util.Date;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.maia_business_solutions.tools.R;

public class GooglePlayServicesUtilsHelper
{
  private static long _24_HOURS_IN_MILLISECONDS = 86400000;
  private static long _7_DAYS_IN_MILLISECONDS = 7 * _24_HOURS_IN_MILLISECONDS;
  
  private static final String REMIND_ME = "REMIND";
  
  private static final String LOG_TAG =
    GooglePlayServicesUtilsHelper.class.getSimpleName();
  
  public static boolean servicesConnected(final FragmentActivity activity)
  {
    if (activity == null)
      throw new NullPointerException("activity is null");
    
    boolean retVal = false;
    
    // Check that Google Play services is available
    final int resultCode =
      GooglePlayServicesUtil.isGooglePlayServicesAvailable(activity);
    
    // If Google Play services is available
    if (ConnectionResult.SUCCESS == resultCode) {
      // In debug mode, log the status
      Log.d(LOG_TAG, "Google Play services is available.");
      
      retVal = true;
    }
    else {
      final SharedPreferences preference =
          activity.getPreferences(Context.MODE_PRIVATE);
      
      final Date now = new Date();
      final long remindMe = preference.getLong(REMIND_ME, 0);

      if (remindMe >= 0  && now.getTime() >= remindMe) {
        // Create a new DialogFragment for the error dialog
        final ErrorDialogFragment errorFragment =
            new ErrorDialogFragment();

        errorFragment.setData(resultCode);

        // Show the error dialog in the DialogFragment
        errorFragment.show(activity.getSupportFragmentManager(),
            "");
      }
    }

    return retVal;
  }

  // Global constants
  /*
   * Define a request code to send to Google Play services
   * This code is returned in Activity.onActivityResult
   */
  public final static int CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

  public static class ErrorDialogFragment extends DialogFragment {
    private static final int NEVER_REMIND_ME = -1;
    private int errorCode;
    
    public ErrorDialogFragment() {
      super();
    }
    
    public void setData(final int errorCode) {
      this.errorCode = errorCode;
    }
    
    // Return a Dialog to the DialogFragment.
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
      final Activity activity = getActivity();
      
      final Dialog dialog = GooglePlayServicesUtil.getErrorDialog(errorCode,
          activity, CONNECTION_FAILURE_RESOLUTION_REQUEST);
      
      final AlertDialog alertDialog = (AlertDialog)dialog;
      
      if (alertDialog != null) {
        alertDialog.setMessage(
            activity.getString(R.string.play_services_error_msg));
        
        alertDialog.setButton(DialogInterface.BUTTON_NEUTRAL, "Remind me", new OnClickListener()
        {
          @Override
          public void onClick(DialogInterface dialog, int which)
          {
            final Date remindMe =
                new Date(new Date().getTime() + _7_DAYS_IN_MILLISECONDS);
            
            final Editor prefEditor =
                activity.getPreferences(Context.MODE_PRIVATE).edit();
            
            prefEditor.putLong(REMIND_ME, remindMe.getTime());
            
            prefEditor.commit();
            
            Log.i(LOG_TAG, "Remind after: " + remindMe.toString());
          }
        });
        
        alertDialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Never", new OnClickListener()
        {
          
          @Override
          public void onClick(DialogInterface dialog, int which)
          {
            final Editor prefEditor =
                activity.getPreferences(Context.MODE_PRIVATE).edit();
            
            prefEditor.putLong(REMIND_ME, NEVER_REMIND_ME);
            
            prefEditor.commit();
            
            Log.i(LOG_TAG, "Never remind me selected on" +
                new Date().toString());
          }
        });
      }
      
      return dialog;
    }

    @Override
    public void onCancel(DialogInterface dialog)
    {
      super.onCancel(dialog);
      
      final Date remindMe =
          new Date(new Date().getTime() + _24_HOURS_IN_MILLISECONDS);
      
      final Editor prefEditor =
          getActivity().getPreferences(Context.MODE_PRIVATE).edit();
      
      prefEditor.putLong(REMIND_ME, remindMe.getTime());
      
      prefEditor.commit();
      Log.i(LOG_TAG, "Remind me after: " + remindMe.toString());
    }
  }
}