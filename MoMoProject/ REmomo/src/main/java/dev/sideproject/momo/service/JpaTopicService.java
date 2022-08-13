package dev.sideproject.momo.service;

import dev.sideproject.momo.domain.topic.TopicEntity;
import dev.sideproject.momo.dto.TopicDto;
import dev.sideproject.momo.domain.topic.TopicRepository;
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
public class JpaTopicService implements TopicService{
    private final TopicRepository topicRepository;





    @Override
    public TopicDto create(TopicDto dto) {
        TopicEntity topicEntity = new TopicEntity();
        topicEntity.setTitle(dto.getTitle());
        topicEntity = this.topicRepository.save(topicEntity);
        return new TopicDto(
                topicEntity.getTid(),
                topicEntity.getTitle(),
                topicEntity.getCreateAt()
        );
    }

    @Override
    public Collection<TopicDto> readAll() {
        List<TopicDto> topicDtoList = new ArrayList<>();

        this.topicRepository.findAll().forEach(topicEntity ->
                topicDtoList.add(
                        new TopicDto(
                                topicEntity.getTid(),
                                topicEntity.getTitle(),
                                topicEntity.getCreateAt()
                        )));
        return topicDtoList;
    }

    @Override
    public TopicDto read(Long id) {
        Optional<TopicEntity> topicEntityOptional = this.topicRepository.findById(id);
        if (topicEntityOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        TopicEntity topicEntity = topicEntityOptional.get();

        return new TopicDto(
                topicEntity.getTid(),
                topicEntity.getTitle(),
                topicEntity.getCreateAt()
        );
    }

    @Override
    public boolean update(Long id, TopicDto dto) {
        Optional<TopicEntity> topicEntityOptional = this.topicRepository.findById(id);

        if (topicEntityOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        TopicEntity topicEntity = topicEntityOptional.get();
        topicEntity.setTitle(dto.getTitle());

        this.topicRepository.save(topicEntity);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        Optional<TopicEntity> topicEntityOptional = this.topicRepository.findById(id);

        if (topicEntityOptional.isEmpty())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        TopicEntity topicEntity = topicEntityOptional.get();
        this.topicRepository.delete(topicEntity);
        return true;
    }



}
