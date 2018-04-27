package javatraining.training.exceptions;

import lombok.NoArgsConstructor;

/**
 * Created by Adela Vasilache on 27.04.2018
 */
@NoArgsConstructor
public class InvalidDataException extends Exception {
    private static final long serialVersionUID = 1L;

    public InvalidDataException(String error) {
        super(error);
    }
}
