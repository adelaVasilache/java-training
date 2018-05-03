package javatraining.training.factories;

import javatraining.training.constants.PostConstants;
import javatraining.training.dtos.ImageDto;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Adela Vasilache on 26.04.2018
 */
public class ImageFactory {
    public static ImageDto createImageDto(String fileName, String filePath){
       return ImageDto.builder().fileName(fileName).filePath(filePath).build();
    }

    public static ImageDto createImageDto(){
        return ImageDto.builder().fileName(PostConstants.FILE_NAME).filePath(PostConstants.FILE_PATH).build();
    }

    public static Set<ImageDto> createImageDtoSet(){
        return new HashSet<>(Arrays.asList(ImageFactory.createImageDto("image1", "path1"),
                ImageFactory.createImageDto("image2", "path2")));
    }

}
