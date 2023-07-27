package com.sekawanmedia.soaltes.controller;

import com.sekawanmedia.soaltes.dto.PostCreateRequestDTO;
import com.sekawanmedia.soaltes.dto.PostDetailResponseDTO;
import com.sekawanmedia.soaltes.dto.PostEditRequestDTO;
import com.sekawanmedia.soaltes.service.PostService;
import com.sekawanmedia.soaltes.validator.ImageConstraint;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/api")
public class PostController {

    private final PostService postService;

    @PostMapping("/posts")
    public ResponseEntity<Void> addPosts(@Valid @ModelAttribute  PostCreateRequestDTO dto) throws IOException {
        postService.create(dto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Object> getPostById(@PathVariable("id") int id){
        PostDetailResponseDTO responseDTO = postService.findById(id);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Object> editPost(@PathVariable("id") int id, @Valid @ModelAttribute PostEditRequestDTO dto) throws IOException {
        postService.edit(dto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/posts/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable("id") int id)  {
        //postService.edit(dto, id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
