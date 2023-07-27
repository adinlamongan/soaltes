package com.sekawanmedia.soaltes.validator;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class VideoConstraintValidator implements ConstraintValidator<VideoConstraint, MultipartFile> {


    @Override
    public void initialize(VideoConstraint constraintAnnotation) {
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(MultipartFile multipartFile, ConstraintValidatorContext context) {
        boolean result = true;

        String contentType = multipartFile.getContentType();
        if (!isSupportedContentType(contentType)) {
            result = false;
        }

        return result;
    }


    private boolean isSupportedContentType(String contentType) {
        return contentType.equals("video/mp4");
    }
}
