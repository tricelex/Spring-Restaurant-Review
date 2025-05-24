package com.chuckz.restaurant.services;

import com.chuckz.restaurant.domain.RestaurantCreateUpdateRequest;
import com.chuckz.restaurant.domain.entities.Restaurant;

public interface RestaurantService {
  Restaurant createRestaurant(RestaurantCreateUpdateRequest request);
}
