package javatraining.training.services.business;

import javatraining.training.dtos.CommentDto;
import javatraining.training.dtos.PostDto;
import javatraining.training.exceptions.NotFoundException;
import org.springframework.security.core.Authentication;

import java.util.Set;

/**
 * Created by Adela Vasilache on 24.04.2018
 */
public interface PostBusinessService {

    void addPost(PostDto postDto, Authentication authentication) throws NotFoundException;

    Set<CommentDto> addComment(CommentDto commentDto, Authentication authentication) throws NotFoundException;
}
