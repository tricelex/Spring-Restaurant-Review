package com.chuckz.restaurant.domain;

import com.chuckz.restaurant.domain.entities.Address;
import com.chuckz.restaurant.domain.entities.OperatingHours;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantCreateUpdateRequest {
  private String name;
  private String cuisineType;
  private String contactInformation;
  private Address address;
  private OperatingHours operatingHours;
  private List<String> photoIds;
}
