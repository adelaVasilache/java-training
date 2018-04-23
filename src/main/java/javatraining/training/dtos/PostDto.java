package javatraining.training.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by Adela Vasilache on 23.04.2018
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostDto {
    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    @NotNull
    private Set<TagDto> tags;

    private Set<ImageDto> images;

//    @NotNull
    UserDto user;

}
