package com.soft.campushelper.post.service;


import com.soft.campushelper.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PostWriterService {

    private final PostRepository postRepository;
}
