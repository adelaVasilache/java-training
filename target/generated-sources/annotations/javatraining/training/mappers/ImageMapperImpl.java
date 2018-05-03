package javatraining.training.mappers;

import java.util.HashSet;
import java.util.Set;
import javatraining.training.dtos.ImageDto;
import javatraining.training.models.Image;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-05-03T14:46:28+0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_161 (Oracle Corporation)"
)
public class ImageMapperImpl extends ImageMapper {

    @Override
    public Set<Image> toImageList(Set<ImageDto> imageDtoList) {
        if ( imageDtoList == null ) {
            return null;
        }

        Set<Image> set = new HashSet<Image>( Math.max( (int) ( imageDtoList.size() / .75f ) + 1, 16 ) );
        for ( ImageDto imageDto : imageDtoList ) {
            set.add( imageDtoToImage( imageDto ) );
        }

        return set;
    }

    protected Image imageDtoToImage(ImageDto imageDto) {
        if ( imageDto == null ) {
            return null;
        }

        Image image = new Image();

        image.setFileName( imageDto.getFileName() );
        image.setFilePath( imageDto.getFilePath() );

        return image;
    }
}
