package javatraining.training.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

/**
 * Created by Adela Vasilache on 23.04.2018
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDto {
    @Email
    @NotEmpty
    private String email;

    private String firstName;

    private String lastName;
}
