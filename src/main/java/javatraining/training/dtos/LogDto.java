package javatraining.training.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.Date;

/**
 * Created by Adela Vasilache on 24.05.2018
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class LogDto {
    @NotEmpty
    private Date created;

    @NotEmpty
    private String event;

    @NotEmpty
    private String userId;

    @NotEmpty
    private String description;
}
