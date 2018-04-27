package javatraining.training.factories;

import javatraining.training.dtos.ImageDto;
import javatraining.training.dtos.PostDto;
import javatraining.training.dtos.TagDto;

import java.util.Set;

/**
 * Created by Adela Vasilache on 26.04.2018
 */
public class PostFactory {
    public static PostDto createPostDto(String title, String content, Set<TagDto>tags, Set<ImageDto> images,
                                        Double grade){
        return PostDto.builder().title(title).content(content).tags(tags).images(images).grade(grade).build();
    }
}
