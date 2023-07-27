package com.sekawanmedia.soaltes.service;

import com.sekawanmedia.soaltes.dto.PostCreateRequestDTO;
import com.sekawanmedia.soaltes.dto.PostDetailResponseDTO;
import com.sekawanmedia.soaltes.dto.PostEditRequestDTO;

import java.io.IOException;

public interface PostService {
    void create(PostCreateRequestDTO dto) throws IOException;
    PostDetailResponseDTO findById(int id);

    void edit(PostEditRequestDTO dto, int id) throws IOException;
    void delete(int id);

}
