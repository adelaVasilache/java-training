package javatraining.training.mappers;

import javatraining.training.dtos.GradeDto;
import javatraining.training.models.Grade;
import javatraining.training.models.Post;
import javatraining.training.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.Date;

/**
 * Created by Adela Vasilache on 03.05.2018
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class GradeMapper {
    public static final GradeMapper INSTANCE = Mappers.getMapper(GradeMapper.class);

    public abstract void copyProperties(GradeDto commentDto, @MappingTarget Grade comment);

    public Grade toGrade(GradeDto gradeDto, Post post, User user){
        Grade grade = new Grade();
        copyProperties(gradeDto, grade);
        grade.setCreated(new Date());
        grade.setPost(post);
        grade.setUser(user);
        return grade;
    }
}
