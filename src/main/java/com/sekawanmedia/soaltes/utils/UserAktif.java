package com.sekawanmedia.soaltes.utils;

import com.sekawanmedia.soaltes.model.AppUser;
import lombok.Builder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
public class UserAktif {
    public int getUserId(){
        AppUser user = (AppUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return user.getId();
    }

}
