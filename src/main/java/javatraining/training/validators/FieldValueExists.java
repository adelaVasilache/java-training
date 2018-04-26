package javatraining.training.validators;

/**
 * Created by Adela Vasilache on 24.04.2018
 */
public interface FieldValueExists {
    boolean fieldValueExists(Object value, String fieldName) throws UnsupportedOperationException;
}
