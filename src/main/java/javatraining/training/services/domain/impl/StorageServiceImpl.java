package javatraining.training.services.domain.impl;

import javatraining.training.exceptions.StorageException;
import javatraining.training.services.domain.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * Created by Adela Vasilache on 08.05.2018
 */
@Service
public class StorageServiceImpl implements StorageService{
    private final String root = "/opt/tomcat/webapps/media";
    private final MessageSource messageSource;

    @Autowired
    public StorageServiceImpl(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Override
    public void store(MultipartFile file, String postId) {
        Path dirPathObj = Paths.get(root, postId);
        if(!Files.exists(dirPathObj))
        {
            try{
                Files.createDirectory(dirPathObj);
            } catch(IOException ex){
                throw new StorageException(messageSource.getMessage("error.create.directory",
                        null, null));
            }
        }
        String filename = StringUtils.cleanPath(file.getOriginalFilename());
        try {
            if (file.isEmpty()) {
                throw new StorageException(messageSource.getMessage("error.empty.file",
                        null, null) + filename);
            }
            if (filename.contains("..")) {
                throw new StorageException(
                        messageSource.getMessage("error.relative.path", null, null)
                                + filename);
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, dirPathObj.resolve(filename),
                        StandardCopyOption.REPLACE_EXISTING);
            }
        }
        catch (IOException e) {
            throw new StorageException(messageSource.getMessage("error.store.file",
                    null, null) + filename, e);
        }
    }

}
