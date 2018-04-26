package javatraining.training.dtos;

import com.fasterxml.jackson.annotation.JsonInclude;
import javatraining.training.models.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by Adela Vasilache on 24.04.2018
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CommentDto {
    @NotEmpty
    private String content;

    @NotNull
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long postId;

    private UserCommentDto user;
}
