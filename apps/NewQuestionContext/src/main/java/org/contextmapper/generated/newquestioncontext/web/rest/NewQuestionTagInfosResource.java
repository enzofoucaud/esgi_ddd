package org.contextmapper.generated.newquestioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.newquestioncontext.repository.NewQuestionTagInfosRepository;
import org.contextmapper.generated.newquestioncontext.service.NewQuestionTagInfosService;
import org.contextmapper.generated.newquestioncontext.service.dto.NewQuestionTagInfosDTO;
import org.contextmapper.generated.newquestioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.newquestioncontext.domain.NewQuestionTagInfos}.
 */
@RestController
@RequestMapping("/api")
public class NewQuestionTagInfosResource {

    private final Logger log = LoggerFactory.getLogger(NewQuestionTagInfosResource.class);

    private final NewQuestionTagInfosService newQuestionTagInfosService;

    private final NewQuestionTagInfosRepository newQuestionTagInfosRepository;

    public NewQuestionTagInfosResource(
        NewQuestionTagInfosService newQuestionTagInfosService,
        NewQuestionTagInfosRepository newQuestionTagInfosRepository
    ) {
        this.newQuestionTagInfosService = newQuestionTagInfosService;
        this.newQuestionTagInfosRepository = newQuestionTagInfosRepository;
    }

    /**
     * {@code GET  /new-question-tag-infos} : get all the newQuestionTagInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of newQuestionTagInfos in body.
     */
    @GetMapping("/new-question-tag-infos")
    public List<NewQuestionTagInfosDTO> getAllNewQuestionTagInfos() {
        log.debug("REST request to get all NewQuestionTagInfos");
        return newQuestionTagInfosService.findAll();
    }

    /**
     * {@code GET  /new-question-tag-infos/:id} : get the "id" newQuestionTagInfos.
     *
     * @param id the id of the newQuestionTagInfosDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the newQuestionTagInfosDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/new-question-tag-infos/{id}")
    public ResponseEntity<NewQuestionTagInfosDTO> getNewQuestionTagInfos(@PathVariable Long id) {
        log.debug("REST request to get NewQuestionTagInfos : {}", id);
        Optional<NewQuestionTagInfosDTO> newQuestionTagInfosDTO = newQuestionTagInfosService.findOne(id);
        return ResponseUtil.wrapOrNotFound(newQuestionTagInfosDTO);
    }
}
