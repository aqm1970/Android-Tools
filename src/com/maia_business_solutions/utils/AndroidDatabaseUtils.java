package com.maia_business_solutions.utils;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public abstract class AndroidDatabaseUtils
{
  private static final String LOG_TAG = AndroidDatabaseUtils.class.getSimpleName();
  
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
  
  public static void closeDB(final SQLiteDatabase db)
  {
    try {
      if (db != null)
        db.close();
    }
    catch (Exception e) {
      Log.e(LOG_TAG, "Error closing database", e);
    }
  }
  
  public static void closeCursor(final Cursor cursor)
  {
    try {
      if (cursor != null)
        cursor.close();
    }
    catch (Exception e) {
      Log.e(LOG_TAG, "Error closing cursor", e);
    }
  }
  
  public static void closeStatement(final SQLiteStatement statement)
  {
    try {
      if (statement != null)
        statement.close();
    }
    catch (Exception e) {
      Log.e(LOG_TAG, "Error closing statement", e);
    }
  }
  
  public static SQLiteStatement compileInsertStatement(final SQLiteDatabase db,
      final String tableName, final String ... strings) throws SQLException
  {
    final StringBuilder sql = new StringBuilder();
    sql.append("INSERT INTO ").append(tableName).append(" (");
    
    final StringBuilder values = new StringBuilder();
    values.append(" VALUES (");
    
    boolean firstTime = true;
    
    for (final String column : strings) {
      if (firstTime) {
        firstTime = false;
      }
      else {
        sql.append(", ");
        values.append(", ");
      }
      
      sql.append(column);
      values.append("?");
    }
    
    sql.append(") ");
    values.append(") ");
    
    return db.compileStatement(sql.append(values.toString()).toString());
  }
  
  public static SQLiteStatement compileUpdateStatement(final SQLiteDatabase db,
      final String tableName, final String where, final String ... updateColumns)
  {
    final StringBuilder builder = new StringBuilder();
    
    builder.append("UPDATE ")
      .append(tableName)
      .append(" SET ");
    
    for (int i = 0; i < updateColumns.length; ++i) {
      if (i != 0)
        builder.append(", ");
      
      builder.append(updateColumns[i])
        .append(" = ?");
    }
    
    builder.append(" WHERE ")
      .append(where)
      .append(" = ?");
    
    return db.compileStatement(builder.toString());
  }

  private static int getColumnIdxOrThrow(final Cursor cursor,
      final String colName)
  {
    return cursor.getColumnIndexOrThrow(colName);
  }
}