package javatraining.training.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;

/**
 * Created by Adela Vasilache on 23.04.2018
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ImageDto {
    @NotEmpty
    private String fileName;

    @NotEmpty
    private String filePath;
}
