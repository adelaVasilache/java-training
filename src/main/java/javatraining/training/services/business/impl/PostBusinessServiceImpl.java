package javatraining.training.services.business.impl;

import javatraining.training.dtos.CommentDto;
import javatraining.training.dtos.PostDto;
import javatraining.training.exceptions.NotFoundException;
import javatraining.training.mappers.PostMapper;
import javatraining.training.models.Post;
import javatraining.training.models.User;
import javatraining.training.services.business.PostBusinessService;
import javatraining.training.services.domain.ImageService;
import javatraining.training.services.domain.PostService;
import javatraining.training.services.domain.TagsService;
import javatraining.training.services.domain.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by Adela Vasilache on 24.04.2018
 */
@Service
public class PostBusinessServiceImpl implements PostBusinessService {
    private final TagsService tagsService;
    private final PostMapper postMapper;
    private final PostService postService;
    private final UserService userService;
    private final ImageService imageService;

    @Autowired
    public PostBusinessServiceImpl(TagsService tagsService, PostService postService, UserService userService, ImageService imageService) {
        this.tagsService = tagsService;
        this.postService = postService;
        this.userService = userService;
        this.imageService = imageService;
        this.postMapper = PostMapper.INSTANCE;
    }

    @Override
    public void addPost(PostDto postDto, Authentication authentication) throws NotFoundException {
        User user = userService.getUserByEmail(authentication.getPrincipal().toString());
        Post post = postMapper.copyProperties(postDto, user);
        tagsService.addTagsThatDontExist(post.getTags());
        imageService.addImagesThatDontExist(post.getImages());
        postService.save(post);
    }

    @Override
    public Set<CommentDto> addComment(CommentDto commentDto, Authentication authentication) throws NotFoundException {
        User user = userService.getUserByEmail(authentication.getPrincipal().toString());
       return postService.addComment(commentDto, user);
    }

}
