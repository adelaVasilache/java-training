package javatraining.training.controllers;

import javatraining.training.config.TemplatePath;
import javatraining.training.dtos.CommentDto;
import javatraining.training.dtos.GradeDto;
import javatraining.training.dtos.PostDto;
import javatraining.training.exceptions.DuplicatePostException;
import javatraining.training.exceptions.GradeException;
import javatraining.training.exceptions.InvalidDataException;
import javatraining.training.exceptions.NotFoundException;
import javatraining.training.exceptions.UserRightsException;
import javatraining.training.services.business.PostBusinessService;
import javatraining.training.services.domain.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;
import java.util.Set;

/**
 * Created by Adela Vasilache on 23.04.2018
 */
@Controller
@RequestMapping(value = "/post")
public class PostController {
    private final PostBusinessService postBusinessService;
    private final PostService postService;
    private final TemplatePath templatePath;

    @Autowired
    public PostController(PostBusinessService postBusinessService, PostService postService, TemplatePath templatePath) {
        this.postBusinessService = postBusinessService;
        this.postService = postService;
        this.templatePath = templatePath;
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public ResponseEntity<PostDto> addPost(@RequestBody @Valid PostDto postDto) throws NotFoundException, InvalidDataException, DuplicatePostException, GradeException {
        postBusinessService.addPost(postDto, SecurityContextHolder.getContext().getAuthentication());
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    @RequestMapping(value = "/comment", method = RequestMethod.POST)
    public Set<CommentDto> addComment(@RequestBody @Valid CommentDto commentDto) throws NotFoundException {
        return postBusinessService.addComment(commentDto, SecurityContextHolder.getContext().getAuthentication());
    }

    @RequestMapping(value = "/latest/{perPage}", method = RequestMethod.GET)
    public String getLatest(@PathVariable Integer perPage, Model model) {
        model.addAttribute("latestPosts", postService.getLatestPosts(perPage));

        return templatePath.getTemplate("post.latest");
    }

    @RequestMapping(value = "/rate", method = RequestMethod.POST)
    public @ResponseBody PostDto ratePost(@RequestBody @Valid GradeDto gradeDto, Model model) throws NotFoundException {
        model.addAttribute("gradedPost", postBusinessService.ratePost(gradeDto,
                SecurityContextHolder.getContext().getAuthentication()));

        return postBusinessService.ratePost(gradeDto, SecurityContextHolder.getContext().getAuthentication());
//        return templatePath.getTemplate("post.latest");
    }

    @RequestMapping(value = "/popular/{perPage}", method = RequestMethod.GET)
    public ResponseEntity<Page<PostDto>> getPopular(@PathVariable Integer perPage) {
        return new ResponseEntity<>(postService.getPopularPosts(perPage), HttpStatus.OK);
    }

    @RequestMapping(value = "/all/{month}/{year}", method = RequestMethod.GET)
    public ResponseEntity<Page<PostDto>> getForMonth(@PathVariable Integer month, @PathVariable Integer year) throws ParseException {
        return new ResponseEntity<>(postService.getAllForMonth(year, month, new PageRequest(0, 2)), HttpStatus.OK);
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public ResponseEntity<PostDto> editPost(@RequestBody @Valid PostDto postDto) throws NotFoundException, UserRightsException, InvalidDataException, GradeException {
        return new ResponseEntity<>(postBusinessService.editPost(postDto, SecurityContextHolder.getContext().getAuthentication()), HttpStatus.OK);
    }

    @RequestMapping(value = "/add/file/{postId}", method = RequestMethod.POST)
    public ResponseEntity<HttpStatus> AddFile(@RequestParam("file")MultipartFile file, @PathVariable String postId) throws NotFoundException {
        postBusinessService.addFile(file, postId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(value = "/post/{like}", method = RequestMethod.GET)
    public List<PostDto> test(@PathVariable String like){
        return postService.getAllLike(like);
    }
}
