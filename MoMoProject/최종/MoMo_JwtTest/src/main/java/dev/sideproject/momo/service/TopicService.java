package dev.sideproject.momo.service;

import dev.sideproject.momo.entity.TopicEntity;
import dev.sideproject.momo.model.TopicDto;

import java.util.Collection;

public interface TopicService {

    TopicDto create(TopicDto dto);
    Collection<TopicDto> readAll();
    TopicDto read(Long id);
    boolean update(Long id, TopicDto dto);
    boolean delete(Long id);

}
