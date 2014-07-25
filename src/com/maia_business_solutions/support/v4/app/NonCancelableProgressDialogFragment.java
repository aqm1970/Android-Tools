package com.maia_business_solutions.support.v4.app;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

public class NonCancelableProgressDialogFragment extends DialogFragment
{
  private final static String TAG =
      NonCancelableProgressDialogFragment.class.getName();
  
  private ProgressDialog progressDialog;
  
  public NonCancelableProgressDialogFragment()
  {
    setStyle(STYLE_NORMAL, STYLE_NORMAL);
    setShowsDialog(true);
  }

  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState)
  {
    progressDialog = new NonCancelableDialog(getActivity());
    
    return progressDialog;
  }
  
  public ProgressDialog getProgressDialog()
  {
    return progressDialog;
  }
  
  public static NonCancelableProgressDialogFragment getFragment(
      final FragmentManager fm)
  {
    return (NonCancelableProgressDialogFragment) fm.findFragmentByTag(TAG);
  }
  
  public void removeFragment(final FragmentManager fm)
  {
    final FragmentTransaction ft = fm.beginTransaction();
    
    ft.remove(this);
    
    ft.commit();
  }
  
  public void show(final FragmentManager fm)
  {
    super.show(fm, TAG);
  }
  
  static class NonCancelableDialog extends ProgressDialog {

    public NonCancelableDialog(final Context context)
    {
      super(context);
    }

    @Override
    public void onBackPressed()
    {
    }

    @Override
    public void hide()
    {
    }

    @Override
    public void cancel()
    {
    }
  }
}