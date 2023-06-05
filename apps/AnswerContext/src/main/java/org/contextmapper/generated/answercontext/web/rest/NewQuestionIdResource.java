package org.contextmapper.generated.answercontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.answercontext.repository.NewQuestionIdRepository;
import org.contextmapper.generated.answercontext.service.NewQuestionIdService;
import org.contextmapper.generated.answercontext.service.dto.NewQuestionIdDTO;
import org.contextmapper.generated.answercontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.answercontext.domain.NewQuestionId}.
 */
@RestController
@RequestMapping("/api")
public class NewQuestionIdResource {

    private final Logger log = LoggerFactory.getLogger(NewQuestionIdResource.class);

    private final NewQuestionIdService newQuestionIdService;

    private final NewQuestionIdRepository newQuestionIdRepository;

    public NewQuestionIdResource(NewQuestionIdService newQuestionIdService, NewQuestionIdRepository newQuestionIdRepository) {
        this.newQuestionIdService = newQuestionIdService;
        this.newQuestionIdRepository = newQuestionIdRepository;
    }

    /**
     * {@code GET  /new-question-ids} : get all the newQuestionIds.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of newQuestionIds in body.
     */
    @GetMapping("/new-question-ids")
    public List<NewQuestionIdDTO> getAllNewQuestionIds() {
        log.debug("REST request to get all NewQuestionIds");
        return newQuestionIdService.findAll();
    }

    /**
     * {@code GET  /new-question-ids/:id} : get the "id" newQuestionId.
     *
     * @param id the id of the newQuestionIdDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the newQuestionIdDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/new-question-ids/{id}")
    public ResponseEntity<NewQuestionIdDTO> getNewQuestionId(@PathVariable Long id) {
        log.debug("REST request to get NewQuestionId : {}", id);
        Optional<NewQuestionIdDTO> newQuestionIdDTO = newQuestionIdService.findOne(id);
        return ResponseUtil.wrapOrNotFound(newQuestionIdDTO);
    }
}
