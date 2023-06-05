package org.contextmapper.generated.answercontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.answercontext.domain.NewQuestionId;
import org.contextmapper.generated.answercontext.repository.NewQuestionIdRepository;
import org.contextmapper.generated.answercontext.service.dto.NewQuestionIdDTO;
import org.contextmapper.generated.answercontext.service.mapper.NewQuestionIdMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link NewQuestionId}.
 */
@Service
@Transactional
public class NewQuestionIdService {

    private final Logger log = LoggerFactory.getLogger(NewQuestionIdService.class);

    private final NewQuestionIdRepository newQuestionIdRepository;

    private final NewQuestionIdMapper newQuestionIdMapper;

    public NewQuestionIdService(NewQuestionIdRepository newQuestionIdRepository, NewQuestionIdMapper newQuestionIdMapper) {
        this.newQuestionIdRepository = newQuestionIdRepository;
        this.newQuestionIdMapper = newQuestionIdMapper;
    }

    /**
     * Save a newQuestionId.
     *
     * @param newQuestionIdDTO the entity to save.
     * @return the persisted entity.
     */
    public NewQuestionIdDTO save(NewQuestionIdDTO newQuestionIdDTO) {
        log.debug("Request to save NewQuestionId : {}", newQuestionIdDTO);
        NewQuestionId newQuestionId = newQuestionIdMapper.toEntity(newQuestionIdDTO);
        newQuestionId = newQuestionIdRepository.save(newQuestionId);
        return newQuestionIdMapper.toDto(newQuestionId);
    }

    /**
     * Update a newQuestionId.
     *
     * @param newQuestionIdDTO the entity to save.
     * @return the persisted entity.
     */
    public NewQuestionIdDTO update(NewQuestionIdDTO newQuestionIdDTO) {
        log.debug("Request to update NewQuestionId : {}", newQuestionIdDTO);
        NewQuestionId newQuestionId = newQuestionIdMapper.toEntity(newQuestionIdDTO);
        newQuestionId = newQuestionIdRepository.save(newQuestionId);
        return newQuestionIdMapper.toDto(newQuestionId);
    }

    /**
     * Partially update a newQuestionId.
     *
     * @param newQuestionIdDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<NewQuestionIdDTO> partialUpdate(NewQuestionIdDTO newQuestionIdDTO) {
        log.debug("Request to partially update NewQuestionId : {}", newQuestionIdDTO);

        return newQuestionIdRepository
            .findById(newQuestionIdDTO.getId())
            .map(existingNewQuestionId -> {
                newQuestionIdMapper.partialUpdate(existingNewQuestionId, newQuestionIdDTO);

                return existingNewQuestionId;
            })
            .map(newQuestionIdRepository::save)
            .map(newQuestionIdMapper::toDto);
    }

    /**
     * Get all the newQuestionIds.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<NewQuestionIdDTO> findAll() {
        log.debug("Request to get all NewQuestionIds");
        return newQuestionIdRepository.findAll().stream().map(newQuestionIdMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one newQuestionId by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NewQuestionIdDTO> findOne(Long id) {
        log.debug("Request to get NewQuestionId : {}", id);
        return newQuestionIdRepository.findById(id).map(newQuestionIdMapper::toDto);
    }

    /**
     * Delete the newQuestionId by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete NewQuestionId : {}", id);
        newQuestionIdRepository.deleteById(id);
    }
}
