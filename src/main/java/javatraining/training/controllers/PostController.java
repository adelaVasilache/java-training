package javatraining.training.controllers;

import javatraining.training.dtos.PostDto;
import javatraining.training.services.domain.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Adela Vasilache on 23.04.2018
 */
@RestController
@RequestMapping(value = "/post")
public class PostController {
    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public void addPost(@RequestBody  PostDto postDto){
        PostDto post = postService.addPost(postDto);
    }
}
