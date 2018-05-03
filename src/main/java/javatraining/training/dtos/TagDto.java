package javatraining.training.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Pattern;


/**
 * Created by Adela Vasilache on 23.04.2018
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class TagDto {
    @NotEmpty
    @Pattern(regexp = "^\\S*$")
    private String name;
}
