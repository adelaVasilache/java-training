package javatraining.training.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;
import java.text.DecimalFormat;
import java.util.Date;

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
    private String postId;

    @NotNull
    @Range(min = 0, max = 10)
    private Double grade;

    private UserCommentDto user;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private Date created;

    public String toString(){
        return "postId: " + this.postId + "grade:" + this.grade;
    }
}
