package javatraining.training.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Created by Adela Vasilache on 26.04.2018
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserCommentDto {
    private String email;

    private String firstName;

    private String lastName;
}
