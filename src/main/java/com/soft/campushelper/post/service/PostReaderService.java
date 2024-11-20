package com.soft.campushelper.post.service;

import com.soft.campushelper.Member.Member;
import com.soft.campushelper.post.Post;
import com.soft.campushelper.post.repository.PostRepository;
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
    public Page<Post> getPostList(Member member, Pageable pageable) {

        return postRepository.findAllByWriter(member, pageable);

    }
}
