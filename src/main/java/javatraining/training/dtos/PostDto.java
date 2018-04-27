package javatraining.training.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * Created by Adela Vasilache on 23.04.2018
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class PostDto {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long postId;

    @NotEmpty
    private String title;

    @NotEmpty
    private String content;

    @NotNull
    private Set<TagDto> tags;

    private Set<ImageDto> images;

    private Double grade;

}
