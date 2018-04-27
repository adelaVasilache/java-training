package javatraining.training.exceptions;

import lombok.NoArgsConstructor;

/**
 * Created by Adela Vasilache on 27.04.2018
 */
@NoArgsConstructor
public class UserRightsException extends Exception {
    private static final long serialVersionUID = 1L;

    public UserRightsException(String error) {
        super(error);
    }

}
