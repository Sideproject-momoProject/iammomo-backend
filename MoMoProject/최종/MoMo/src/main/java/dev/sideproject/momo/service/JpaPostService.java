package dev.sideproject.momo.service;

import dev.sideproject.momo.entity.JwtUserEntity;
import dev.sideproject.momo.entity.PostEntity;
import dev.sideproject.momo.model.PostDto;
import dev.sideproject.momo.repository.JwtUserRepository;
import dev.sideproject.momo.repository.PostRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class JpaPostService implements PostService{
    private final PostRepository postRepository;
    private final JwtUserRepository userRepository;


    public JpaPostService(PostRepository postRepository, JwtUserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;

    }


    @Override
    public PostDto create(PostDto dto) {

        if (!this.userRepository.existsById(dto.getUserId()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        JwtUserEntity userEntity = this.userRepository.findById(dto.getUserId()).get();


        PostEntity postEntity = new PostEntity();
        postEntity.setContent(dto.getContent());
        postEntity.setTopic(dto.getTopic());
        postEntity.setWriter(userEntity);
        postEntity = this.postRepository.save(postEntity);


        return new PostDto(
                postEntity.getId(),
                postEntity.getContent(),
                postEntity.getTopic(),
                postEntity.getWriter().getUId(),
                postEntity.getCreateAt()

        );
    }

    @Override
    public PostDto read(Long topicId) {
        Optional<PostEntity> postEntityOptional = this.postRepository.findById(topicId);
        if (postEntityOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        PostEntity postEntity = postEntityOptional.get();



        return new PostDto(
                postEntity.getId(),
                postEntity.getContent(),
                postEntity.getTopic(),
                postEntity.getWriter().getUId(),
                postEntity.getCreateAt()
        );
    }

    @Override
    public Collection<PostDto> readAll() {
        List<PostDto> postDtoList = new ArrayList<>();

        this.postRepository.findAll().forEach(postEntity ->
                postDtoList.add(
                        new PostDto(
                                postEntity.getId(),
                                postEntity.getContent(),
                                postEntity.getTopic(),
                                postEntity.getWriter().getUId(),
                                postEntity.getCreateAt()
                        )
                ));
        return postDtoList;
    }

    @Override
    public boolean update(PostDto dto, Long postId) {
        if (!this.postRepository.existsById(postId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        PostEntity postEntity = this.postRepository.findById(postId).get();

        if (!postEntity.getWriter().getId().equals(dto.getUserId()))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        postEntity.setContent(
                dto.getContent() == null ? postEntity.getContent() : dto.getContent());
        postEntity.setTopic(dto.getTopic());

        this.postRepository.save(postEntity);
        return true;
    }

    @Override
    public boolean delete(Long postId) {
      Optional<PostEntity> postEntityOptional = this.postRepository.findById(postId);

      if (postEntityOptional.isEmpty())
          throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      PostEntity postEntity = postEntityOptional.get();
      this.postRepository.delete(postEntity);
        return true;
    }
}
