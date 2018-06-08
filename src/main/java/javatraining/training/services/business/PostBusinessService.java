package javatraining.training.services.business;

import javatraining.training.dtos.CommentDto;
import javatraining.training.dtos.GradeDto;
import javatraining.training.dtos.PostDto;
import javatraining.training.exceptions.DuplicatePostException;
import javatraining.training.exceptions.GradeException;
import javatraining.training.exceptions.InvalidDataException;
import javatraining.training.exceptions.NotFoundException;
import javatraining.training.exceptions.UserRightsException;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

/**
 * Created by Adela Vasilache on 24.04.2018
 */
public interface PostBusinessService {

    void addPost(PostDto postDto, Authentication authentication) throws NotFoundException, InvalidDataException, DuplicatePostException, GradeException;

    Set<CommentDto> addComment(CommentDto commentDto, Authentication authentication) throws NotFoundException;

    PostDto ratePost(GradeDto gradeDto, Authentication authentication) throws NotFoundException;

    PostDto editPost(PostDto postDto, Authentication authentication) throws NotFoundException, UserRightsException, InvalidDataException, GradeException;

    void addFile(MultipartFile file, String postId) throws NotFoundException;
}
