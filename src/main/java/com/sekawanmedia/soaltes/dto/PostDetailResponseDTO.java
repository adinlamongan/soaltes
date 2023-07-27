package com.sekawanmedia.soaltes.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class PostDetailResponseDTO {
    private int id;
    private int userId;
    private String nama;
    private String alamat;
    private String kelurahan;
    private String kota;
    private String propinsi;
    private String hobi;
    private String makananFavorit;
    private String minumanFavorit;
    private String image;
    private String video;
    private String document;
}
