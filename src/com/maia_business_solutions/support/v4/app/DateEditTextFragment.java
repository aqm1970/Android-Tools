package com.maia_business_solutions.support.v4.app;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.DatePickerDialog.OnDateSetListener;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;

import com.maia_business_solutions.tools.R;

public class DateEditTextFragment extends Fragment
{
  private static final String DATE_TEXT = DateEditTextFragment.class.getName() + "date-text";

  private final static DateFormat DEFAULT_DATE_FORMAT =
      new SimpleDateFormat("MM/dd/yyyy", Locale.US);
  
  private EditText dateEditText;
  private View openDatePicker;
  
  private DateFormat dateFormat = null;
  
  static {
    DEFAULT_DATE_FORMAT.setLenient(false);
  }

  public DateEditTextFragment()
  {
  }
  
  public void showDatePickerButton(final boolean show)
  {
    if (show)
      openDatePicker.setVisibility(View.VISIBLE);
    else
      openDatePicker.setVisibility(View.GONE);
  }
  
  public void setDateFormat(final DateFormat format)
  {
    dateFormat = format;
  }
  
  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState)
  {
    return inflater.inflate(R.layout.date_edit_view, container, false);
  }

  @Override
  public void onViewCreated(View view, Bundle savedInstanceState)
  {
    dateEditText = (EditText) view.findViewById(R.id.date_edit_text);
    
    openDatePicker = view.findViewById(R.id.show_date_picker);
    
    openDatePicker.setOnClickListener(new OnClickListener()
    {
      @Override
      public void onClick(View v)
      {
        final DatePickerDialogFragment pickerDialogFragment =
            new DatePickerDialogFragment();
        
        pickerDialogFragment.addOnDateSetListener(new OnDateSetListener()
        {
          @Override
          public void onDateSet(DatePicker view, int year, int monthOfYear,
              int dayOfMonth)
          {
            final Date date = pickerDialogFragment.getDate();
            
            dateEditText.setText(formatDate(date));
          }
        });
      }
    });
  }

  @Override
  public void onViewStateRestored(Bundle savedInstanceState)
  {
    super.onViewStateRestored(savedInstanceState);
    
    String value = null;
    
    if (savedInstanceState != null)
      value = savedInstanceState.getString(DATE_TEXT);
    
    if (value == null)
      value = formatDate(new Date());
    
    dateEditText.setText(value);
  }

  @Override
  public void onSaveInstanceState(Bundle outState)
  {
    super.onSaveInstanceState(outState);
    
    outState.putString(DATE_TEXT, dateEditText.getText().toString());
  }

  private String formatDate(final Date date)
  {
    final DateFormat localDateFormat;
    
    if (dateFormat == null)
      localDateFormat = DEFAULT_DATE_FORMAT;
    else
      localDateFormat = dateFormat;
    
    return localDateFormat.format(date);
  }
}