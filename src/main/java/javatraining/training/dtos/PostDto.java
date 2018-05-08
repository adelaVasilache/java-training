package javatraining.training.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
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
    @Valid
    private Set<TagDto> tags;

    @Valid
    private Set<ImageDto> images;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Double grade;

}
