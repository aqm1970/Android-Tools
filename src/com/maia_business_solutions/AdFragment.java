package com.maia_business_solutions;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.maia_business_solutions.tools.R;

public class AdFragment extends Fragment
{
  private static final String LOG_TAG = AdFragment.class.getSimpleName();
  
  private AdView adView;

  @Override
  public void onAttach(Activity activity)
  {
    super.onAttach(activity);
    
    if (adView == null) {
      adView = new AdView(activity);
      
      Log.i(LOG_TAG, "Instantiated 'adView'");
    }
    
    Log.i(LOG_TAG, "onAttach");
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState)
  {
    final ViewGroup group = (ViewGroup) inflater.inflate(
        R.layout.ad_frame, container, false);
    
    adView.setAdSize(AdSize.SMART_BANNER);
    adView.setAdUnitId("ca-app-pub-5976313619192225/9481126998");
    
    final Bundle bundle = new Bundle();
    
    bundle.putString("color_bg", "FFFFFF");
    bundle.putString("color_bg_top", "008000");
    bundle.putString("color_border", "FFFFFF");
    bundle.putString("color_link", "000000");
    bundle.putString("color_text", "000000");
    bundle.putString("color_url", "008000");

    final AdRequest adRequest = new AdRequest.Builder()
      .addTestDevice(AdRequest.DEVICE_ID_EMULATOR)
      .addTestDevice("26FCD454891599601BF32AEB7B4F4B5A")
      .addNetworkExtrasBundle(AdMobAdapter.class, bundle)
      .addKeyword("Food")
      .addKeyword("Nutrition")
      .addKeyword("Fitness")
      .addKeyword("Diet")
      .build();
    
    adView.loadAd(adRequest);
    
    group.addView(adView);
    
    return group;
  }

  public void onDestroyActivity()
  {
    if (adView != null) {
      adView.destroy();
      
      Log.i(LOG_TAG, "Destroyed 'adView'");
    }
    
    adView = null;
    
    Log.i(LOG_TAG, "onDestroyActivity");
  }

  public void onPauseActivity()
  {
    if (adView != null) {
      adView.pause();
      
      Log.i(LOG_TAG, "Paused 'adView'");
    }
    
    Log.i(LOG_TAG, "onPauseActivity");
  }

  public void onResumeActivity()
  {
    if (adView != null) {
      adView.resume();
      
      Log.i(LOG_TAG, "Resumed 'adView'");
    }
    
    Log.i(LOG_TAG, "onResumeActivity");
  }
}