package com.myblog2.service.impl;

import com.myblog2.entity.Post;
import com.myblog2.exception.ResourceNotFound;
import com.myblog2.payload.PostDto;
import com.myblog2.repository.PostRepository;
import com.myblog2.service.PostService;
import org.hibernate.event.spi.PostCommitDeleteEventListener;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public PostDto savePost(PostDto postDto) {
        Post post = mapToEntity(postDto);
        Post savedPost = postRepository.save(post);
        PostDto dto = mapToDto(savedPost);
        return dto;
    }

    @Override
    public void deleteById(long id) {

        Post post = postRepository.findById(id).orElseThrow(
                () -> new ResourceNotFound("Post not found with id " + id)
        );

        postRepository.deleteById(id);
    }

    Post mapToEntity(PostDto postDto){
        Post post = new Post();
        post.setTitle(postDto.getTitle());
        post.setDescription(postDto.getDescription());
        post.setContent(postDto.getContent());

        return post;
    }

    PostDto mapToDto(Post post){
        PostDto dto = new PostDto();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setDescription(post.getDescription());
        dto.setContent(post.getContent());

        return dto;
    }
}
