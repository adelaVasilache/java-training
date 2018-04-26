package javatraining.training.services.domain;

import javatraining.training.models.Image;

import java.util.Set;

/**
 * Created by Adela Vasilache on 25.04.2018
 */
public interface ImageService {
    void addImagesThatDontExist(Set<Image> images);
}
