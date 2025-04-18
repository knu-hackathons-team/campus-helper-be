package com.soft.campushelper.post.service;

import com.soft.campushelper.global.constants.MessageConstants;
import com.soft.campushelper.member.Member;
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
    public Post getPostById(Long postId) {
        return postRepository.findById(postId).orElseThrow(
                () -> new EntityNotFoundException(MessageConstants.POST_NOT_FOUND)
        );
    }

    @Transactional(readOnly = true)
    public Page<Post> getPostList(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<Post> getPostListByWriter(Member member, Pageable pageable) {
        return postRepository.findAllByWriter(member, pageable);
    }
}
