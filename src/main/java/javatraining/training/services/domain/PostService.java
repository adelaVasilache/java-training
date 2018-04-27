package javatraining.training.services.domain;

import javatraining.training.dtos.CommentDto;
import javatraining.training.dtos.GradeDto;
import javatraining.training.dtos.PostDto;
import javatraining.training.exceptions.NotFoundException;
import javatraining.training.exceptions.UserRightsException;
import javatraining.training.models.Post;
import javatraining.training.models.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;

import java.text.ParseException;
import java.util.Set;

/**
 * Created by Adela Vasilache on 23.04.2018
 */
public interface PostService {

    Post findPostById(Long id) throws NotFoundException;

    void save(Post post);

    Set<CommentDto> addComment(CommentDto commentDto, User user) throws NotFoundException;

    Page<PostDto> getLatestPosts(Integer perPage);

    PostDto ratePost(GradeDto gradeDto) throws NotFoundException;

    Page<PostDto> getPopularPosts(Integer perPage);

    Page<PostDto> getAllForMonth(Integer year, Integer month, Pageable pageable) throws ParseException;

}
