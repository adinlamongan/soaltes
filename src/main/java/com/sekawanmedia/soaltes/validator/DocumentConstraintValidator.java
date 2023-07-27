package com.sekawanmedia.soaltes.validator;

import org.springframework.web.multipart.MultipartFile;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class DocumentConstraintValidator implements ConstraintValidator<DocumentConstraint, MultipartFile> {


    @Override
    public void initialize(DocumentConstraint constraintAnnotation) {
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
        return contentType.equals("application/pdf")
                || contentType.equals("application/txt")
                || contentType.equals("application/docx");
    }
}
