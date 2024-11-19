package com.soft.campushelper.post.repository;

import com.soft.campushelper.post.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {
}
