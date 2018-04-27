package javatraining.training.services.business;

import javatraining.training.dtos.ImageDto;
import javatraining.training.dtos.PostDto;
import javatraining.training.dtos.TagDto;
import javatraining.training.factories.ImageFactory;
import javatraining.training.factories.PostFactory;
import javatraining.training.factories.TagFactory;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Adela Vasilache on 26.04.2018
 */
public class PostBusinessServiceTest {
   private final PostBusinessService postBusinessService;

   @Autowired
    public PostBusinessServiceTest(PostBusinessService postBusinessService) {
        this.postBusinessService = postBusinessService;
    }

    @Test
    public void addPost(){
        Set<ImageDto> images = new HashSet<>(Arrays.asList(ImageFactory.createImageDto("image1", "path1"),
                ImageFactory.createImageDto("image2", "path2")));
        Set<TagDto> tags = new HashSet<>(Arrays.asList(TagFactory.createTagDto("tag1"),
                TagFactory.createTagDto("tag2")));
        PostDto post = PostFactory.createPostDto("test post", "test content", tags, images, 8d);
        //UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken();
        //authenticationToken.set
        //Authentication authentication = SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken());
        //postBusinessService.addPost(post, );
    }
}
