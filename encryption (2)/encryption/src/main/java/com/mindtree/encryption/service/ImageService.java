package com.mindtree.encryption.service;

import org.springframework.core.io.Resource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public interface ImageService {
    void init();
    public void deleteAll();


    public Resource load(String filename);
    

    public Stream<Path> loadAll();

    ResponseEntity<?> uploadFileMulti(MultipartFile[] images, String passedToken);

    ResponseEntity<Map<String, Object>> deleteImageByName(String imgName, String passedToken);

    ResponseEntity<Map<String, Object>> logout(String passedToken);

    ResponseEntity<Boolean> checkToken(Map<String, String> map);

    ResponseEntity<Map<String, String>> login(Map<String, String> map);

    ResponseEntity<Map<String, Object>> deleteAllImage(String passedToken);
//
//    List<String> getAllFileNames();
}
