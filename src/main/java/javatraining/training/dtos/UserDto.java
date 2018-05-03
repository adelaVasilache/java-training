package javatraining.training.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Pattern;


/**
 * Created by Adela Vasilache on 23.04.2018
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDto extends BasicUserDto {
    @Pattern(regexp = "^[a-zA-Z\\s]+$")
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z\\s]+$")
    private String lastName;
}
