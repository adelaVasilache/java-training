package javatraining.training.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import java.text.DecimalFormat;

/**
 * Created by Adela Vasilache on 25.04.2018
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GradeDto {
    @NotNull
    private Long postId;

    @NotNull
    private Double grade;
}
