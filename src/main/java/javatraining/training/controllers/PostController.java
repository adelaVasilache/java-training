package javatraining.training.controllers;

import com.sun.deploy.net.HttpDownload;
import com.sun.media.sound.InvalidDataException;
import javatraining.training.dtos.CommentDto;
import javatraining.training.dtos.GradeDto;
import javatraining.training.dtos.PostDto;
import javatraining.training.exceptions.NotFoundException;
import javatraining.training.exceptions.UserRightsException;
import javatraining.training.models.Comment;
import javatraining.training.services.business.PostBusinessService;
import javatraining.training.services.domain.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.Set;

/**
 * Created by Adela Vasilache on 23.04.2018
 */
@RestController
@RequestMapping(value = "/post")
public class PostController {
    private final PostBusinessService postBusinessService;
    private final PostService postService;

    @Autowired
    public PostController(PostBusinessService postBusinessService, PostService postService) {
        this.postBusinessService = postBusinessService;
        this.postService = postService;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public void addPost(@RequestBody @Valid PostDto postDto) throws NotFoundException, InvalidDataException {
        postBusinessService.addPost(postDto, SecurityContextHolder.getContext().getAuthentication());
    }

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Set<CommentDto> addComment(@RequestBody @Valid CommentDto commentDto) throws NotFoundException {
        return postBusinessService.addComment(commentDto, SecurityContextHolder.getContext().getAuthentication());
    }

    @RequestMapping(value = "/latest/{perPage}", method = RequestMethod.GET)
    public ResponseEntity<Page<PostDto>> getLatest(@PathVariable Integer perPage){
        return new ResponseEntity<>(postService.getLatestPosts(perPage), HttpStatus.OK);
    }

    @RequestMapping(value = "/rate", method = RequestMethod.POST)
    public ResponseEntity<PostDto> ratePost(@RequestBody @Valid GradeDto gradeDto) throws NotFoundException {
        return new ResponseEntity<>(postService.ratePost(gradeDto), HttpStatus.OK);
    }

    @RequestMapping(value = "/popular/{perPage}")
    public ResponseEntity<Page<PostDto>> getPopular(@PathVariable Integer perPage){
        return new ResponseEntity<>(postService.getPopularPosts(perPage), HttpStatus.OK);
    }

    @RequestMapping(value = "/all/{year}/{month}", method = RequestMethod.GET)
    public ResponseEntity<Page<PostDto>> getForMonth(@PathVariable Integer year, @PathVariable Integer month) throws ParseException {
        return new ResponseEntity<>(postService.getAllForMonth(year, month,new PageRequest(0, 10)), HttpStatus.OK);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResponseEntity<PostDto> editPost(@RequestBody @Valid PostDto postDto) throws NotFoundException, UserRightsException, InvalidDataException {
        return new ResponseEntity<>(postBusinessService.editPost(postDto, SecurityContextHolder.getContext().getAuthentication()), HttpStatus.OK);
    }
}
