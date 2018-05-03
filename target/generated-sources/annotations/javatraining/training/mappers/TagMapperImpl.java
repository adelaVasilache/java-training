package javatraining.training.mappers;

import java.util.HashSet;
import java.util.Set;
import javatraining.training.dtos.TagDto;
import javatraining.training.models.Tag;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-05-03T14:46:28+0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_161 (Oracle Corporation)"
)
public class TagMapperImpl extends TagMapper {

    @Override
    public Set<Tag> toTagList(Set<TagDto> tagsDtoList) {
        if ( tagsDtoList == null ) {
            return null;
        }

        Set<Tag> set = new HashSet<Tag>( Math.max( (int) ( tagsDtoList.size() / .75f ) + 1, 16 ) );
        for ( TagDto tagDto : tagsDtoList ) {
            set.add( tagDtoToTag( tagDto ) );
        }

        return set;
    }

    protected Tag tagDtoToTag(TagDto tagDto) {
        if ( tagDto == null ) {
            return null;
        }

        Tag tag = new Tag();

        tag.setName( tagDto.getName() );

        return tag;
    }
}
