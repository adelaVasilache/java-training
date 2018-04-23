package javatraining.training.mappers;

import javatraining.training.dtos.PostDto;
import javatraining.training.models.Post;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * Created by Adela Vasilache on 23.04.2018
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class PostMapper {
    public static final PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    public abstract Post toPost(PostDto post);
}
