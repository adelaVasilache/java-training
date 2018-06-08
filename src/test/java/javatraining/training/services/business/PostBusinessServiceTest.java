package javatraining.training.services.business;

import javatraining.training.constants.UserConstants;
import javatraining.training.dtos.CommentDto;
import javatraining.training.dtos.PostDto;
import javatraining.training.exceptions.DuplicatePostException;
import javatraining.training.exceptions.GradeException;
import javatraining.training.exceptions.InvalidDataException;
import javatraining.training.exceptions.NotFoundException;
import javatraining.training.exceptions.UserRightsException;
import javatraining.training.factories.CommentFactory;
import javatraining.training.factories.PostFactory;
import javatraining.training.services.domain.PostService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;

import static org.junit.Assert.assertEquals;

/**
 * Created by Adela Vasilache on 26.04.2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@TestPropertySource(locations = "classpath:application-test.properties")
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTestRun.sql"),
})
@Transactional
public class PostBusinessServiceTest {
    @Autowired
   PostBusinessService postBusinessService;
    @Autowired
   PostService postService;

    @Test
    public void addPostTest() throws DuplicatePostException, InvalidDataException, NotFoundException, GradeException {
        PostDto postDto = PostFactory.createPostDto();
        Authentication auth = new UsernamePasswordAuthenticationToken(UserConstants.EMAIL, UserConstants.PASSWORD);
        postBusinessService.addPost(postDto, auth);

        assertEquals(postService.countPostsByTitle("test post"), Long.valueOf(1));
    }

    @Test(expected = DuplicatePostException.class)
    public void addPostShouldThrowDuplicatePostException() throws DuplicatePostException, InvalidDataException, NotFoundException, GradeException {
        PostDto postDto = PostFactory.createPostDto("test post1", "test content");
        Authentication auth = new UsernamePasswordAuthenticationToken(UserConstants.EMAIL, UserConstants.PASSWORD);
        postBusinessService.addPost(postDto, auth);
    }

    @Test(expected = NotFoundException.class)
    public void addPostShouldThrowNotFoundException() throws DuplicatePostException, InvalidDataException, NotFoundException, GradeException {
        PostDto postDto = PostFactory.createPostDto("test post1", "test content");
        Authentication auth = new UsernamePasswordAuthenticationToken("nonExistentUser", UserConstants.PASSWORD);
        postBusinessService.addPost(postDto, auth);
    }

    @Test(expected = GradeException.class)
    public void addPostShouldThrowGradeException() throws DuplicatePostException, InvalidDataException, NotFoundException, GradeException {
        PostDto postDto = PostFactory.createPostDto();
        postDto.setGrade(8d);
        Authentication auth = new UsernamePasswordAuthenticationToken(UserConstants.EMAIL, UserConstants.PASSWORD);
        postBusinessService.addPost(postDto, auth);
    }

    @Test(expected = UserRightsException.class)
    public void editPostShouldThrowUserRightsException() throws UserRightsException, InvalidDataException, NotFoundException, GradeException {
        PostDto postDto = PostFactory.createPostDto("test post edited", "test content");
        postDto.setId(1L);
        Authentication auth = new UsernamePasswordAuthenticationToken(UserConstants.EMAIL_WRONG, UserConstants.PASSWORD);
        postBusinessService.editPost(postDto, auth);
    }

    @Test(expected = NotFoundException.class)
    public void editPostShouldThrowNotFoundException() throws UserRightsException, InvalidDataException, NotFoundException, GradeException {
        PostDto postDto = PostFactory.createPostDto("test post edited", "test content");
        postDto.setId(10L);
        Authentication auth = new UsernamePasswordAuthenticationToken(UserConstants.EMAIL_WRONG, UserConstants.PASSWORD);
        postBusinessService.editPost(postDto, auth);
    }

    @Test(expected = GradeException.class)
    public void editPostShouldThrowGradeException() throws UserRightsException, InvalidDataException, NotFoundException, GradeException {
        PostDto postDto = PostFactory.createPostDto("test post edited", "test content");
        postDto.setGrade(8d);
        Authentication auth = new UsernamePasswordAuthenticationToken(UserConstants.EMAIL_WRONG, UserConstants.PASSWORD);
        postBusinessService.editPost(postDto, auth);
    }

    @Test
    public void addCommentTest() throws NotFoundException {
        CommentDto commentDto = CommentFactory.createCommentDto();
        Authentication auth = new UsernamePasswordAuthenticationToken(UserConstants.EMAIL, UserConstants.PASSWORD);
        postBusinessService.addComment(commentDto, auth);

        assertEquals(postService.findPostById(1L).getComments().size(), 3);
    }

    @Test(expected = NotFoundException.class)
    public void addCommentShouldThrowNotFoundException() throws NotFoundException {
        CommentDto commentDto = CommentFactory.createCommentDto(4L);
        Authentication auth = new UsernamePasswordAuthenticationToken(UserConstants.EMAIL, UserConstants.PASSWORD);
        postBusinessService.addComment(commentDto, auth);
    }
}
