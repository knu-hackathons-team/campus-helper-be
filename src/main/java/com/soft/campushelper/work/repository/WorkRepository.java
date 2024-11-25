package com.soft.campushelper.work.repository;

import com.soft.campushelper.member.Member;
import com.soft.campushelper.post.Post;
import com.soft.campushelper.work.Work;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WorkRepository extends JpaRepository<Work, Long> {

    Optional<Work> findByPost(Post post);

    Page<Work> findAllByWorker(Member worker, Pageable pageable);
}
