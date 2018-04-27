package javatraining.training.mappers;

import javatraining.training.dtos.PostDto;
import javatraining.training.models.Post;
import javatraining.training.models.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.util.Date;
import java.util.List;

/**
 * Created by Adela Vasilache on 23.04.2018
 */
@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
public abstract class PostMapper {
    public static final PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "images", ignore = true)
    public abstract Post toPost(PostDto postDto);

    public Post setProperties(PostDto postDto, User user, Long postId) {
        Post post = toPost(postDto);
        post.setCreated(new Date());
        post.setTags(TagMapper.INSTANCE.toTagList(postDto.getTags()));
        post.setImages(ImageMapper.INSTANCE.toImageList(postDto.getImages()));
        post.setUser(user);
        post.setId(postId);

        return post;
    }

    public abstract PostDto toPostDto(Post post);

    public abstract List<PostDto> toPostDtoList(List<Post> posts);

    public Page<PostDto> toPagePostDto(Page<Post> posts) {
        return new PageImpl<>(toPostDtoList(posts.getContent()));
    }
}
