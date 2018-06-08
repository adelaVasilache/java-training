package javatraining.training.mappers;

import javatraining.training.dtos.LogDto;
import javatraining.training.models.Log;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

/**
 * Created by Adela Vasilache on 24.05.2018
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class LogMapper {
    public static final LogMapper INSTANCE = Mappers.getMapper(LogMapper.class);

    public abstract Log toLog(LogDto logDto);
}
