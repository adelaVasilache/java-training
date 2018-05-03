package javatraining.training.mappers;

import javatraining.training.dtos.ImageDto;
import javatraining.training.models.Image;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.Set;

/**
 * Created by Adela Vasilache on 24.04.2018
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class ImageMapper {
    public static final ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);

    public abstract Set<Image> toImageList(Set<ImageDto> imageDtoList);
}
