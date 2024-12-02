package com.soft.campushelper.work.service;

import com.soft.campushelper.member.Member;
import com.soft.campushelper.post.Post;
import com.soft.campushelper.work.Work;
import com.soft.campushelper.work.repository.WorkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class WorkReaderService {

    private final WorkRepository workRepository;

    @Transactional(readOnly = true)
    public Work getWorkByPost(Post post) {
        return workRepository.findByPost(post).orElseThrow(
                () -> new IllegalStateException("진행 중인 작업이 없습니다.")
        );
    }

    public Page<Work> findAllByWorker(Member worker, Pageable pageable) {
        return workRepository.findAllByWorker(worker, pageable);
    }
}
