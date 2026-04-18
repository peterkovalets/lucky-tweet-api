package org.peterkovalets.lucky_tweet_api.rest;

import org.peterkovalets.lucky_tweet_api.common.enums.StorageBucket;
import org.peterkovalets.lucky_tweet_api.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/files")
public class FileUploadController {

    private final StorageService storageService;

    @Autowired
    public FileUploadController(StorageService storageService) {
        this.storageService = storageService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> handleFileUpload(@RequestParam("file") MultipartFile file,
                                                   @RequestParam("bucket") StorageBucket bucket) {
        String fileName = storageService.store(file, bucket);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(String.format("File %s uploaded successfully.", fileName));
    }
}
