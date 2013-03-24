package com.maia_business_solutions.utils;

import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.content.Context;
import android.util.Log;

final public class Utils {
  private static final String NAME = Utils.class.getSimpleName();
  
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
  
  public static File copyToExternal(final Context context,
      final InputStream inputStream, final String file)
  {
    File retVal = null;
    
    OutputStream outStream = null;
    
    try {
      final File outFile = new File(context.getExternalFilesDir(null), file);
      
      if (outFile.exists() == false) {
        Log.d(NAME, "File (" + outFile.getAbsolutePath() + ") did not exist." +
        		" Copying to getExternalFilesDir(null)");
        
        outStream = new FileOutputStream(outFile);
        final byte[] data = new byte[inputStream.available()];

        inputStream.read(data);
        outStream.write(data);
      }
      
      retVal = outFile;
    }
    catch (Exception e) {
      Log.e(NAME, "Utils.copyToExternal", e);
    }
    finally {
      close(inputStream);
      close(outStream);
    }
    
    return retVal;
  }
}