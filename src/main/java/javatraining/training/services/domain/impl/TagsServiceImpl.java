package javatraining.training.services.domain.impl;

import javatraining.training.models.Post;
import javatraining.training.models.Tag;
import javatraining.training.repositories.TagRepository;
import javatraining.training.services.domain.TagsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Adela Vasilache on 24.04.2018
 */
@Service
public class TagsServiceImpl implements TagsService {
    public final TagRepository tagRepository;

    @Autowired
    public TagsServiceImpl(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    @Override
    public Set<Tag> addTagsThatDontExist(Set<Tag> tags){
        Set<String> tagNames = tags.stream().map(Tag::getName).collect(Collectors.toSet());
        Set<Tag> existingTags = tagRepository.findByNameIn(tagNames);
        Set<String> existingTagNames = existingTags.stream().map(Tag::getName).collect(Collectors.toSet());

        Set<Tag> tagsToSave = tags.stream().filter(
                tag -> !existingTagNames.contains(tag.getName())).collect(Collectors.toSet());
        tagRepository.save(tagsToSave);
        existingTags.addAll(tagsToSave);

        return existingTags;
    }
}
