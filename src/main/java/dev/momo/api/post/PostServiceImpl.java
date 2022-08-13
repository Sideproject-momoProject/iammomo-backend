package dev.momo.api.post;
import dev.momo.api.category.dto.CategoryDto;
import dev.momo.api.category.entity.Category;
import dev.momo.api.category.repository.CategoryRepository;
import dev.momo.api.global.exception.CategoryNotFoundException;
import dev.momo.api.global.exception.InvalidParamException;
import dev.momo.api.global.exception.PostNotFoundException;
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
import java.util.stream.Collectors;


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
                .post(dto.getPost())
                .isUpdated(dto.isUpdated())
                .isDeleted(dto.isDeleted())
                .build();

        Post resPost = postRepository.save(reqPost);
        PostDto postDto = PostDto.builder()
                .postId(resPost.getPostId())
                .post(resPost.getPost())
                .isUpdated(resPost.isUpdated())
                .isDeleted(resPost.isDeleted())
                .createAt(resPost.getCreateAt())
                .updateAt(resPost.getUpdateAt())
                .questionDto(QuestionDto.builder()
                        .questionId(reqQuestion.getQuestionId())
                        .question(reqQuestion.getQuestion())
                        .categoryDto(categoryDto)
                        .build())
                .build();
        return postDto;
    }

    @Override
    public List<PostDto> readAllPost(Long categoryId, Long questionId) throws CategoryNotFoundException, QuestionNotFoundException, PostNotFoundException {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (!categoryRepository.existsById(categoryId))
        throw new CategoryNotFoundException();

        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if (!questionRepository.existsById(questionId))
            throw new QuestionNotFoundException();

        Category resCategory = categoryOptional.get();
        Question resQuestion = questionOptional.get();

        CategoryDto categoryDto = CategoryDto.builder()
                .categoryId(resCategory.getCategoryId())
                .category(resCategory.getCategory())
                .build();

        QuestionDto questionDto = QuestionDto.builder()
                .questionId(resQuestion.getQuestionId())
                .question(resQuestion.getQuestion())
                .categoryDto(categoryDto)
                .build();

        if (postRepository.findAll().isEmpty())
            throw new PostNotFoundException();

        return postRepository.findAll().stream()
                .map(post -> PostDto.builder()
                        .postId(post.getPostId())
                        .post(post.getPost())
                        .questionDto(questionDto)
                        .isUpdated(post.isUpdated())
                        .isDeleted(post.isDeleted())
                        .build())
                .collect(Collectors.toList());
    }

    @Override
    public PostDto readPost(Long categoryId, Long questionId, Long postId) throws CategoryNotFoundException, QuestionNotFoundException {

        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (!categoryRepository.existsById(categoryId))
            throw new CategoryNotFoundException();

        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if (!questionRepository.existsById(questionId))
            throw new QuestionNotFoundException();

        Category resCategory = categoryOptional.get();
        Question resQuestion = questionOptional.get();

        CategoryDto categoryDto = CategoryDto.builder()
                .categoryId(resCategory.getCategoryId())
                .category(resCategory.getCategory())
                .build();

        QuestionDto questionDto = QuestionDto.builder()
                .questionId(resQuestion.getQuestionId())
                .question(resQuestion.getQuestion())
                .categoryDto(categoryDto)
                .build();

       Optional<Post> postOptional = postRepository.findById(postId);
       Post resPost = postOptional.get();

       PostDto postDto = PostDto.builder()
               .postId(resPost.getPostId())
               .post(resPost.getPost())
               .createAt(resPost.getCreateAt())
               .updateAt(resPost.getUpdateAt())
               .isUpdated(resPost.isUpdated())
               .isDeleted(resPost.isDeleted())
               .questionDto(questionDto)
               .build();
       return postDto;
    }

    @Override
    @Transactional
    public PostDto updatePost(Long categoryId, Long questionId, Long postId, PostDto dto) throws CategoryNotFoundException, QuestionNotFoundException, PostNotFoundException, InvalidParamException {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (!categoryRepository.existsById(categoryId))
            throw new CategoryNotFoundException();

        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if (!questionRepository.existsById(questionId))
            throw new QuestionNotFoundException();

        Category resCategory = categoryOptional.get();
        Question resQuestion = questionOptional.get();

        CategoryDto categoryDto = CategoryDto.builder()
                .categoryId(resCategory.getCategoryId())
                .category(resCategory.getCategory())
                .build();

        QuestionDto questionDto = QuestionDto.builder()
                .questionId(resQuestion.getQuestionId())
                .question(resQuestion.getQuestion())
                .categoryDto(categoryDto)
                .build();

        //파람 값으로 해당 게시글 확인
        Optional<Post> postOptional = postRepository.findById(postId);
        if (!postOptional.get().getPostId().equals(postId))
            throw new InvalidParamException();

        //최초 생성된 데이터의 update상태를 변경
        Post changeUpdatePost = Post.builder()
                .postId(postOptional.get().getPostId())
                .post(postOptional.get().getPost())
                .isUpdated(true)
                .isDeleted(postOptional.get().isDeleted())
                .build();
        this.postRepository.save(changeUpdatePost);

        //새로 생성된 수정데이터를 생성
        Post reqPost = Post.builder()
                .post(dto.getPost()==null? postOptional.get().getPost() : dto.getPost())
                .isUpdated(true)
                .build();
        Post resPost = postRepository.save(reqPost);

        PostDto postDto = PostDto.builder()
                .postId(resPost.getPostId())
                .post(resPost.getPost())
                .isUpdated(reqPost.isUpdated())
                .isDeleted(resPost.isDeleted())
                .createAt(resPost.getCreateAt())
                .updateAt(resPost.getUpdateAt())
                .questionDto(questionDto)
                .build();
        logger.info("post status Update3 : {}",String.valueOf(postDto.isUpdated()));
        return postDto;
    }

    @Override
    @Transactional
    public boolean deletePost(Long categoryId, Long questionId, Long postId) throws CategoryNotFoundException, QuestionNotFoundException, InvalidParamException {
        Optional<Category> categoryOptional = categoryRepository.findById(categoryId);
        if (!categoryRepository.existsById(categoryId))
            throw new CategoryNotFoundException();

        Optional<Question> questionOptional = questionRepository.findById(questionId);
        if (!questionRepository.existsById(questionId))
            throw new QuestionNotFoundException();

        Category resCategory = categoryOptional.get();
        Question resQuestion = questionOptional.get();

        CategoryDto categoryDto = CategoryDto.builder()
                .categoryId(resCategory.getCategoryId())
                .category(resCategory.getCategory())
                .build();

        QuestionDto questionDto = QuestionDto.builder()
                .questionId(resQuestion.getQuestionId())
                .question(resQuestion.getQuestion())
                .categoryDto(categoryDto)
                .build();

        //파람 값으로 해당 게시글 확인
        Optional<Post> postOptional = postRepository.findById(postId);
        if (!postOptional.get().getPostId().equals(postId))
            throw new InvalidParamException();

        //최초 생성된 데이터의 delete상태를 변경
        Post changeUpdatePost = Post.builder()
                .postId(postOptional.get().getPostId())
                .post(postOptional.get().getPost())
                .isUpdated(postOptional.get().isUpdated())
                .isDeleted(true)
                .build();
        this.postRepository.save(changeUpdatePost);
        return true;
    }
}
