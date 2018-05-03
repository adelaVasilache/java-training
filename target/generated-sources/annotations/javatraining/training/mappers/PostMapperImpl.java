package javatraining.training.mappers;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javatraining.training.dtos.ImageDto;
import javatraining.training.dtos.PostDto;
import javatraining.training.dtos.TagDto;
import javatraining.training.models.Image;
import javatraining.training.models.Post;
import javatraining.training.models.Tag;
import javax.annotation.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2018-05-03T14:46:28+0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 1.8.0_161 (Oracle Corporation)"
)
public class PostMapperImpl extends PostMapper {

    @Override
    public Post toPost(PostDto postDto) {
        if ( postDto == null ) {
            return null;
        }

        Post post = new Post();

        post.setTitle( postDto.getTitle() );
        post.setContent( postDto.getContent() );
        post.setGrade( postDto.getGrade() );

        return post;
    }

    @Override
    public PostDto toPostDto(Post post) {
        if ( post == null ) {
            return null;
        }

        PostDto postDto = new PostDto();

        postDto.setTitle( post.getTitle() );
        postDto.setContent( post.getContent() );
        postDto.setTags( tagSetToTagDtoSet( post.getTags() ) );
        postDto.setImages( imageSetToImageDtoSet( post.getImages() ) );
        postDto.setGrade( post.getGrade() );

        return postDto;
    }

    @Override
    public List<PostDto> toPostDtoList(List<Post> posts) {
        if ( posts == null ) {
            return null;
        }

        List<PostDto> list = new ArrayList<PostDto>( posts.size() );
        for ( Post post : posts ) {
            list.add( toPostDto( post ) );
        }

        return list;
    }

    protected TagDto tagToTagDto(Tag tag) {
        if ( tag == null ) {
            return null;
        }

        TagDto tagDto = new TagDto();

        tagDto.setName( tag.getName() );

        return tagDto;
    }

    protected Set<TagDto> tagSetToTagDtoSet(Set<Tag> set) {
        if ( set == null ) {
            return null;
        }

        Set<TagDto> set1 = new HashSet<TagDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Tag tag : set ) {
            set1.add( tagToTagDto( tag ) );
        }

        return set1;
    }

    protected ImageDto imageToImageDto(Image image) {
        if ( image == null ) {
            return null;
        }

        ImageDto imageDto = new ImageDto();

        imageDto.setFileName( image.getFileName() );
        imageDto.setFilePath( image.getFilePath() );

        return imageDto;
    }

    protected Set<ImageDto> imageSetToImageDtoSet(Set<Image> set) {
        if ( set == null ) {
            return null;
        }

        Set<ImageDto> set1 = new HashSet<ImageDto>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Image image : set ) {
            set1.add( imageToImageDto( image ) );
        }

        return set1;
    }
}
