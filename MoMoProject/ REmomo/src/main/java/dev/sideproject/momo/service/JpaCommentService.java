package dev.sideproject.momo.service;

import dev.sideproject.momo.domain.comment.CommentEntity;
import dev.sideproject.momo.domain.comment.CommentRepository;
import dev.sideproject.momo.domain.post.PostEntity;
import dev.sideproject.momo.domain.post.PostRepository;
import dev.sideproject.momo.domain.user.UserEntity;
import dev.sideproject.momo.domain.user.UserRepository;
import dev.sideproject.momo.dto.CommentDto;
import dev.sideproject.momo.dto.PostDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class JpaCommentService implements CommentService{
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final CommentRepository commentRepository;
    private final Logger logger = LoggerFactory.getLogger(JpaCommentService.class);



    @Override
    public CommentDto create(Long postId, CommentDto dto) {

        if (!this.postRepository.existsById(postId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        if (!this.userRepository.existsById(dto.getUserId()))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        PostEntity postEntity = this.postRepository.findById(postId).get();
        UserEntity userEntity = this.userRepository.findById(dto.getUserId()).get();

        CommentEntity commentEntity = new CommentEntity();
        commentEntity.setComment(dto.getComment());

        commentEntity.setWriter(userEntity);
//        commentEntity.setPostEntity(postEntity);  -> 원래는 이 방식이었지만 JSON 2차 배열방식으로 반환되길 원해서 수정
        commentEntity = this.commentRepository.save(commentEntity);

        /**
         JSON 2차 배열로 POST객체 값을 배열형태로 가져오고 싶음!
         Topic Entity의 PostEntitiyList가 계속 무한 참조됨으로
         Entity를 가져오지 말고 DTO에 담아서 가져오자
         * */
//        PostDto postDto = new PostDto();
//        postDto.setId(postEntity.getPid());
//        postDto.setContent(postEntity.getContent());
//        postDto.setUserId(postEntity.getWriter().getUid());
//        postDto.setTopicId(postEntity.getTopicEntity().getTid());
//        postDto.setTopicTitle(postEntity.getTopicEntity().getTitle());
//        postDto.setCreateAt(postEntity.getCreateAt());

        PostDto postDto = PostDto.builder()
                .id(postEntity.getPid())
                .content(postEntity.getContent())
                .userId(postEntity.getWriter().getUid())
                .topicId(postEntity.getTopicEntity().getTid())
                .topicTitle(postEntity.getTopicEntity().getTitle())
                .build();

        return new CommentDto(
                commentEntity.getCid(),
                commentEntity.getComment(),
                commentEntity.getCreateAt(),
                commentEntity.getWriter().getUid(),
                postDto
        );
    }

    @Override
    public Collection<CommentDto> readAll(Long postId) {
        Optional<PostEntity> postEntityOptional = this.postRepository.findById(postId);

        PostEntity postEntity = this.postRepository.findById(postId).get();

        PostDto postDto = PostDto.builder()
                .id(postEntity.getPid())
                .content(postEntity.getContent())
                .userId(postEntity.getWriter().getUid())
                .topicId(postEntity.getTopicEntity().getTid())
                .topicTitle(postEntity.getTopicEntity().getTitle())
                .build();


        List<CommentDto> commentDtoList = new ArrayList<>();
        this.commentRepository.findAll().forEach(
                commentEntity -> commentDtoList.add(
                        new CommentDto(
                                commentEntity.getCid(),
                                commentEntity.getComment(),
                                commentEntity.getCreateAt(),
                                commentEntity.getWriter().getUid(),
                                postDto
                        )
                )
        );

        return commentDtoList;
    }

    @Override
    public boolean update(Long postId, CommentDto dto, Long commentId) {

        if (!this.commentRepository.existsById(commentId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        CommentEntity commentEntity = this.commentRepository.findById(commentId).get();

        if (!commentEntity.getPostEntity().getPid().equals(postId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        if (!commentEntity.getWriter().getUid().equals(dto.getUserId()))
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);

        commentEntity.setComment(
                dto.getComment() == null ? commentEntity.getComment() : dto.getComment());
        this.commentRepository.save(commentEntity);

        return true;
    }

    @Override
    public boolean delete(Long postId, CommentDto dto, Long commentId) {

        if (!this.commentRepository.existsById(commentId))
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        CommentEntity commentEntity = this.commentRepository.findById(commentId).get();
        if (!commentEntity.getPostEntity().getPid().equals(postId))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        this.commentRepository.deleteById(commentId);

        return true;
    }



}
