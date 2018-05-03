package javatraining.training.exceptions;

import lombok.NoArgsConstructor;

/**
 * Created by Adela Vasilache on 02.05.2018
 */
@NoArgsConstructor
public class GradeException extends Exception {
    private static final long serialVersionUID = 1L;

    public GradeException(String error) {
        super(error);
    }
}
