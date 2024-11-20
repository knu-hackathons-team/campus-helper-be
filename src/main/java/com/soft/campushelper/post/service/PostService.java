package com.soft.campushelper.post.service;

import com.soft.campushelper.Member.Member;
import com.soft.campushelper.Member.service.MemberReaderService;
import com.soft.campushelper.post.Post;
import com.soft.campushelper.post.controller.dto.PostRequest;
import com.soft.campushelper.post.controller.dto.PostResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final MemberReaderService memberReaderService;
    private final PostReaderService postReaderService;
    private final PostWriterService postWriterService;

    @Transactional
    public void addPost(Long memberId, PostRequest.Add request){
        Member member = memberReaderService.getMemberById(memberId);

        Post post = request.toEntity();

        postWriterService.save(post);
    }

}
