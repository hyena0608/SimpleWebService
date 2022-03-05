package com.wizard.webservice.web;

import com.wizard.webservice.domain.posts.Posts;
import com.wizard.webservice.domain.posts.PostsRepository;
import com.wizard.webservice.dto.PostsSaveRequestDto;
import com.wizard.webservice.service.PostsService;
import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WebRestController {

    private final PostsService postsService;

    @GetMapping("/hello")
    public String hello() {
        return "HelloWorld";
    }

    @PostMapping("/posts")
    public Long savePosts(@RequestBody PostsSaveRequestDto dto) {
        return postsService.save(dto);
    }

}
