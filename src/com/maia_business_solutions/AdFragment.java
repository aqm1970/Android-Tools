package com.maia_business_solutions;

import android.app.Fragment;
import android.os.Bundle;
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
  private AdView adView;

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState)
  {
    final ViewGroup group = (ViewGroup) inflater.inflate(
        R.layout.ad_frame, container, false);
    
    adView = new AdView(getActivity());
    
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

  @Override
  public void onDestroy()
  {
    if (adView != null)
      adView.destroy();
    
    adView = null;
    
    super.onDestroy();
  }

  @Override
  public void onPause()
  {
    super.onPause();
    
    if (adView != null)
      adView.pause();
  }

  @Override
  public void onResume()
  {
    super.onResume();
    
    if (adView != null)
      adView.resume();
  }
}