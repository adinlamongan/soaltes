package com.sekawanmedia.soaltes.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.sekawanmedia.soaltes.validator.DocumentConstraint;
import com.sekawanmedia.soaltes.validator.ImageConstraint;
import com.sekawanmedia.soaltes.validator.VideoConstraint;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@ToString(exclude =  {"document","video", "image"})
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostEditRequestDTO {

    @NotEmpty
    private String nama;
    @NotEmpty
    private String alamat;
    @NotEmpty
    private String kelurahan;
    private String kota;
    private String propinsi;
    private String hobi;
    private String makananFavorit;
    private String minumanFavorit;

    @DocumentConstraint
    private MultipartFile document;
    @VideoConstraint
    private MultipartFile video;
    @ImageConstraint
    private MultipartFile image;

}
