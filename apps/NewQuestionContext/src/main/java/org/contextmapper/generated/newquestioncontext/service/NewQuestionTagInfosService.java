package org.contextmapper.generated.newquestioncontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.newquestioncontext.domain.NewQuestionTagInfos;
import org.contextmapper.generated.newquestioncontext.repository.NewQuestionTagInfosRepository;
import org.contextmapper.generated.newquestioncontext.service.dto.NewQuestionTagInfosDTO;
import org.contextmapper.generated.newquestioncontext.service.mapper.NewQuestionTagInfosMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link NewQuestionTagInfos}.
 */
@Service
@Transactional
public class NewQuestionTagInfosService {

    private final Logger log = LoggerFactory.getLogger(NewQuestionTagInfosService.class);

    private final NewQuestionTagInfosRepository newQuestionTagInfosRepository;

    private final NewQuestionTagInfosMapper newQuestionTagInfosMapper;

    public NewQuestionTagInfosService(
        NewQuestionTagInfosRepository newQuestionTagInfosRepository,
        NewQuestionTagInfosMapper newQuestionTagInfosMapper
    ) {
        this.newQuestionTagInfosRepository = newQuestionTagInfosRepository;
        this.newQuestionTagInfosMapper = newQuestionTagInfosMapper;
    }

    /**
     * Save a newQuestionTagInfos.
     *
     * @param newQuestionTagInfosDTO the entity to save.
     * @return the persisted entity.
     */
    public NewQuestionTagInfosDTO save(NewQuestionTagInfosDTO newQuestionTagInfosDTO) {
        log.debug("Request to save NewQuestionTagInfos : {}", newQuestionTagInfosDTO);
        NewQuestionTagInfos newQuestionTagInfos = newQuestionTagInfosMapper.toEntity(newQuestionTagInfosDTO);
        newQuestionTagInfos = newQuestionTagInfosRepository.save(newQuestionTagInfos);
        return newQuestionTagInfosMapper.toDto(newQuestionTagInfos);
    }

    /**
     * Update a newQuestionTagInfos.
     *
     * @param newQuestionTagInfosDTO the entity to save.
     * @return the persisted entity.
     */
    public NewQuestionTagInfosDTO update(NewQuestionTagInfosDTO newQuestionTagInfosDTO) {
        log.debug("Request to update NewQuestionTagInfos : {}", newQuestionTagInfosDTO);
        NewQuestionTagInfos newQuestionTagInfos = newQuestionTagInfosMapper.toEntity(newQuestionTagInfosDTO);
        newQuestionTagInfos = newQuestionTagInfosRepository.save(newQuestionTagInfos);
        return newQuestionTagInfosMapper.toDto(newQuestionTagInfos);
    }

    /**
     * Partially update a newQuestionTagInfos.
     *
     * @param newQuestionTagInfosDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<NewQuestionTagInfosDTO> partialUpdate(NewQuestionTagInfosDTO newQuestionTagInfosDTO) {
        log.debug("Request to partially update NewQuestionTagInfos : {}", newQuestionTagInfosDTO);

        return newQuestionTagInfosRepository
            .findById(newQuestionTagInfosDTO.getId())
            .map(existingNewQuestionTagInfos -> {
                newQuestionTagInfosMapper.partialUpdate(existingNewQuestionTagInfos, newQuestionTagInfosDTO);

                return existingNewQuestionTagInfos;
            })
            .map(newQuestionTagInfosRepository::save)
            .map(newQuestionTagInfosMapper::toDto);
    }

    /**
     * Get all the newQuestionTagInfos.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<NewQuestionTagInfosDTO> findAll() {
        log.debug("Request to get all NewQuestionTagInfos");
        return newQuestionTagInfosRepository
            .findAll()
            .stream()
            .map(newQuestionTagInfosMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one newQuestionTagInfos by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NewQuestionTagInfosDTO> findOne(Long id) {
        log.debug("Request to get NewQuestionTagInfos : {}", id);
        return newQuestionTagInfosRepository.findById(id).map(newQuestionTagInfosMapper::toDto);
    }

    /**
     * Delete the newQuestionTagInfos by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete NewQuestionTagInfos : {}", id);
        newQuestionTagInfosRepository.deleteById(id);
    }
}
