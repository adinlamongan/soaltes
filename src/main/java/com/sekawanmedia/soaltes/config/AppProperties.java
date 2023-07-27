package com.sekawanmedia.soaltes.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
//@ConfigurationProperties(prefix = "app.baseurl")
public class AppProperties {
    @Value("${app.baseurl.storage}")
    private String BASE_URL_STORAGE;

    private String documentStorage = "documents/";
    private String imageStorage = "images/";
    private String videoStorage = "videos/";
    private String imgProfileDefault = "img/default.png";
    private String noImage = "img/noimage.png";

    public String getDocumentStorage() {
        return BASE_URL_STORAGE + documentStorage;
    }
    public String getImageStorage() {
        return BASE_URL_STORAGE + imageStorage;
    }
    public String getVideoStorage() {
        return BASE_URL_STORAGE + videoStorage;
    }
    public String getImgProfileDefault() {
        return BASE_URL_STORAGE + imgProfileDefault;
    }
    public String getNoImage() {
        return BASE_URL_STORAGE + noImage;
    }

    
}
