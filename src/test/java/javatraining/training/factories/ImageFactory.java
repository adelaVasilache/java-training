package javatraining.training.factories;

import javatraining.training.dtos.ImageDto;

import java.util.Set;

/**
 * Created by Adela Vasilache on 26.04.2018
 */
public class ImageFactory {
    public static ImageDto createImageDto(String fileName, String filePath){
       return ImageDto.builder().fileName(fileName).filePath(filePath).build();
    }

}
