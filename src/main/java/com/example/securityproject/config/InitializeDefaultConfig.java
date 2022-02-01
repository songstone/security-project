package com.example.securityproject.config;

import com.example.securityproject.service.NoteService;
import com.example.securityproject.service.NoticeService;
import com.example.securityproject.domain.user.User;
import com.example.securityproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;


//h2용 더미 데이터 설정
@Configuration
@RequiredArgsConstructor
@Profile(value = "!test") //test 제외
public class InitializeDefaultConfig {

    private final UserService userService;
    private final NoteService noteService;
    private final NoticeService noticeService;


    @Bean
    public void initializeDefaultUser(){
        User user = userService.signUp("user", "user");

        noteService.saveNote(user,"제목입니다.","내용입니다.");
    }

    @Bean
    public void initializeDefaultAdmin(){
        userService.signUpAdmin("admin","admin");
        noticeService.saveNotice("환영합니다.","환영합니다 여러분");
        noticeService.saveNotice("노트 작성 방법 공지", "1. 회원가입\n2. 로그인\n3. 노트 작성\n4. 저장\n* 본인 외에는 게시글을 볼 수 없습니다.");
    }
}
