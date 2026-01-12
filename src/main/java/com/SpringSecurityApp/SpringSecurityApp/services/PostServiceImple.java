package com.SpringSecurityApp.SpringSecurityApp.services;

import com.SpringSecurityApp.SpringSecurityApp.dto.PostDTO;
import com.SpringSecurityApp.SpringSecurityApp.entities.PostEntity;
import com.SpringSecurityApp.SpringSecurityApp.entities.User;
import com.SpringSecurityApp.SpringSecurityApp.exception.ResourceNotFoundException;
import com.SpringSecurityApp.SpringSecurityApp.repositories.PostRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class PostServiceImple implements PostService{

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PostDTO> getAllPost() {
        return postRepository
                .findAll()
                .stream()
                .map(postEntity -> modelMapper.map(postEntity,PostDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PostDTO createNewPost(PostDTO inputPost) {
        PostEntity postEntity= modelMapper.map(inputPost,PostEntity.class);
        return modelMapper.map(postRepository.save(postEntity),PostDTO.class);
//        return postDTO;
    }

    @Override
    public PostDTO getPostById(Long postId) {
        User user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        log.info("User {}",user);
        PostEntity postEntity=postRepository
                .findById(postId)
                .orElseThrow(()->new ResourceNotFoundException("Post Not Found with this ID:"+postId));
        return modelMapper.map(postEntity,PostDTO.class);
    }

    @Override
    public PostDTO updatePost(PostDTO inputPost, Long postId) {
        PostEntity olderPost= postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post Not Found with this "+postId));
        inputPost.setId(postId);
        modelMapper.map(inputPost,olderPost);
        PostEntity postEntity=postRepository.save(olderPost);
        return modelMapper.map(postEntity,PostDTO.class);

    }
}
