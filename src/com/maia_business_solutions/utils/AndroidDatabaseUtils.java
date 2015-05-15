package com.maia_business_solutions.utils;

import android.database.Cursor;

public abstract class AndroidDatabaseUtils
{
  public static String getString(final Cursor cursor, final String colName)
  {
    return cursor.getString(getColumnIdxOrThrow(cursor, colName));
  }
  
  public static String getString(final Cursor cursor, final String colName,
      final String defaultValue)
  {
    String retVal = defaultValue;
    
    try {
      int idx = getColumnIdxOrThrow(cursor, colName);
      
      retVal = cursor.getString(idx);
    }
    catch (Exception e) {}
    
    return retVal;
  }
  
  public static int getInt(final Cursor cursor, final String colName)
  {
    return cursor.getInt(getColumnIdxOrThrow(cursor, colName));
  }
  
  public static float getFloat(final Cursor cursor, final String colName)
  {
    return cursor.getFloat(getColumnIdxOrThrow(cursor, colName));
  }
  
  public static double getDouble(final Cursor cursor, final String colName)
  {
    return cursor.getDouble(getColumnIdxOrThrow(cursor, colName));
  }
  
  public static long getLong(final Cursor cursor, final String colName)
  {
    return cursor.getLong(getColumnIdxOrThrow(cursor, colName));
  }

  private static int getColumnIdxOrThrow(final Cursor cursor,
      final String colName)
  {
    return cursor.getColumnIndexOrThrow(colName);
  }
}