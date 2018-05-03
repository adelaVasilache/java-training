package javatraining.training.services.business.impl;

import javatraining.training.dtos.CommentDto;
import javatraining.training.dtos.PostDto;
import javatraining.training.exceptions.DuplicatePostException;
import javatraining.training.exceptions.GradeException;
import javatraining.training.exceptions.InvalidDataException;
import javatraining.training.exceptions.NotFoundException;
import javatraining.training.exceptions.UserRightsException;
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
    public void addPost(PostDto postDto, Authentication authentication) throws NotFoundException, InvalidDataException, DuplicatePostException, GradeException {
        if(postDto.getGrade() != null) {
            throw new GradeException(postDto.getTitle());
        }
        User user = userService.getUserByEmail(authentication.getPrincipal().toString());
        if (postService.countPostsByTitle(postDto.getTitle()) > 0) {
            throw new DuplicatePostException(postDto.getTitle());
        }
            Post post = postMapper.setProperties(postDto, user, null);
            post.setTags(tagsService.addTagsThatDontExist(post.getTags()));
            post.setImages(imageService.addImagesThatDontExist(post.getImages()));
            try {
                postService.save(post);
            } catch (Exception e) {
                throw new InvalidDataException();
            }

    }

    @Override
    public PostDto editPost(PostDto postDto, Authentication authentication) throws NotFoundException, UserRightsException, InvalidDataException, GradeException {
        if(postDto.getGrade() != null) {
            throw new GradeException(postDto.getTitle());
        }
        Post post = postService.findPostById(postDto.getPostId());
        if (!post.getUser().getEmail().equals(authentication.getPrincipal().toString())) {
            throw new UserRightsException(authentication.getPrincipal().toString());
        }
        Post postUpdated = postMapper.setProperties(postDto, post.getUser(), post.getId());
        postUpdated.setTags(tagsService.addTagsThatDontExist(postUpdated.getTags()));
        postUpdated.setImages(imageService.addImagesThatDontExist(postUpdated.getImages()));
        try {
            postService.save(postUpdated);
        } catch (Exception e) {
            throw new InvalidDataException();
        }

        return postMapper.toPostDto(postUpdated);
    }

    @Override
    public Set<CommentDto> addComment(CommentDto commentDto, Authentication authentication) throws NotFoundException {
        User user = userService.getUserByEmail(authentication.getPrincipal().toString());

        return postService.addComment(commentDto, user);
    }

}
