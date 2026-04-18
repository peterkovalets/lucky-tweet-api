package org.peterkovalets.lucky_tweet_api.common.enums;

public enum StorageBucket {

    AVATARS("avatars"),
    THUMBNAILS("thumbnails");

    private final String bucketName;

    StorageBucket(String bucketName) {
        this.bucketName = bucketName;
    }

    public String getBucketName() {
        return bucketName;
    }
}
