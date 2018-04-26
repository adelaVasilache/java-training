package javatraining.training.services.domain.impl;

import javatraining.training.dtos.CommentDto;
import javatraining.training.dtos.GradeDto;
import javatraining.training.dtos.PostDto;
import javatraining.training.exceptions.NotFoundException;
import javatraining.training.mappers.CommentMapper;
import javatraining.training.mappers.PostMapper;
import javatraining.training.models.Comment;
import javatraining.training.models.Post;
import javatraining.training.models.User;
import javatraining.training.services.domain.PostService;
import javatraining.training.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Set;

/**
 * Created by Adela Vasilache on 23.04.2018
 */
@Service
public class PostServiceImpl implements PostService {
    private final CommentMapper commentMapper;
    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Autowired
    public PostServiceImpl(PostRepository postRepository) {
        this.postMapper = PostMapper.INSTANCE;
        this.commentMapper = CommentMapper.INSTANCE;
        this.postRepository = postRepository;
    }

    @Override
    public Set<CommentDto> addComment(CommentDto commentDto, User user) throws NotFoundException {
        Post post = postRepository.findById(commentDto.getPostId()).orElseThrow(
                () -> new NotFoundException(commentDto.getPostId().toString()));
        Set<Comment> comments = post.getComments();
        comments.add(commentMapper.toComment(commentDto, post, user));
        post.setComments(comments);
        save(post);

        return commentMapper.toCommentDtoList(post.getComments());
    }

    @Override
    public Page<PostDto> getLatestPosts(Integer perPage){
        Pageable limit = new PageRequest(0,perPage, Sort.Direction.DESC, "created");
        Page<Post> latestPosts = postRepository.findAll(limit);

        return postMapper.toPagePostDto(latestPosts);
    }

    @Override
    public PostDto ratePost(GradeDto gradeDto) throws NotFoundException {
        Post post = postRepository.findById(gradeDto.getPostId()).orElseThrow(() ->
                new NotFoundException(gradeDto.getPostId().toString()));
        Double grade = (post.getGrade() + gradeDto.getGrade()) / 2;
        post.setGrade(grade);
        save(post);

        return postMapper.toPostDto(post);
    }

    @Override
    public Page<PostDto> getPopularPosts(Integer perPage){
        Pageable limit = new PageRequest(0, perPage, Sort.Direction.DESC, "grade");
        Page<Post> popularPosts = postRepository.findAll(limit);

        return postMapper.toPagePostDto(popularPosts);
    }

    @Override
    public void save(Post post) {
        postRepository.save(post);
    }
}
