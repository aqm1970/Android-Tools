package com.maia_business_solutions.utils;

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URL;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.Gravity;
import android.widget.Toast;

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
  
  public static void displayGetPDFAppDialog(final Context context)
  {
    final AlertDialog.Builder builder = new AlertDialog.Builder(context);
    
    builder.setTitle("No PDF Application Found");
    builder.setMessage("Download one from Android Market?");
    
    builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
    {
      @Override
      public void onClick(DialogInterface dialog, int which) {
        final Intent marketIntent = new Intent(Intent.ACTION_VIEW);
        
        marketIntent.setData(Uri.parse("market://details?id=com.adobe.reader"));
        
        try {
          context.startActivity(marketIntent);
        }
        catch (ActivityNotFoundException e) {
          Log.e(NAME, "No activity to start market intent", e);
          
          final Toast toast = Toast.makeText(context,
              "Unable to start Google Play Market", Toast.LENGTH_LONG);
          
          toast.setGravity(Gravity.CENTER, 0, 0);
          
          toast.show();
        }
      }
    });
    
    builder.setNegativeButton("No", null);
    
    builder.create().show();
  }
  
  public static String getDataFromURL(final String spec) throws IOException
  {
    final StringBuilder builder = new StringBuilder();
    
    BufferedReader reader = null;
    
    try {
      final URL url = new URL(spec);
      
      reader = new BufferedReader(new InputStreamReader(url.openStream()));
      
      String line = null;
      
      while ( (line = reader.readLine()) != null)
        builder.append(line);
    }
    finally {
      close(reader);
    }
    
    return builder.toString();
  }
  
  public static String cap1stChar(String userIdea)
  {
    if (userIdea != null && userIdea.isEmpty() == false)
    {
      final char[] stringArray = userIdea.toCharArray();
      
      stringArray[0] = Character.toUpperCase(stringArray[0]);
      
      userIdea = new String(stringArray);
    }
      
      return userIdea;
  }
}