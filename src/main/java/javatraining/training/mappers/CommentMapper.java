package javatraining.training.mappers;

import javatraining.training.dtos.CommentDto;
import javatraining.training.models.Comment;
import javatraining.training.models.Post;
import javatraining.training.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Adela Vasilache on 24.04.2018
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class CommentMapper {
    public static final CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    public abstract void copyProperties(CommentDto commentDto, @MappingTarget Comment comment);

    public Comment toComment(CommentDto commentDto, Post post, User user) {
        Comment comment = new Comment();
        copyProperties(commentDto, comment);
        comment.setCreated(new Date());
        comment.setPost(post);
        comment.setUser(user);

        return comment;
    }

    public Comment toCommentWithoutUser(CommentDto commentDto, Post post) {
        Comment comment = new Comment();
        copyProperties(commentDto, comment);
        comment.setCreated(new Date());
        comment.setPost(post);

        return comment;
    }

    public abstract void copyProperties(Set<Comment> comment, @MappingTarget Set<CommentDto> commentDto);

    public Set<CommentDto> toCommentDtoList(Set<Comment> commentList) {
        Set<CommentDto> commentDtos = new HashSet<>();
        copyProperties(commentList, commentDtos);

        return commentDtos;
    }
}
