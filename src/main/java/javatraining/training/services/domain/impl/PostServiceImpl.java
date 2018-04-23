package javatraining.training.services.domain.impl;

import javatraining.training.dtos.PostDto;
import javatraining.training.mappers.PostMapper;
import javatraining.training.models.Post;
import javatraining.training.services.domain.PostService;
import javatraining.training.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Adela Vasilache on 23.04.2018
 */
@Service
public class PostServiceImpl implements PostService {
    private final PostMapper postMapper;
    private final PostRepository postRepository;

    @Autowired
    public PostServiceImpl(PostMapper postMapper, PostRepository postRepository) {
        this.postRepository = postRepository;
        this.postMapper = PostMapper.INSTANCE;
    }

    @Override
    public PostDto addPost(PostDto postDto){
        Post post = postMapper.toPost(postDto);
        postRepository.save(post);
        return postDto;
    }
}
