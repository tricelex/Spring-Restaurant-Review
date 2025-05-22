package com.chuckz.restaurant.mappers;

import com.chuckz.restaurant.domain.dtos.PhotoDto;
import com.chuckz.restaurant.domain.entities.Photo;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PhotoMapper {
  PhotoDto toDto(Photo photo);
}
