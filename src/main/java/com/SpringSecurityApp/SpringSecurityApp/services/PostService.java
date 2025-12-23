package com.SpringSecurityApp.SpringSecurityApp.services;

import com.SpringSecurityApp.SpringSecurityApp.dto.PostDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostService {
    List<PostDTO> getAllPost();
    PostDTO createNewPost(PostDTO inputPost);

    PostDTO getPostById(Long postId);

    PostDTO updatePost(PostDTO inputPost, Long postId);
}
