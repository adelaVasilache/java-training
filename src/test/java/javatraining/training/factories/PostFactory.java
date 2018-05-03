package javatraining.training.factories;

import javatraining.training.constants.PostConstants;
import javatraining.training.dtos.ImageDto;
import javatraining.training.dtos.PostDto;
import javatraining.training.dtos.TagDto;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Adela Vasilache on 26.04.2018
 */
public class PostFactory {
    public static PostDto createPostDto(String title, String content){
        return PostDto.builder().title(title).content(content).tags(TagFactory.createTagDtoSet()).
                images(ImageFactory.createImageDtoSet()).build();
    }

    public static PostDto createPostDto(){
        Set<ImageDto> images = ImageFactory.createImageDtoSet();
        Set<TagDto> tags = TagFactory.createTagDtoSet();
        return PostDto.builder().title(PostConstants.POST_TITLE).content(PostConstants.POST_CONTENT).
                images(images).tags(tags).build();
    }
}
