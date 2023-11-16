package com.myblog2.controller;

import com.myblog2.payload.PostDto;
import com.myblog2.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }



    // http://localhost:8080/api/posts
    // Saving the Data into the database by using @PostMapping
    @PostMapping
    public ResponseEntity<PostDto> createPost(
            @RequestBody PostDto postDto
    ){
        PostDto dto = postService.savePost(postDto);

        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }


    // http://localhost:8080/api/posts/9
    // Delete the Data into the database by using @DeleteMapping
    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteById(
            @PathVariable long id
    ){
        postService.deleteById(id);

        return new ResponseEntity<>("Post is deleted", HttpStatus.OK);
    }
}
