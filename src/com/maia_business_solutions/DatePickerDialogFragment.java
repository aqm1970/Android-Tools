package com.maia_business_solutions;

import java.util.Calendar;
import java.util.Date;
import java.util.Vector;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.CalendarView;
import android.widget.DatePicker;

public class DatePickerDialogFragment extends DialogFragment implements OnDateSetListener
{
  private static final String TIME_IN_MILLIS = "time-in-millis";
  
  private final Calendar calendar = Calendar.getInstance();
  
  private final Vector<OnDateSetListener> listeners =
      new Vector<OnDateSetListener>();

  public DatePickerDialogFragment()
  {
  }
  
  public DatePickerDialogFragment(final Date date)
  {
    calendar.setTime(date);
  }
  
  public Date getDate()
  {
    return calendar.getTime();
  }

  @Override
  public void onDateSet(DatePicker view, int year, int monthOfYear,
      int dayOfMonth)
  {
    calendar.set(Calendar.YEAR, year);
    calendar.set(Calendar.MONTH, monthOfYear);
    calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
    
    for (final OnDateSetListener l : listeners)
      l.onDateSet(view, year, monthOfYear, dayOfMonth);
  }
  
  public void addOnDateSetListener(final OnDateSetListener listener)
  {
    listeners.add(listener);
  }
  
  public void removeOnDateSetListener(final OnDateSetListener listener)
  {
    listeners.remove(listener);
  }
  
  @Override
  public Dialog onCreateDialog(Bundle savedInstanceState)
  {
    final DatePickerDialog dialog = new DatePickerDialog(getActivity(), this,
        0, 0, 0);
    
    final DatePicker datePicker = dialog.getDatePicker();
    
    datePicker.setCalendarViewShown(true);
    datePicker.setSpinnersShown(false);
    
    final CalendarView calendarView = datePicker.getCalendarView();
    
    calendarView.setShowWeekNumber(false);
    calendarView.setShownWeekCount(3);
    calendarView.setDate(calendar.getTimeInMillis(), false, true);
    
    return dialog;
  }
  
  @Override
  public void onSaveInstanceState(Bundle outState)
  {
    super.onSaveInstanceState(outState);
    
    outState.putLong(TIME_IN_MILLIS, calendar.getTimeInMillis());
  }

  @Override
  public void onViewStateRestored(Bundle savedInstanceState)
  {
    super.onViewStateRestored(savedInstanceState);
    
    if (savedInstanceState != null) {
      final long timeInMillies = savedInstanceState.getLong(TIME_IN_MILLIS,
          new Date().getTime());
      
      calendar.setTimeInMillis(timeInMillies);
    }
  }
}