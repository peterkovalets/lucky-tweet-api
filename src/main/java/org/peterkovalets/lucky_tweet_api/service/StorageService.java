package org.peterkovalets.lucky_tweet_api.service;

import org.peterkovalets.lucky_tweet_api.common.enums.StorageBucket;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    String store(MultipartFile file, StorageBucket bucket);
}
