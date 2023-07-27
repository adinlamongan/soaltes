package com.sekawanmedia.soaltes.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.sekawanmedia.soaltes.config.AppProperties;
import com.sekawanmedia.soaltes.dto.RegisterRequestDTO;
import com.sekawanmedia.soaltes.model.AppUser;
import com.sekawanmedia.soaltes.repository.AppUserRepo;
import com.sekawanmedia.soaltes.repository.PostRepo;
import com.sekawanmedia.soaltes.service.AppUserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AppUserServiceImpl implements AppUserService {

    private AppUserRepo appUserRepo;
    private PasswordEncoder passwordEncoder;
    private PostRepo postsRepo;
    private AppProperties appProperties;

    @Override
    public void create(RegisterRequestDTO dto) {
        AppUser appUser = new AppUser();
        appUser.setEmail(dto.getEmail());
        appUser.setUsername(dto.getUsername());
        appUser.setPassword(passwordEncoder.encode(dto.getPassword()));
        appUserRepo.save(appUser);
    }
}
