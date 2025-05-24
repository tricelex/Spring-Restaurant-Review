package com.chuckz.restaurant.services;

import com.chuckz.restaurant.domain.entities.Photo;
import java.util.Optional;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {
  Photo uploadPhoto(MultipartFile file);

  Optional<Resource> getPhotoAsResource(String id);
}
