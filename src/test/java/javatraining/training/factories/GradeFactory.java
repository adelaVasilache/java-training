package javatraining.training.factories;

import javatraining.training.dtos.GradeDto;

/**
 * Created by Adela Vasilache on 03.05.2018
 */
public class GradeFactory {
    public static GradeDto createGradeDto(){
        return GradeDto.builder().postId("1").grade(10d).build();
    }

    public static GradeDto createGradeDto(String postId){
        return GradeDto.builder().postId(postId).grade(10d).build();
    }
}
