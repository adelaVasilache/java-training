package javatraining.training.mappers;

import java.util.Set;
import javatraining.training.dtos.CommentDto;
import javatraining.training.dtos.UserCommentDto;
import javatraining.training.models.Comment;
import javatraining.training.models.User;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-05-03T14:46:28+0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_161 (Oracle Corporation)"
)
public class CommentMapperImpl extends CommentMapper {

    @Override
    public void copyProperties(CommentDto commentDto, Comment comment) {
        if ( commentDto == null ) {
            return;
        }

        comment.setCreated( commentDto.getCreated() );
        comment.setContent( commentDto.getContent() );
        if ( commentDto.getUser() != null ) {
            if ( comment.getUser() == null ) {
                comment.setUser( new User() );
            }
            userCommentDtoToUser( commentDto.getUser(), comment.getUser() );
        }
        else {
            comment.setUser( null );
        }
    }

    @Override
    public void copyProperties(Set<Comment> comment, Set<CommentDto> commentDto) {
        if ( comment == null ) {
            return;
        }

        commentDto.clear();
        for ( Comment comment1 : comment ) {
            commentDto.add( commentToCommentDto( comment1 ) );
        }
    }

    protected void userCommentDtoToUser(UserCommentDto userCommentDto, User mappingTarget) {
        if ( userCommentDto == null ) {
            return;
        }

        mappingTarget.setEmail( userCommentDto.getEmail() );
        mappingTarget.setFirstName( userCommentDto.getFirstName() );
        mappingTarget.setLastName( userCommentDto.getLastName() );
    }

    protected UserCommentDto userToUserCommentDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserCommentDto userCommentDto = new UserCommentDto();

        userCommentDto.setEmail( user.getEmail() );
        userCommentDto.setFirstName( user.getFirstName() );
        userCommentDto.setLastName( user.getLastName() );

        return userCommentDto;
    }

    protected CommentDto commentToCommentDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentDto commentDto = new CommentDto();

        commentDto.setContent( comment.getContent() );
        commentDto.setUser( userToUserCommentDto( comment.getUser() ) );
        commentDto.setCreated( comment.getCreated() );

        return commentDto;
    }
}
