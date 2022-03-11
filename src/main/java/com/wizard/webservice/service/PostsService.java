package com.wizard.webservice.service;

import com.wizard.webservice.domain.posts.Posts;
import com.wizard.webservice.domain.posts.PostsRepository;
import com.wizard.webservice.dto.PostsMainResponseDto;
import com.wizard.webservice.dto.PostsSaveRequestDto;
import com.wizard.webservice.web.WebRestController;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto dto) {
        return postsRepository.save(dto.toEntity()).getId();
    }

    public List<PostsMainResponseDto> findAllDesc() {
        return postsRepository.findAllDesc()
                .map(PostsMainResponseDto::new)
                .collect(Collectors.toList());
    }
}
