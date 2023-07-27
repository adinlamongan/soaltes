package com.sekawanmedia.soaltes.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Table("posts")
@NoArgsConstructor
public class Posts {
    @Id
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
    private LocalDateTime createdAt=LocalDateTime.now().withNano(0);
}
