package javatraining.training.services.domain.impl;

import javatraining.training.models.Image;
import javatraining.training.repositories.ImageRepository;
import javatraining.training.services.domain.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by Adela Vasilache on 25.04.2018
 */
@Service
public class ImageServiceImpl implements ImageService {
    private final ImageRepository imageRepository;

    @Autowired
    public ImageServiceImpl(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    @Override
    public Set<Image> addImagesThatDontExist(Set<Image> images) {
        Set<String> imageNames = images.stream().map(Image::getFileName).collect(Collectors.toSet());
        Set<Image> existingImages = imageRepository.findByFileNameIn(imageNames);
        Set<String> existingImageNames = existingImages.stream().map(Image::getFileName).collect(Collectors.toSet());

        Set<Image> imagesToSave = images.stream().filter(
                image -> !existingImageNames.contains(image.getFileName())).collect(Collectors.toSet());
        imageRepository.save(imagesToSave);
        existingImages.addAll(imagesToSave);

        return existingImages;
    }
}
