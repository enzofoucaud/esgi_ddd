package org.contextmapper.generated.newquestioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.newquestioncontext.repository.NewQuestionRepository;
import org.contextmapper.generated.newquestioncontext.service.NewQuestionService;
import org.contextmapper.generated.newquestioncontext.service.dto.NewQuestionDTO;
import org.contextmapper.generated.newquestioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.newquestioncontext.domain.NewQuestion}.
 */
@RestController
@RequestMapping("/api")
public class NewQuestionResource {

    private final Logger log = LoggerFactory.getLogger(NewQuestionResource.class);

    private static final String ENTITY_NAME = "newQuestionContextNewQuestion";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final NewQuestionService newQuestionService;

    private final NewQuestionRepository newQuestionRepository;

    public NewQuestionResource(NewQuestionService newQuestionService, NewQuestionRepository newQuestionRepository) {
        this.newQuestionService = newQuestionService;
        this.newQuestionRepository = newQuestionRepository;
    }

    /**
     * {@code POST  /new-questions} : Create a new newQuestion.
     *
     * @param newQuestionDTO the newQuestionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new newQuestionDTO, or with status {@code 400 (Bad Request)} if the newQuestion has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/new-questions")
    public ResponseEntity<NewQuestionDTO> createNewQuestion(@RequestBody NewQuestionDTO newQuestionDTO) throws URISyntaxException {
        log.debug("REST request to save NewQuestion : {}", newQuestionDTO);
        if (newQuestionDTO.getId() != null) {
            throw new BadRequestAlertException("A new newQuestion cannot already have an ID", ENTITY_NAME, "idexists");
        }
        NewQuestionDTO result = newQuestionService.save(newQuestionDTO);
        return ResponseEntity
            .created(new URI("/api/new-questions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /new-questions/:id} : Updates an existing newQuestion.
     *
     * @param id the id of the newQuestionDTO to save.
     * @param newQuestionDTO the newQuestionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated newQuestionDTO,
     * or with status {@code 400 (Bad Request)} if the newQuestionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the newQuestionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/new-questions/{id}")
    public ResponseEntity<NewQuestionDTO> updateNewQuestion(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody NewQuestionDTO newQuestionDTO
    ) throws URISyntaxException {
        log.debug("REST request to update NewQuestion : {}, {}", id, newQuestionDTO);
        if (newQuestionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, newQuestionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!newQuestionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        NewQuestionDTO result = newQuestionService.update(newQuestionDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, newQuestionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /new-questions/:id} : Partial updates given fields of an existing newQuestion, field will ignore if it is null
     *
     * @param id the id of the newQuestionDTO to save.
     * @param newQuestionDTO the newQuestionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated newQuestionDTO,
     * or with status {@code 400 (Bad Request)} if the newQuestionDTO is not valid,
     * or with status {@code 404 (Not Found)} if the newQuestionDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the newQuestionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/new-questions/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<NewQuestionDTO> partialUpdateNewQuestion(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody NewQuestionDTO newQuestionDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update NewQuestion partially : {}, {}", id, newQuestionDTO);
        if (newQuestionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, newQuestionDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!newQuestionRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<NewQuestionDTO> result = newQuestionService.partialUpdate(newQuestionDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, newQuestionDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /new-questions} : get all the newQuestions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of newQuestions in body.
     */
    @GetMapping("/new-questions")
    public List<NewQuestionDTO> getAllNewQuestions() {
        log.debug("REST request to get all NewQuestions");
        return newQuestionService.findAll();
    }

    /**
     * {@code GET  /new-questions/:id} : get the "id" newQuestion.
     *
     * @param id the id of the newQuestionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the newQuestionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/new-questions/{id}")
    public ResponseEntity<NewQuestionDTO> getNewQuestion(@PathVariable Long id) {
        log.debug("REST request to get NewQuestion : {}", id);
        Optional<NewQuestionDTO> newQuestionDTO = newQuestionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(newQuestionDTO);
    }

    /**
     * {@code DELETE  /new-questions/:id} : delete the "id" newQuestion.
     *
     * @param id the id of the newQuestionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/new-questions/{id}")
    public ResponseEntity<Void> deleteNewQuestion(@PathVariable Long id) {
        log.debug("REST request to delete NewQuestion : {}", id);
        newQuestionService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
