package com.SpringSecurityApp.SpringSecurityApp.controllers;


import com.SpringSecurityApp.SpringSecurityApp.dto.PostDTO;
import com.SpringSecurityApp.SpringSecurityApp.services.PostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/posts")
public class PostController {
    private final PostService postService;
    @Secured({"ROLE_USER","ROLE_ADMIN"})
    @GetMapping
    public ResponseEntity<List<PostDTO>> getallPost(){
        return ResponseEntity.ok(postService.getAllPost());
    }

    @GetMapping("/{postId}")
//    @PreAuthorize("hasRole('USER',' ADMIN') OR hasAuthority('POST_VIEW')")
    @PreAuthorize("@postSecurity.isOwnerOfPostId(#postId)")
    public PostDTO getPostById(@PathVariable Long postId){
//        System.out.println("under");
        return postService.getPostById(postId);
    }

    @PostMapping
    public ResponseEntity<PostDTO> createNewPost(@RequestBody @Valid PostDTO inputPost){
         PostDTO postDTO= postService.createNewPost(inputPost);
        return new ResponseEntity<>(postDTO, HttpStatus.CREATED);
    }

    @PutMapping("/{postId}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO inputPost,@PathVariable Long postId){
        PostDTO postDTO=postService.updatePost(inputPost,postId);
        return new ResponseEntity<>(postDTO, HttpStatus.OK);
    }



}
