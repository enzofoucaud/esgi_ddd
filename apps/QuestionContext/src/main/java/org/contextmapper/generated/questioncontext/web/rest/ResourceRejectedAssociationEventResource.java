package org.contextmapper.generated.questioncontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.questioncontext.repository.ResourceRejectedAssociationEventRepository;
import org.contextmapper.generated.questioncontext.service.ResourceRejectedAssociationEventService;
import org.contextmapper.generated.questioncontext.service.dto.ResourceRejectedAssociationEventDTO;
import org.contextmapper.generated.questioncontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.questioncontext.domain.ResourceRejectedAssociationEvent}.
 */
@RestController
@RequestMapping("/api")
public class ResourceRejectedAssociationEventResource {

    private final Logger log = LoggerFactory.getLogger(ResourceRejectedAssociationEventResource.class);

    private final ResourceRejectedAssociationEventService resourceRejectedAssociationEventService;

    private final ResourceRejectedAssociationEventRepository resourceRejectedAssociationEventRepository;

    public ResourceRejectedAssociationEventResource(
        ResourceRejectedAssociationEventService resourceRejectedAssociationEventService,
        ResourceRejectedAssociationEventRepository resourceRejectedAssociationEventRepository
    ) {
        this.resourceRejectedAssociationEventService = resourceRejectedAssociationEventService;
        this.resourceRejectedAssociationEventRepository = resourceRejectedAssociationEventRepository;
    }

    /**
     * {@code GET  /resource-rejected-association-events} : get all the resourceRejectedAssociationEvents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of resourceRejectedAssociationEvents in body.
     */
    @GetMapping("/resource-rejected-association-events")
    public List<ResourceRejectedAssociationEventDTO> getAllResourceRejectedAssociationEvents() {
        log.debug("REST request to get all ResourceRejectedAssociationEvents");
        return resourceRejectedAssociationEventService.findAll();
    }

    /**
     * {@code GET  /resource-rejected-association-events/:id} : get the "id" resourceRejectedAssociationEvent.
     *
     * @param id the id of the resourceRejectedAssociationEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the resourceRejectedAssociationEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/resource-rejected-association-events/{id}")
    public ResponseEntity<ResourceRejectedAssociationEventDTO> getResourceRejectedAssociationEvent(@PathVariable Long id) {
        log.debug("REST request to get ResourceRejectedAssociationEvent : {}", id);
        Optional<ResourceRejectedAssociationEventDTO> resourceRejectedAssociationEventDTO = resourceRejectedAssociationEventService.findOne(
            id
        );
        return ResponseUtil.wrapOrNotFound(resourceRejectedAssociationEventDTO);
    }
}
