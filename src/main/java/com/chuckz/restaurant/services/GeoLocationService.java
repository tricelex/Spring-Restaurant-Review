package com.chuckz.restaurant.services;

import com.chuckz.restaurant.domain.GeoLocation;
import com.chuckz.restaurant.domain.entities.Address;

public interface GeoLocationService {
  GeoLocation geoLocate(Address address);
}
