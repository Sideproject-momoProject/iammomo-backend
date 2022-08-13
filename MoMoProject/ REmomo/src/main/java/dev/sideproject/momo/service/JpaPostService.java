package dev.sideproject.momo.service;

import dev.sideproject.momo.domain.post.PostEntity;
import dev.sideproject.momo.domain.topic.TopicEntity;
import dev.sideproject.momo.domain.user.UserEntity;
import dev.sideproject.momo.dto.PostDto;
import dev.sideproject.momo.domain.post.PostRepository;
import dev.sideproject.momo.domain.topic.TopicRepository;
import dev.sideproject.momo.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class JpaPostService implements PostService{
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final TopicRepository topicRepository;


    @Override
    public PostDto create(Long topicId, PostDto dto) {

        if (!this.topicRepository.existsById(topicId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        if (!this.userRepository.existsById(dto.getUserId()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        TopicEntity topicEntity = this.topicRepository.findById(topicId).get();
        UserEntity userEntity = this.userRepository.findById(dto.getUserId()).get();

        PostEntity postEntity = new PostEntity();
        postEntity.setContent(dto.getContent());

        postEntity.setWriter(userEntity);
        postEntity.setTopicEntity(topicEntity);
        postEntity = this.postRepository.save(postEntity);

        return new PostDto(
                postEntity.getPid(),
                postEntity.getContent(),
                postEntity.getWriter().getUid(),
                postEntity.getTopicEntity().getTid(),
                postEntity.getTopicEntity().getTitle(),
                postEntity.getCreateAt()
        );
    }

    @Override
    public PostDto read(Long topicId, Long postId) {
        if (!this.postRepository.existsById(postId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        PostEntity postEntity = this.postRepository.findById(postId).get();
        if (!postEntity.getTopicEntity().getTid().equals(topicId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);

        return new PostDto(
                postEntity.getPid(),
                postEntity.getContent(),
                postEntity.getWriter().getUid(),
                postEntity.getTopicEntity().getTid(),
                postEntity.getTopicEntity().getTitle(),
                postEntity.getCreateAt()
        );
    }

    @Override
    public Collection<PostDto> readAll(Long topicId) {
        Optional<TopicEntity> topicEntityOptional = this.topicRepository.findById(topicId);
        TopicEntity topicEntity = topicEntityOptional.get();
        List<PostDto> postDtoList = new ArrayList<>();
        topicEntity.getPostEntityList().forEach(
                postEntity -> postDtoList.add( new PostDto(
                        postEntity.getPid(),
                        postEntity.getContent(),
                        postEntity.getWriter().getUid(),
                        postEntity.getTopicEntity().getTid(),
                        postEntity.getTopicEntity().getTitle(),
                        postEntity.getCreateAt()
                        )
                )
        );
        return postDtoList;
    }

    @Override
    public boolean update(Long topicId, PostDto dto, Long postId) {

        if (!this.postRepository.existsById(postId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        PostEntity postEntity = this.postRepository.findById(postId).get();

        if (!postEntity.getTopicEntity().getTid().equals(topicId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        if (!postEntity.getWriter().getUid().equals(dto.getUserId()))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        postEntity.setContent(
                dto.getContent() == null ? postEntity.getContent() : dto.getContent());

        this.postRepository.save(postEntity);
        return true;
    }

    @Override
    public boolean delete(Long topicId, Long postId) {

        if (!this.postRepository.existsById(postId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        PostEntity postEntity = this.postRepository.findById(postId).get();
        if (!postEntity.getTopicEntity().getTid().equals(topicId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        this.postRepository.deleteById(postId);

        return true;
    }
}
