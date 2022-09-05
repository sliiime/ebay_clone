package com.skaypal.ebay_clone.property;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "image")
@Component
public class ImageStorageProperty {

    private String uploadDirectory;

    public String getUploadDirectory(){return this.uploadDirectory;}

    public void setUploadDirectory(String uploadDirectory){this.uploadDirectory = uploadDirectory;}
}
