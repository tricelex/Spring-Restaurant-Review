package com.chuckz.restaurant.services.impl;

import com.chuckz.restaurant.exceptions.StorageException;
import com.chuckz.restaurant.services.StorageService;
import jakarta.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
@Slf4j
public class FileSystemStorageService implements StorageService {
  @Value("${app.storage.location:uploads}")
  private String storageLocation;

  private Path rootLocation;

  @PostConstruct
  public void init() {
    rootLocation = Paths.get(storageLocation);
    try {
      Files.createDirectories(rootLocation);
    } catch (IOException e) {
      throw new StorageException("Could not initialize storage location", e);
    }
  }

  @Override
  public String store(MultipartFile file, String fileName) {
    try {

      if (file.isEmpty()) {
        throw new StorageException("Cannot store empty file");
      }
      String extension = StringUtils.getFilenameExtension(file.getOriginalFilename());
      String finalFileName = fileName + "." + extension;

      Path destinationFile =
          rootLocation.resolve(Paths.get(finalFileName)).normalize().toAbsolutePath();

      if (!destinationFile.getParent().equals(rootLocation.toAbsolutePath())) {
        throw new StorageException("Cannot store file outside specified directory");
      }

      try (InputStream inputStream = file.getInputStream()) {
        Files.copy(inputStream, destinationFile, StandardCopyOption.REPLACE_EXISTING);
      }

      return finalFileName;
    } catch (IOException e) {
      throw new StorageException("Failed to store file", e);
    }
  }

  @Override
  public Optional<Resource> loadAsResource(String filename) {
    try {
      Path file = rootLocation.resolve(filename);

      Resource resource = new UrlResource(file.toUri());

      if (resource.exists() || resource.isReadable()) {
        return Optional.of(resource);
      } else {
        log.warn("Could not read file: {}", filename);
        return Optional.empty();
      }
    } catch (MalformedURLException e) {
      log.warn("Could not read file: {}", filename, e);
      return Optional.empty();
    }
  }
}
