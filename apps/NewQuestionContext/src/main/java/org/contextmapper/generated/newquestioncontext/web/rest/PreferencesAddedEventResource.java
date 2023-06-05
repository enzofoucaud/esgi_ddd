package org.contextmapper.generated.newquestioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.newquestioncontext.repository.PreferencesAddedEventRepository;
import org.contextmapper.generated.newquestioncontext.service.PreferencesAddedEventService;
import org.contextmapper.generated.newquestioncontext.service.dto.PreferencesAddedEventDTO;
import org.contextmapper.generated.newquestioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.newquestioncontext.domain.PreferencesAddedEvent}.
 */
@RestController
@RequestMapping("/api")
public class PreferencesAddedEventResource {

    private final Logger log = LoggerFactory.getLogger(PreferencesAddedEventResource.class);

    private final PreferencesAddedEventService preferencesAddedEventService;

    private final PreferencesAddedEventRepository preferencesAddedEventRepository;

    public PreferencesAddedEventResource(
        PreferencesAddedEventService preferencesAddedEventService,
        PreferencesAddedEventRepository preferencesAddedEventRepository
    ) {
        this.preferencesAddedEventService = preferencesAddedEventService;
        this.preferencesAddedEventRepository = preferencesAddedEventRepository;
    }

    /**
     * {@code GET  /preferences-added-events} : get all the preferencesAddedEvents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of preferencesAddedEvents in body.
     */
    @GetMapping("/preferences-added-events")
    public List<PreferencesAddedEventDTO> getAllPreferencesAddedEvents() {
        log.debug("REST request to get all PreferencesAddedEvents");
        return preferencesAddedEventService.findAll();
    }

    /**
     * {@code GET  /preferences-added-events/:id} : get the "id" preferencesAddedEvent.
     *
     * @param id the id of the preferencesAddedEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the preferencesAddedEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/preferences-added-events/{id}")
    public ResponseEntity<PreferencesAddedEventDTO> getPreferencesAddedEvent(@PathVariable Long id) {
        log.debug("REST request to get PreferencesAddedEvent : {}", id);
        Optional<PreferencesAddedEventDTO> preferencesAddedEventDTO = preferencesAddedEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(preferencesAddedEventDTO);
    }
}
