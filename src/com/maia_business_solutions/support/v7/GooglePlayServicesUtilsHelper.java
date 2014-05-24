package com.maia_business_solutions.support.v7;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;

public class GooglePlayServicesUtilsHelper
{
  private static final String LOG_TAG =
    GooglePlayServicesUtilsHelper.class.getSimpleName();
  
  public static boolean servicesConnected(final FragmentActivity activity)
  {
    if (activity == null)
      return false;
    
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
      // Get the error dialog from Google Play services
      Dialog errorDialog = GooglePlayServicesUtil.getErrorDialog(
          resultCode,
          activity,
          CONNECTION_FAILURE_RESOLUTION_REQUEST);

      // If Google Play services can provide an error dialog
      if (errorDialog != null) {
        // Create a new DialogFragment for the error dialog
        ErrorDialogFragment errorFragment =
          new ErrorDialogFragment();
        // Set the dialog in the DialogFragment
        errorFragment.setDialog(errorDialog);
        // Show the error dialog in the DialogFragment
        errorFragment.show(activity.getSupportFragmentManager(),
            "");
        
        retVal = true;
      }
    }
    
    return retVal;
  }

  // Global constants
  /*
   * Define a request code to send to Google Play services
   * This code is returned in Activity.onActivityResult
   */
  private final static int
  CONNECTION_FAILURE_RESOLUTION_REQUEST = 9000;

  public static class ErrorDialogFragment extends DialogFragment {
    // Global field to contain the error dialog
    private Dialog mDialog;
    // Default constructor. Sets the dialog field to null
    public ErrorDialogFragment() {
      super();
      mDialog = null;
    }
    // Set the dialog to display
    public void setDialog(Dialog dialog) {
      mDialog = dialog;
    }
    // Return a Dialog to the DialogFragment.
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
      return mDialog;
    }
  }
}