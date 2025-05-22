package com.chuckz.restaurant.services;

import com.chuckz.restaurant.domain.entities.Photo;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface PhotoService {
  Photo uploadPhoto(MultipartFile file, String fileName);

  Optional<Resource> getPhotoAsResource(String id);
}
