package org.contextmapper.generated.newquestioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.newquestioncontext.repository.NewQuestionTagInfosViewedEventRepository;
import org.contextmapper.generated.newquestioncontext.service.NewQuestionTagInfosViewedEventService;
import org.contextmapper.generated.newquestioncontext.service.dto.NewQuestionTagInfosViewedEventDTO;
import org.contextmapper.generated.newquestioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.newquestioncontext.domain.NewQuestionTagInfosViewedEvent}.
 */
@RestController
@RequestMapping("/api")
public class NewQuestionTagInfosViewedEventResource {

    private final Logger log = LoggerFactory.getLogger(NewQuestionTagInfosViewedEventResource.class);

    private final NewQuestionTagInfosViewedEventService newQuestionTagInfosViewedEventService;

    private final NewQuestionTagInfosViewedEventRepository newQuestionTagInfosViewedEventRepository;

    public NewQuestionTagInfosViewedEventResource(
        NewQuestionTagInfosViewedEventService newQuestionTagInfosViewedEventService,
        NewQuestionTagInfosViewedEventRepository newQuestionTagInfosViewedEventRepository
    ) {
        this.newQuestionTagInfosViewedEventService = newQuestionTagInfosViewedEventService;
        this.newQuestionTagInfosViewedEventRepository = newQuestionTagInfosViewedEventRepository;
    }

    /**
     * {@code GET  /new-question-tag-infos-viewed-events} : get all the newQuestionTagInfosViewedEvents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of newQuestionTagInfosViewedEvents in body.
     */
    @GetMapping("/new-question-tag-infos-viewed-events")
    public List<NewQuestionTagInfosViewedEventDTO> getAllNewQuestionTagInfosViewedEvents() {
        log.debug("REST request to get all NewQuestionTagInfosViewedEvents");
        return newQuestionTagInfosViewedEventService.findAll();
    }

    /**
     * {@code GET  /new-question-tag-infos-viewed-events/:id} : get the "id" newQuestionTagInfosViewedEvent.
     *
     * @param id the id of the newQuestionTagInfosViewedEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the newQuestionTagInfosViewedEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/new-question-tag-infos-viewed-events/{id}")
    public ResponseEntity<NewQuestionTagInfosViewedEventDTO> getNewQuestionTagInfosViewedEvent(@PathVariable Long id) {
        log.debug("REST request to get NewQuestionTagInfosViewedEvent : {}", id);
        Optional<NewQuestionTagInfosViewedEventDTO> newQuestionTagInfosViewedEventDTO = newQuestionTagInfosViewedEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(newQuestionTagInfosViewedEventDTO);
    }
}
