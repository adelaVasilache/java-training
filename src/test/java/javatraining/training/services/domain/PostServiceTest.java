package javatraining.training.services.domain;

import javatraining.training.dtos.ImageDto;
import javatraining.training.dtos.PostDto;
import javatraining.training.dtos.TagDto;
import javatraining.training.factories.ImageFactory;
import javatraining.training.factories.PostFactory;
import javatraining.training.factories.TagFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Adela Vasilache on 26.04.2018
 */
public class PostServiceTest {
    private final PostService postService;

    @Autowired
    public PostServiceTest(PostService postService) {
        this.postService = postService;
    }

}
