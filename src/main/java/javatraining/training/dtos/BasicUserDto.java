package javatraining.training.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;

/**
 * Created by Adela Vasilache on 24.04.2018
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BasicUserDto {
    @Email
    @NotEmpty
    private String email;

    //at least one uppercase letter, one lowercase letter, one digit, one special character and minimum 8 length
    @Pattern(regexp = "^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$")
    private String password;
}
