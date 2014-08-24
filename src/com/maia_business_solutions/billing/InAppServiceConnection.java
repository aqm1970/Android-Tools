package com.maia_business_solutions.billing;

import java.util.ArrayList;
import java.util.Arrays;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;

import com.android.vending.billing.IInAppBillingService;

public class InAppServiceConnection implements ServiceConnection
{
  private static final String IN_APP_PURCHASE_TYPE = "inapp";

  private static final String ITEM_ID_LIST = "ITEM_ID_LIST";

  private static final int API_VERSION = 3;

  private static final String ACTION = "com.android.vending.billing.InAppBillingService.BIND";

  private static final String LOG_TAG = InAppServiceConnection.class.getName();
  
  private IInAppBillingService service;
  
  public IInAppBillingService getService()
  {
    return service;
  }
  
  public void bindService(final Activity activity)
  {
    activity.bindService(new Intent(ACTION), this, Context.BIND_AUTO_CREATE);
  }
  
  public Bundle getSkuDetails(final String packageName,
      final ArrayList<String> skuList)
  {
    final Bundle skusBundle = new Bundle();
    
    skusBundle.putStringArrayList(ITEM_ID_LIST, skuList);
    
    Bundle retVal = new Bundle();
    
    try {
      retVal = service.getSkuDetails(
          API_VERSION, packageName, IN_APP_PURCHASE_TYPE, skusBundle);
    }
    catch (Exception e) {
      Log.e(LOG_TAG, "Unable to get SKU Details", e);
    }
    
    return retVal;
  }
  
  public Bundle getSkuDetails(final String packageName, final String ... skus)
  {
    return getSkuDetails(packageName,
        new ArrayList<String>(Arrays.asList(skus)));
  }

  @Override
  public void onServiceConnected(ComponentName name, IBinder service)
  {
    Log.i(LOG_TAG, "Established service connection to component: " + name);
    
    this.service = IInAppBillingService.Stub.asInterface(service); 
  }

  @Override
  public void onServiceDisconnected(ComponentName name)
  {
    service = null;
  }
}