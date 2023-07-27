package com.sekawanmedia.soaltes.service.impl;

import com.sekawanmedia.soaltes.config.AppProperties;
import com.sekawanmedia.soaltes.dto.PostCreateRequestDTO;
import com.sekawanmedia.soaltes.dto.PostDetailResponseDTO;
import com.sekawanmedia.soaltes.dto.PostEditRequestDTO;
import com.sekawanmedia.soaltes.exception.ResourceNotFoundException;
import com.sekawanmedia.soaltes.model.Posts;
import com.sekawanmedia.soaltes.repository.PostRepo;
import com.sekawanmedia.soaltes.service.PostService;
import com.sekawanmedia.soaltes.utils.FileUploadUtil;
import com.sekawanmedia.soaltes.utils.UserAktif;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PostServiceImpl implements PostService {

    private PostRepo postRepo;
    private UserAktif userAktif;
    private AppProperties appProperties;


    @Override
    public void create(PostCreateRequestDTO dto) throws IOException {
        String fileNameImage = StringUtils.cleanPath(Objects.requireNonNull(dto.getImage().getOriginalFilename()));
        String fileNameDocument = StringUtils.cleanPath(Objects.requireNonNull(dto.getDocument().getOriginalFilename()));
        String fileNameVideo = StringUtils.cleanPath(Objects.requireNonNull(dto.getVideo().getOriginalFilename()));

        Posts post = new Posts();
        post.setUserId(userAktif.getUserId());
        post.setNama(dto.getNama());
        post.setAlamat(dto.getAlamat());
        post.setKelurahan(dto.getKelurahan());
        post.setKota(dto.getKota());
        post.setPropinsi(dto.getPropinsi());
        post.setHobi(dto.getHobi());
        post.setMakananFavorit(dto.getMakananFavorit());
        post.setMinumanFavorit(dto.getMinumanFavorit());
        post.setImage(UUID.randomUUID().toString() + getFileExtension(fileNameImage));
        post.setDocument(UUID.randomUUID().toString() + getFileExtension(fileNameDocument));
        post.setVideo(UUID.randomUUID().toString() + getFileExtension(fileNameVideo));
        postRepo.save(post);
        String uploadDir;
        if (dto.getVideo() != null) {
            uploadDir = "storage/videos/";
            FileUploadUtil.saveFile(uploadDir, post.getVideo(), dto.getVideo());
        }
        if (dto.getImage() != null) {
            uploadDir = "storage/images/";
            FileUploadUtil.saveFile(uploadDir, post.getImage(), dto.getImage());
        }
        if (dto.getDocument() != null) {
            uploadDir = "storage/documents/";
            FileUploadUtil.saveFile(uploadDir, post.getDocument(), dto.getDocument());
        }

    }

    @Override
    public PostDetailResponseDTO findById(int id) {
        Posts posts = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("post not found"));
        PostDetailResponseDTO dto = new PostDetailResponseDTO();
        dto.setId(posts.getId());
        dto.setUserId(posts.getUserId());
        dto.setNama(posts.getNama());
        dto.setAlamat(posts.getAlamat());
        dto.setKelurahan(posts.getKelurahan());
        dto.setKota(posts.getKota());
        dto.setPropinsi(posts.getPropinsi());
        dto.setHobi(posts.getHobi());
        dto.setMakananFavorit(posts.getMakananFavorit());
        dto.setMinumanFavorit(posts.getMinumanFavorit());
        dto.setImage(appProperties.getImageStorage() + posts.getImage());
        dto.setVideo(appProperties.getVideoStorage() + posts.getVideo());
        dto.setDocument(appProperties.getDocumentStorage() + posts.getDocument());
        return dto;
    }

    @Override
    public void edit(PostEditRequestDTO dto, int id) throws IOException {
        String fileNameImage = StringUtils.cleanPath(Objects.requireNonNull(dto.getImage().getOriginalFilename()));
        String fileNameDocument = StringUtils.cleanPath(Objects.requireNonNull(dto.getDocument().getOriginalFilename()));
        String fileNameVideo = StringUtils.cleanPath(Objects.requireNonNull(dto.getVideo().getOriginalFilename()));

        Posts post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("post not found"));
        String oldImage = post.getImage();
        String oldDocument = post.getDocument();
        String oldVideo = post.getVideo();
        post.setUserId(userAktif.getUserId());
        post.setNama(dto.getNama());
        post.setAlamat(dto.getAlamat());
        post.setKelurahan(dto.getKelurahan());
        post.setKota(dto.getKota());
        post.setPropinsi(dto.getPropinsi());
        post.setHobi(dto.getHobi());
        post.setMakananFavorit(dto.getMakananFavorit());
        post.setMinumanFavorit(dto.getMinumanFavorit());
        post.setImage(UUID.randomUUID().toString() + getFileExtension(fileNameImage));
        post.setDocument(UUID.randomUUID().toString() + getFileExtension(fileNameDocument));
        post.setVideo(UUID.randomUUID().toString() + getFileExtension(fileNameVideo));
        postRepo.save(post);
        String uploadDir;
        if (dto.getVideo() != null) {
            uploadDir = "storage/videos/";
            FileUploadUtil.deletFile(uploadDir + oldVideo);
            FileUploadUtil.saveFile(uploadDir, post.getVideo(), dto.getVideo());
        }
        if (dto.getImage() != null) {
            uploadDir = "storage/images/";
            FileUploadUtil.deletFile(uploadDir + oldImage);
            FileUploadUtil.saveFile(uploadDir, post.getImage(), dto.getImage());
        }
        if (dto.getDocument() != null) {
            uploadDir = "storage/documents/";
            FileUploadUtil.deletFile(uploadDir + oldDocument);
            FileUploadUtil.saveFile(uploadDir, post.getDocument(), dto.getDocument());
        }
    }

    @Override
    public void delete(int id) {
        Posts post = postRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("post not found"));
        String oldImage = post.getImage();
        String oldDocument = post.getDocument();
        String oldVideo = post.getVideo();
        postRepo.delete(post);
        String uploadDir;
        uploadDir = "storage/videos/";
        FileUploadUtil.deletFile(uploadDir + oldVideo);

        uploadDir = "storage/images/";
        FileUploadUtil.deletFile(uploadDir + oldImage);

        uploadDir = "storage/documents/";
        FileUploadUtil.deletFile(uploadDir + oldDocument);
    }

    private String getFileExtension(String filename) {
        int i = filename.lastIndexOf('.');
        return "." + filename.substring(i + 1);
    }
}
