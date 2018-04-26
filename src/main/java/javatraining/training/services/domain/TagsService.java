package javatraining.training.services.domain;

import javatraining.training.models.Tag;

import java.util.Set;

/**
 * Created by Adela Vasilache on 24.04.2018
 */
public interface TagsService {
    void addTagsThatDontExist(Set<Tag> tags);
}
