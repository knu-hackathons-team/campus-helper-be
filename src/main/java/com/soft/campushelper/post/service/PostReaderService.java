package com.soft.campushelper.post.service;

import com.soft.campushelper.Member.Member;
import com.soft.campushelper.post.Post;
import com.soft.campushelper.post.repository.PostRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostReaderService {
    private final PostRepository postRepository;

    @Transactional(readOnly = true)
    public Post getPostById(Long postId){
        return postRepository.findById(postId).orElseThrow(
                () -> new EntityNotFoundException("해당 게시물이 존재하지 않습니다.")
        );
    }

    @Transactional(readOnly = true)
    public Page<Post> getPostList(Pageable pageable) {
        return postRepository.findAll(pageable);
    }
}
