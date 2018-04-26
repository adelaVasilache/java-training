package javatraining.training.mappers;

import javatraining.training.dtos.PostDto;
import javatraining.training.models.Post;
import javatraining.training.models.User;
import org.joda.time.DateTime;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.Set;

/**
 * Created by Adela Vasilache on 23.04.2018
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class PostMapper {
    public static final PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "images", ignore = true)
    public abstract Post toPost(PostDto postDto);

    public Post copyProperties(PostDto postDto, User user){
        Post post = toPost(postDto);
        post.setCreated(new DateTime());
        post.setTags(TagMapper.INSTANCE.toTagList(postDto.getTags()));
        post.setImages(ImageMapper.INSTANCE.toImageList(postDto.getImages()));
        post.setUser(user);

        return post;
    }

    public abstract PostDto toPostDto(Post post);

    public abstract List<PostDto> toPostDtoList(List<Post> posts);

    public Page<PostDto> toPagePostDto(Page<Post> posts){
        return new PageImpl<>(toPostDtoList(posts.getContent()));
    }
}
