package com.gdev.geekacademybackend.services;

import java.io.IOException;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import com.gdev.geekacademybackend.models.File;
import com.gdev.geekacademybackend.repositories.FileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

@Transactional
@Service
public class FileStorageService {

  @Autowired
  private FileRepository fileRepository;

  public File store(MultipartFile fileMP) throws IOException {
    String fileName = StringUtils.cleanPath(fileMP.getOriginalFilename());
    File file = new File(fileName, fileMP.getContentType(), fileMP.getBytes());

    return fileRepository.save(file);
  }

  public File getFile(String id) {
    return fileRepository.findById(id).get();
  }

  public Stream<File> getAllFiles() {
    return fileRepository.findAll().stream();
  }
}