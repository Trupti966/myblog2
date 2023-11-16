package com.myblog2.service;

import com.myblog2.payload.PostDto;

public interface PostService {

    PostDto savePost(PostDto postDto);


    void deleteById(long id);
}
