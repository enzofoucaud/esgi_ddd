package org.contextmapper.generated.statcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.statcontext.repository.ViewRankingCommandRepository;
import org.contextmapper.generated.statcontext.service.ViewRankingCommandService;
import org.contextmapper.generated.statcontext.service.dto.ViewRankingCommandDTO;
import org.contextmapper.generated.statcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.statcontext.domain.ViewRankingCommand}.
 */
@RestController
@RequestMapping("/api")
public class ViewRankingCommandResource {

    private final Logger log = LoggerFactory.getLogger(ViewRankingCommandResource.class);

    private static final String ENTITY_NAME = "statContextViewRankingCommand";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ViewRankingCommandService viewRankingCommandService;

    private final ViewRankingCommandRepository viewRankingCommandRepository;

    public ViewRankingCommandResource(
        ViewRankingCommandService viewRankingCommandService,
        ViewRankingCommandRepository viewRankingCommandRepository
    ) {
        this.viewRankingCommandService = viewRankingCommandService;
        this.viewRankingCommandRepository = viewRankingCommandRepository;
    }

    /**
     * {@code POST  /view-ranking-commands} : Create a new viewRankingCommand.
     *
     * @param viewRankingCommandDTO the viewRankingCommandDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new viewRankingCommandDTO, or with status {@code 400 (Bad Request)} if the viewRankingCommand has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/view-ranking-commands")
    public ResponseEntity<ViewRankingCommandDTO> createViewRankingCommand(@RequestBody ViewRankingCommandDTO viewRankingCommandDTO)
        throws URISyntaxException {
        log.debug("REST request to save ViewRankingCommand : {}", viewRankingCommandDTO);
        if (viewRankingCommandDTO.getId() != null) {
            throw new BadRequestAlertException("A new viewRankingCommand cannot already have an ID", ENTITY_NAME, "idexists");
        }
        ViewRankingCommandDTO result = viewRankingCommandService.save(viewRankingCommandDTO);
        return ResponseEntity
            .created(new URI("/api/view-ranking-commands/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /view-ranking-commands/:id} : Updates an existing viewRankingCommand.
     *
     * @param id the id of the viewRankingCommandDTO to save.
     * @param viewRankingCommandDTO the viewRankingCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated viewRankingCommandDTO,
     * or with status {@code 400 (Bad Request)} if the viewRankingCommandDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the viewRankingCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/view-ranking-commands/{id}")
    public ResponseEntity<ViewRankingCommandDTO> updateViewRankingCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ViewRankingCommandDTO viewRankingCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to update ViewRankingCommand : {}, {}", id, viewRankingCommandDTO);
        if (viewRankingCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, viewRankingCommandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!viewRankingCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        ViewRankingCommandDTO result = viewRankingCommandService.update(viewRankingCommandDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, viewRankingCommandDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /view-ranking-commands/:id} : Partial updates given fields of an existing viewRankingCommand, field will ignore if it is null
     *
     * @param id the id of the viewRankingCommandDTO to save.
     * @param viewRankingCommandDTO the viewRankingCommandDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated viewRankingCommandDTO,
     * or with status {@code 400 (Bad Request)} if the viewRankingCommandDTO is not valid,
     * or with status {@code 404 (Not Found)} if the viewRankingCommandDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the viewRankingCommandDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/view-ranking-commands/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<ViewRankingCommandDTO> partialUpdateViewRankingCommand(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody ViewRankingCommandDTO viewRankingCommandDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update ViewRankingCommand partially : {}, {}", id, viewRankingCommandDTO);
        if (viewRankingCommandDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, viewRankingCommandDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!viewRankingCommandRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<ViewRankingCommandDTO> result = viewRankingCommandService.partialUpdate(viewRankingCommandDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, viewRankingCommandDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /view-ranking-commands} : get all the viewRankingCommands.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of viewRankingCommands in body.
     */
    @GetMapping("/view-ranking-commands")
    public List<ViewRankingCommandDTO> getAllViewRankingCommands() {
        log.debug("REST request to get all ViewRankingCommands");
        return viewRankingCommandService.findAll();
    }

    /**
     * {@code GET  /view-ranking-commands/:id} : get the "id" viewRankingCommand.
     *
     * @param id the id of the viewRankingCommandDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the viewRankingCommandDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/view-ranking-commands/{id}")
    public ResponseEntity<ViewRankingCommandDTO> getViewRankingCommand(@PathVariable Long id) {
        log.debug("REST request to get ViewRankingCommand : {}", id);
        Optional<ViewRankingCommandDTO> viewRankingCommandDTO = viewRankingCommandService.findOne(id);
        return ResponseUtil.wrapOrNotFound(viewRankingCommandDTO);
    }

    /**
     * {@code DELETE  /view-ranking-commands/:id} : delete the "id" viewRankingCommand.
     *
     * @param id the id of the viewRankingCommandDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/view-ranking-commands/{id}")
    public ResponseEntity<Void> deleteViewRankingCommand(@PathVariable Long id) {
        log.debug("REST request to delete ViewRankingCommand : {}", id);
        viewRankingCommandService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
