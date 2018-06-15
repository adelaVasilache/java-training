package javatraining.training.services.business.impl;

import javatraining.training.dtos.CommentDto;
import javatraining.training.dtos.GradeDto;
import javatraining.training.dtos.PostDto;
import javatraining.training.exceptions.DuplicatePostException;
import javatraining.training.exceptions.GradeException;
import javatraining.training.exceptions.InvalidDataException;
import javatraining.training.exceptions.NotFoundException;
import javatraining.training.exceptions.UserRightsException;
import javatraining.training.mappers.PostMapper;
import javatraining.training.models.Comment;
import javatraining.training.models.Post;
import javatraining.training.models.User;
import javatraining.training.services.business.PostBusinessService;
import javatraining.training.services.domain.CommentService;
import javatraining.training.services.domain.ImageService;
import javatraining.training.services.domain.PostService;
import javatraining.training.services.domain.StorageService;
import javatraining.training.services.domain.TagsService;
import javatraining.training.services.domain.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
    private final StorageService storageService;
    private final CommentService commentService;

    @Autowired
    public PostBusinessServiceImpl(TagsService tagsService, PostService postService, UserService userService, ImageService imageService, StorageService storageService, CommentService commentService) {
        this.tagsService = tagsService;
        this.postService = postService;
        this.userService = userService;
        this.imageService = imageService;
        this.storageService = storageService;
        this.commentService = commentService;
        this.postMapper = PostMapper.INSTANCE;
    }

    @Override
    public void addPost(PostDto postDto, Authentication authentication) throws NotFoundException, InvalidDataException, DuplicatePostException, GradeException {
        if (postDto.getGrade() != null) {
            throw new GradeException(postDto.getTitle());
        }
        User user = userService.getUserByEmail(authentication.getPrincipal().toString());
        if (postService.countPostsByTitle(postDto.getTitle()) > 0) {
            throw new DuplicatePostException(postDto.getTitle());
        }
        Post post = postMapper.setProperties(postDto, user, null);
        post.setTags(tagsService.addTagsThatDontExist(post.getTags()));
        if (post.getImages() != null) {
            post.setImages(imageService.addImagesThatDontExist(post.getImages()));
        }
        try {
            postService.save(post);
        } catch (Exception e) {
            throw new InvalidDataException();
        }

    }

    @Override
    public PostDto editPost(PostDto postDto, Authentication authentication) throws NotFoundException, UserRightsException, InvalidDataException, GradeException {
        if (postDto.getGrade() != null) {
            throw new GradeException(postDto.getTitle());
        }
        Post post = postService.findPostById(postDto.getId());
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
    public String addComment(CommentDto commentDto, Authentication authentication) throws NotFoundException {
        try {
            User user = userService.getUserByEmail(authentication.getPrincipal().toString());

            return postService.addComment(commentDto, user, true).toString();
        } catch(NotFoundException e) {
            User user = new User();

            return postService.addComment(commentDto, user, false).toString();
        }
    }

    @Override
    public PostDto ratePost(GradeDto gradeDto, Authentication authentication) throws NotFoundException {
        try {
        User user = userService.getUserByEmail(authentication.getPrincipal().toString());

        return postService.ratePost(gradeDto, user, true);

        } catch (NotFoundException e) {
           User user = new User();

           return postService.ratePost(gradeDto, user, false);
        }
    }

    @Override
    public void addFile(MultipartFile file, String postId) throws NotFoundException {
        if (postService.countPostsById(Long.valueOf(postId)) != 1) {
            throw new NotFoundException(postId);
        }
        storageService.store(file, postId);
    }

//    @Override
//    public Page<PostDto> getLatestPosts(Integer perPage) throws NotFoundException {
//        List<Post> latestPosts = postService.getLatestPosts(perPage).getContent();
//        Post post = latestPosts.stream().findFirst().orElseThrow(() -> new NotFoundException(""));
//        Integer comments = post.getComments().size();
//        List<Comment> commentList = commentService.getCommentsByPost(latestPosts);
//
//        List<PostDto> postDtoList = postMapper.toPostDtoList(latestPosts);
//
//        postDtoList.forEach(post -> post.setNumberOfComments(commentList.stream().filter(
//                comment -> comment.getPost().getId().equals(post.getId())).count()));
//
//        return new PageImpl<>(postDtoList, new PageRequest(0, 2), postDtoList.size());
//    }

}
