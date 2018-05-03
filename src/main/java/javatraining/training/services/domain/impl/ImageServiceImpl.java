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
        Set<String> imagePaths = images.stream().map(Image::getFilePath).collect(Collectors.toSet());
        Set<Image> existingImages = imageRepository.findByFilePathIn(imagePaths);
        Set<String> existingImagePaths = existingImages.stream().map(Image::getFilePath).collect(Collectors.toSet());

        Set<Image> imagesToSave = images.stream().filter(
                image -> !existingImagePaths.contains(image.getFilePath())).collect(Collectors.toSet());
        imageRepository.save(imagesToSave);
        existingImages.addAll(imagesToSave);

        return existingImages;
    }
}
