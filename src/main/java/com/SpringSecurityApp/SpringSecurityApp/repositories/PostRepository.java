package com.SpringSecurityApp.SpringSecurityApp.repositories;

import com.SpringSecurityApp.SpringSecurityApp.entities.PostEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity,Long> {


}
