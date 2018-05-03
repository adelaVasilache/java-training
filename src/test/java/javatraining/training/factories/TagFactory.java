package javatraining.training.factories;

import javatraining.training.constants.PostConstants;
import javatraining.training.dtos.TagDto;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Adela Vasilache on 26.04.2018
 */
public class TagFactory {
    public static TagDto createTagDto(String name){
        return TagDto.builder().name(name).build();
    }

    public static TagDto createTagDto(){
        return TagDto.builder().name(PostConstants.TAG_NAME).build();
    }

    public static Set<TagDto> createTagDtoSet(){
        return new HashSet<>(Arrays.asList(TagFactory.createTagDto("tag1"),
                TagFactory.createTagDto("tag2")));
    }
}
