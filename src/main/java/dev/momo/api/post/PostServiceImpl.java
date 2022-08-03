package dev.momo.api.post;

import dev.momo.api.category.dto.CategoryDto;
import dev.momo.api.category.entity.Category;
import dev.momo.api.category.repository.CategoryRepository;
import dev.momo.api.global.exception.CategoryNotFoundException;
import dev.momo.api.global.exception.QuestionNotFoundException;
import dev.momo.api.post.dto.PostDto;
import dev.momo.api.post.entity.Post;
import dev.momo.api.post.repository.PostRepository;
import dev.momo.api.question.dto.QuestionDto;
import dev.momo.api.question.entity.Question;
import dev.momo.api.question.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService{
    private final Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);
    private final CategoryRepository categoryRepository;
    private final QuestionRepository questionRepository;
    private final PostRepository postRepository;

    @Override
    @Transactional
    public PostDto createPost(Long categoryId, Long questionId, PostDto dto) throws CategoryNotFoundException, QuestionNotFoundException {
      if (!categoryRepository.existsById(categoryId))
          throw new CategoryNotFoundException();

      if (!questionRepository.existsById(questionId))
          throw new QuestionNotFoundException();

      // 카테고리 정보 가져오기
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        Category reqCategory = categoryOptional.get();
        CategoryDto categoryDto = CategoryDto.builder()
                .categoryId(reqCategory.getCategoryId())
                .category(reqCategory.getCategory())
                .build();
      // 질문 정보 가져오기
        Optional<Question> questionOptional = questionRepository.findById(questionId);
        Question reqQuestion = questionOptional.get();
        QuestionDto questionDto = QuestionDto.builder()
                .questionId(reqQuestion.getQuestionId())
                .question(reqQuestion.getQuestion())
                .build();

      // 게시글 생성
        logger.info("post data : {}", dto.getPostId());
        Post reqPost = Post.builder()
                .postId(dto.getPostId())
                .post(dto.getPost())
                .isStatus(dto.isStatus())
                .isDelete(dto.isDelete())
                .build();

        Post resPost = postRepository.save(reqPost);
        PostDto postDto = PostDto.builder()
                .postId(resPost.getPostId())
                .post(resPost.getPost())
                .isStatus(resPost.isStatus())
                .isDelete(resPost.isDelete())
                .categoryDto(CategoryDto.builder()
                        .categoryId(reqCategory.getCategoryId())
                        .category(reqCategory.getCategory())
                        .build())
                .questionDto(QuestionDto.builder()
                        .questionId(reqQuestion.getQuestionId())
                        .question(reqQuestion.getQuestion())
                        .build())
                .build();
        return postDto;
    }

    @Override
    public List<PostDto> readAllPost(Long categoryId, Long questionId) {
        return null;
    }

    @Override
    public PostDto readPost(Long categoryId, Long questionId, Long postId) {
        return null;
    }

    @Override
    @Transactional
    public boolean updatePost(Long categoryId, Long questionId, Long postId, PostDto dto) {
        return false;
    }

    @Override //todo: 삭제되지 않고 상태 변경이 되도록 구현
    @Transactional
    public boolean deletePost(Long categoryId, Long questionId, Long postId, PostDto dto) {
        return false;
    }
}
