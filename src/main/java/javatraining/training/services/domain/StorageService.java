package javatraining.training.services.domain;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by Adela Vasilache on 08.05.2018
 */
public interface StorageService {
    void store(MultipartFile file, String postId);

}
