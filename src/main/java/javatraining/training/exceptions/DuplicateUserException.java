package javatraining.training.exceptions;

import lombok.NoArgsConstructor;

/**
 * Created by Adela Vasilache on 24.04.2018
 */
@NoArgsConstructor
public class DuplicateUserException extends Exception {
    private static final long serialVersionUID = 1L;

    public DuplicateUserException(String error) {
        super(error);
    }
}
