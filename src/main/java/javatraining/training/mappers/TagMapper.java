package javatraining.training.mappers;

import javatraining.training.dtos.TagDto;
import javatraining.training.models.Tag;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.Set;

/**
 * Created by Adela Vasilache on 24.04.2018
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class TagMapper {
    public static final TagMapper INSTANCE = Mappers.getMapper(TagMapper.class);

    public abstract Set<Tag> toTagList(Set<TagDto> tagsDtoList);
}
