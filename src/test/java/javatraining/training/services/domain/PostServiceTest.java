package javatraining.training.services.domain;

import javatraining.training.dtos.CommentDto;
import javatraining.training.dtos.GradeDto;
import javatraining.training.dtos.PostDto;
import javatraining.training.exceptions.NotFoundException;
import javatraining.training.factories.CommentFactory;
import javatraining.training.factories.GradeFactory;
import javatraining.training.factories.UserFactory;
import javatraining.training.models.Post;
import javatraining.training.models.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.transaction.Transactional;
import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

/**
 * Created by Adela Vasilache on 26.04.2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@TestPropertySource(locations = "classpath:application-test.properties")
@SqlGroup({
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:beforeTestRun.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:afterTestRun.sql")
})
@Transactional
public class PostServiceTest {
    @Autowired
    PostService postService;

    @Test
    public void getLatestPostsTest(){
        List<PostDto> latestPosts = postService.getLatestPosts(2).getContent();

        assertTrue(latestPosts.stream().noneMatch(post -> post.getTitle().equals("test post1")));
    }

    @Test
    public void findPostByIdTest() throws NotFoundException {
        Post post = postService.findPostById(1L);

        assertTrue(post.getTitle().equals("test post1"));
    }

    @Test(expected = NotFoundException.class)
    public void findPostByIdShouldThrowNotFoundException() throws NotFoundException {
        postService.findPostById(10L);
    }

    @Test
    public void countPostsByTitleTest(){
        Long numberOfPosts = postService.countPostsByTitle("test post1");

       assertTrue(numberOfPosts.equals(1L));
    }

    @Test(expected = NotFoundException.class)
    public void addCommentShouldThrowNotFoundException() throws NotFoundException {
        CommentDto commentDto = CommentFactory.createCommentDto(4L);
        User user = UserFactory.createUser();
        postService.addComment(commentDto, user);
    }

    @Test
    public void ratePostTest() throws NotFoundException {
        GradeDto gradeDto = GradeFactory.createGradeDto();
        postService.ratePost(gradeDto);

        GradeDto gradeDtoFirstGrade = GradeFactory.createGradeDto(3L);
        postService.ratePost(gradeDtoFirstGrade);

        assertEquals(postService.findPostById(1L).getGrade(), Double.valueOf(9L));
        assertEquals(postService.findPostById(3L).getGrade(), Double.valueOf(10));
    }

    @Test(expected = NotFoundException.class)
    public void ratePostShouldThrowNotFoundException() throws NotFoundException {
        GradeDto gradeDto = GradeFactory.createGradeDto(7L);
        postService.ratePost(gradeDto);
    }

    @Test
    public void getPopularPostsTest(){
        List<String> postNames = Arrays.asList("test post1", "test post2");
        List<PostDto> posts = postService.getPopularPosts(2).getContent();
        List<String> actualPostnames = posts.stream().map(PostDto::getTitle).collect(Collectors.toList());

        assertTrue(postNames.size() == actualPostnames.size() &&
                postNames.containsAll(actualPostnames) && actualPostnames.containsAll(postNames));
    }

    @Test
    public void getAllForMonthTest() throws ParseException {
        List<PostDto> postsMay = postService.getAllForMonth(2018, 5,
                new PageRequest(0, 10)).getContent();
        List<PostDto> postsApril = postService.getAllForMonth(2018, 4,
                new PageRequest(0, 10)).getContent();
        List<PostDto> postsMarch = postService.getAllForMonth(2018, 3,
                new PageRequest(0, 10)).getContent();

        assertEquals(postsMay.size(), 3);
        assertEquals(postsApril.size(), 2);
        assertEquals(postsMarch.size(), 0);
    }

}
