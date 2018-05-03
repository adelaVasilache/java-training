package javatraining.training.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

/**
 * Created by Adela Vasilache on 26.04.2018
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class ErrorDto {
    private HttpStatus status;

    private String message;

}
