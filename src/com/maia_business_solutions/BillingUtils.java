package com.maia_business_solutions;

import java.util.List;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.maia_business_solutions.billing.IabHelper;
import com.maia_business_solutions.billing.IabHelper.OnConsumeFinishedListener;
import com.maia_business_solutions.billing.IabHelper.OnIabPurchaseFinishedListener;
import com.maia_business_solutions.billing.IabHelper.OnIabSetupFinishedListener;
import com.maia_business_solutions.billing.IabHelper.QueryInventoryFinishedListener;
import com.maia_business_solutions.billing.Purchase;

public final class BillingUtils
{
  private static final String LOG_TAG = BillingUtils.class.getSimpleName();

  private final IabHelper iabHelper;

  public BillingUtils(final Context ctx, final String base64PublicKey)
  {
    iabHelper = new IabHelper(ctx, base64PublicKey);
    
    iabHelper.enableDebugLogging(true, LOG_TAG);
  }
  
  public void setup(final OnIabSetupFinishedListener listener)
  {
    iabHelper.startSetup(listener);
  }
  
  public void dispose()
  {
    iabHelper.dispose();
  }
  
  public boolean subscriptionsSupported()
  {
    return iabHelper.subscriptionsSupported();
  }
  
  public void launchPurchaseFlow(final Activity act, final String sku,
      final int requestCode, final OnIabPurchaseFinishedListener listener)
  {
    iabHelper.launchPurchaseFlow(act, sku, requestCode, listener);
  }
  
  public void launchPurchaseFlowWithPayload(final Activity act, final String sku,
      final int requestCode, final OnIabPurchaseFinishedListener listener,
      final String payload)
  {
    iabHelper.launchPurchaseFlow(act, sku, requestCode, listener, payload);
  }
  
  public void launchSubscriptionPurchaseFlow(final Activity act,
      final String sku, final int requestCode, final OnIabPurchaseFinishedListener listener)
  {
    iabHelper.launchSubscriptionPurchaseFlow(act, sku, requestCode, listener);
  }
  
  public void launchSubscriptionPurchaseFlowWithPayload(final Activity act,
      final String sku, final int requestCode,
      final OnIabPurchaseFinishedListener listener, final String extraData)
  {
    iabHelper.launchSubscriptionPurchaseFlow(act, sku, requestCode, listener, extraData);
  }
  
  public void queryInventoryAsync(final boolean querySkuDetails,
      final List<String> moreSkus,
      final QueryInventoryFinishedListener listener)
  {
    iabHelper.queryInventoryAsync(querySkuDetails, moreSkus, listener);
  }
  
  public void queryInventoryAsync(final QueryInventoryFinishedListener listener)
  {
    iabHelper.queryInventoryAsync(listener);
  }
  
  public void consumeAsync(final Purchase purchase,
      final OnConsumeFinishedListener listener)
  {
    iabHelper.consumeAsync(purchase, listener);
  }
  
  public boolean onActivityResult(final int requestCode, final int resultCode,
      final Intent data)
  {
    return iabHelper.handleActivityResult(requestCode, resultCode, data);
  }
  
  public static String getResponseDesc(int responseCode)
  {
    return IabHelper.getResponseDesc(responseCode);
  }
  
  public static void displayToast(final Context context, final int responseCode)
  {
    final StringBuilder builder = new StringBuilder();
    
    switch (responseCode) {
      case 0 :
        builder.append("Purchase successful");
        break;
      case 1 :
        builder.append("User canceled");
        break;
      case 2 :
        builder.append("Network connection is down");
        break;
      case 4 :
        builder.append("Product is not available for purchase");
        break;
      case 7 : 
        builder.append("Item is already owned");
        break;
      case 8 :
        builder.append("Failure to consume since item is not owned");
        break;
      default :
        builder.append("Error making In-app purcahse");
    }
    
    Toast.makeText(context, builder.toString(), Toast.LENGTH_LONG).show();
  }
}