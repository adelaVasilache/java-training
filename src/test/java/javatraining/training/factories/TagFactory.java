package javatraining.training.factories;

import javatraining.training.dtos.TagDto;

/**
 * Created by Adela Vasilache on 26.04.2018
 */
public class TagFactory {
    public static TagDto createTagDto(String name){
        return TagDto.builder().name(name).build();
    }
}
