package org.contextmapper.generated.newquestioncontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.newquestioncontext.domain.NewQuestion;
import org.contextmapper.generated.newquestioncontext.repository.NewQuestionRepository;
import org.contextmapper.generated.newquestioncontext.service.dto.NewQuestionDTO;
import org.contextmapper.generated.newquestioncontext.service.mapper.NewQuestionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link NewQuestion}.
 */
@Service
@Transactional
public class NewQuestionService {

    private final Logger log = LoggerFactory.getLogger(NewQuestionService.class);

    private final NewQuestionRepository newQuestionRepository;

    private final NewQuestionMapper newQuestionMapper;

    public NewQuestionService(NewQuestionRepository newQuestionRepository, NewQuestionMapper newQuestionMapper) {
        this.newQuestionRepository = newQuestionRepository;
        this.newQuestionMapper = newQuestionMapper;
    }

    /**
     * Save a newQuestion.
     *
     * @param newQuestionDTO the entity to save.
     * @return the persisted entity.
     */
    public NewQuestionDTO save(NewQuestionDTO newQuestionDTO) {
        log.debug("Request to save NewQuestion : {}", newQuestionDTO);
        NewQuestion newQuestion = newQuestionMapper.toEntity(newQuestionDTO);
        newQuestion = newQuestionRepository.save(newQuestion);
        return newQuestionMapper.toDto(newQuestion);
    }

    /**
     * Update a newQuestion.
     *
     * @param newQuestionDTO the entity to save.
     * @return the persisted entity.
     */
    public NewQuestionDTO update(NewQuestionDTO newQuestionDTO) {
        log.debug("Request to update NewQuestion : {}", newQuestionDTO);
        NewQuestion newQuestion = newQuestionMapper.toEntity(newQuestionDTO);
        newQuestion = newQuestionRepository.save(newQuestion);
        return newQuestionMapper.toDto(newQuestion);
    }

    /**
     * Partially update a newQuestion.
     *
     * @param newQuestionDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<NewQuestionDTO> partialUpdate(NewQuestionDTO newQuestionDTO) {
        log.debug("Request to partially update NewQuestion : {}", newQuestionDTO);

        return newQuestionRepository
            .findById(newQuestionDTO.getId())
            .map(existingNewQuestion -> {
                newQuestionMapper.partialUpdate(existingNewQuestion, newQuestionDTO);

                return existingNewQuestion;
            })
            .map(newQuestionRepository::save)
            .map(newQuestionMapper::toDto);
    }

    /**
     * Get all the newQuestions.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<NewQuestionDTO> findAll() {
        log.debug("Request to get all NewQuestions");
        return newQuestionRepository.findAll().stream().map(newQuestionMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one newQuestion by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<NewQuestionDTO> findOne(Long id) {
        log.debug("Request to get NewQuestion : {}", id);
        return newQuestionRepository.findById(id).map(newQuestionMapper::toDto);
    }

    /**
     * Delete the newQuestion by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete NewQuestion : {}", id);
        newQuestionRepository.deleteById(id);
    }
}
