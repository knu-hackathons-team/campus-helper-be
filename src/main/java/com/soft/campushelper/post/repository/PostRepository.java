package com.soft.campushelper.post.repository;

import com.soft.campushelper.member.Member;
import com.soft.campushelper.post.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long> {

    Page<Post> findAllByWriter(Member member, Pageable pageable);

}
