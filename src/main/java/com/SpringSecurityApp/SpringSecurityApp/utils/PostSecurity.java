package com.SpringSecurityApp.SpringSecurityApp.utils;

import com.SpringSecurityApp.SpringSecurityApp.dto.PostDTO;
import com.SpringSecurityApp.SpringSecurityApp.entities.PostEntity;
import com.SpringSecurityApp.SpringSecurityApp.entities.User;
import com.SpringSecurityApp.SpringSecurityApp.services.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class PostSecurity {

    private final PostService postService;

   public boolean isOwnerOfPostId(Long postId){
        User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        PostDTO post=postService.getPostById(postId);

        return post.getAuthor().getId().equals(user.getId());

    }
}
