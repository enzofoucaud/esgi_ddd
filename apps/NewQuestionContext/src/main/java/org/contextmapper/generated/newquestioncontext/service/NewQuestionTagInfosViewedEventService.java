package org.contextmapper.generated.newquestioncontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.newquestioncontext.domain.NewQuestionTagInfosViewedEvent;
import org.contextmapper.generated.newquestioncontext.repository.NewQuestionTagInfosViewedEventRepository;
import org.contextmapper.generated.newquestioncontext.service.dto.NewQuestionTagInfosViewedEventDTO;
import org.contextmapper.generated.newquestioncontext.service.mapper.NewQuestionTagInfosViewedEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link NewQuestionTagInfosViewedEvent}.
 */
@Service
@Transactional
public class NewQuestionTagInfosViewedEventService {

    private final Logger log = LoggerFactory.getLogger(NewQuestionTagInfosViewedEventService.class);

    private final NewQuestionTagInfosViewedEventRepository newQuestionTagInfosViewedEventRepository;

    private final NewQuestionTagInfosViewedEventMapper newQuestionTagInfosViewedEventMapper;

    public NewQuestionTagInfosViewedEventService(
        NewQuestionTagInfosViewedEventRepository newQuestionTagInfosViewedEventRepository,
        NewQuestionTagInfosViewedEventMapper newQuestionTagInfosViewedEventMapper
    ) {
        this.newQuestionTagInfosViewedEventRepository = newQuestionTagInfosViewedEventRepository;
        this.newQuestionTagInfosViewedEventMapper = newQuestionTagInfosViewedEventMapper;
    }

    /**
     * Save a newQuestionTagInfosViewedEvent.
     *
     * @param newQuestionTagInfosViewedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public NewQuestionTagInfosViewedEventDTO save(NewQuestionTagInfosViewedEventDTO newQuestionTagInfosViewedEventDTO) {
        log.debug("Request to save NewQuestionTagInfosViewedEvent : {}", newQuestionTagInfosViewedEventDTO);
        NewQuestionTagInfosViewedEvent newQuestionTagInfosViewedEvent = newQuestionTagInfosViewedEventMapper.toEntity(
            newQuestionTagInfosViewedEventDTO
        );
        newQuestionTagInfosViewedEvent = newQuestionTagInfosViewedEventRepository.save(newQuestionTagInfosViewedEvent);
        return newQuestionTagInfosViewedEventMapper.toDto(newQuestionTagInfosViewedEvent);
    }

    /**
     * Update a newQuestionTagInfosViewedEvent.
     *
     * @param newQuestionTagInfosViewedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public NewQuestionTagInfosViewedEventDTO update(NewQuestionTagInfosViewedEventDTO newQuestionTagInfosViewedEventDTO) {
        log.debug("Request to update NewQuestionTagInfosViewedEvent : {}", newQuestionTagInfosViewedEventDTO);
        NewQuestionTagInfosViewedEvent newQuestionTagInfosViewedEvent = newQuestionTagInfosViewedEventMapper.toEntity(
            newQuestionTagInfosViewedEventDTO
        );
        // no save call needed as we have no fields that can be updated
        return newQuestionTagInfosViewedEventMapper.toDto(newQuestionTagInfosViewedEvent);
    }

    /**
     * Partially update a newQuestionTagInfosViewedEvent.
     *
     * @param newQuestionTagInfosViewedEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<NewQuestionTagInfosViewedEventDTO> partialUpdate(NewQuestionTagInfosViewedEventDTO newQuestionTagInfosViewedEventDTO) {
        log.debug("Request to partially update NewQuestionTagInfosViewedEvent : {}", newQuestionTagInfosViewedEventDTO);

        return newQuestionTagInfosViewedEventRepository
            .findById(newQuestionTagInfosViewedEventDTO.getId())
            .map(existingNewQuestionTagInfosViewedEvent -> {
                newQuestionTagInfosViewedEventMapper.partialUpdate(
                    existingNewQuestionTagInfosViewedEvent,
                    newQuestionTagInfosViewedEventDTO
                );

                return existingNewQuestionTagInfosViewedEvent;
            })
            // .map(newQuestionTagInfosViewedEventRepository::save)
            .map(newQuestionTagInfosViewedEventMapper::toDto);
    }

    /**
     * Get all the newQuestionTagInfosViewedEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<NewQuestionTagInfosViewedEventDTO> findAll() {
        log.debug("Request to get all NewQuestionTagInfosViewedEvents");
        return newQuestionTagInfosViewedEventRepository
            .findAll()
            .stream()
            .map(newQuestionTagInfosViewedEventMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one newQuestionTagInfosViewedEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NewQuestionTagInfosViewedEventDTO> findOne(Long id) {
        log.debug("Request to get NewQuestionTagInfosViewedEvent : {}", id);
        return newQuestionTagInfosViewedEventRepository.findById(id).map(newQuestionTagInfosViewedEventMapper::toDto);
    }

    /**
     * Delete the newQuestionTagInfosViewedEvent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete NewQuestionTagInfosViewedEvent : {}", id);
        newQuestionTagInfosViewedEventRepository.deleteById(id);
    }
}
