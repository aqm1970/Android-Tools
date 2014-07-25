package com.maia_business_solutions;

import android.app.ProgressDialog;
import android.content.Context;

public class NonCancelableDialog extends ProgressDialog {

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