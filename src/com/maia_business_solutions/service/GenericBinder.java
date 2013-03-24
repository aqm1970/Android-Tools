package com.maia_business_solutions.service;

import android.app.Service;

public interface GenericBinder<S extends Service> {
  public S getService();
}
