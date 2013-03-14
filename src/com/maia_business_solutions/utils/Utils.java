package com.maia_business_solutions.utils;

import java.io.Closeable;
import java.io.IOException;

import android.util.Log;

public class Utils {
  public static void close(Closeable closeable)
  {
    if (closeable != null) {
      try {
        closeable.close();
      }
      catch (IOException e) {
        Log.w(Utils.class.getSimpleName(), e);
      }
    }
  }
}
