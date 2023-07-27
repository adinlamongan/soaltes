package com.sekawanmedia.soaltes.repository;


import com.sekawanmedia.soaltes.model.Posts;
import org.springframework.data.repository.CrudRepository;

public interface PostRepo extends CrudRepository<Posts, Integer> {

}
