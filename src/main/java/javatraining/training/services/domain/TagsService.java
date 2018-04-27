package javatraining.training.services.domain;

import javatraining.training.models.Post;
import javatraining.training.models.Tag;

import java.util.Set;

/**
 * Created by Adela Vasilache on 24.04.2018
 */
public interface TagsService {
    Set<Tag> addTagsThatDontExist(Set<Tag> tags);
}
