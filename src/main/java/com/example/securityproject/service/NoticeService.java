package com.example.securityproject.service;

import com.example.securityproject.domain.notice.Notice;
import com.example.securityproject.domain.notice.NoticeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
public class NoticeService {

    private final NoticeRepository noticeRepository;

    @Transactional(readOnly = true)
    public List<Notice> findAll(){
        return noticeRepository.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    public Notice saveNotice(String title, String content){
        Notice notice = Notice.builder()
                .title(title)
                .content(content)
                .build();
        return noticeRepository.save(notice);
    }

    public void deleteNotice(Long id){
        noticeRepository.findById(id).ifPresent(noticeRepository::delete);
    }
}
