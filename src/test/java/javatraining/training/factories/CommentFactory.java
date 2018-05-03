package javatraining.training.factories;

import javatraining.training.constants.PostConstants;
import javatraining.training.dtos.CommentDto;

import java.util.Date;

/**
 * Created by Adela Vasilache on 02.05.2018
 */
public class CommentFactory {
    public static CommentDto createCommentDto(){
        return CommentDto.builder().postId(1L).content(PostConstants.POST_CONTENT).created(new Date()).
                user(UserFactory.createUserCommentDto()).build();
    }

    public static CommentDto createCommentDto(Long postId){
        return CommentDto.builder().postId(postId).content(PostConstants.POST_CONTENT).created(new Date()).
                user(UserFactory.createUserCommentDto()).build();
    }
}
