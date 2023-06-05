package org.contextmapper.generated.evaluationcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.evaluationcontext.repository.EarnPointRepository;
import org.contextmapper.generated.evaluationcontext.service.EarnPointService;
import org.contextmapper.generated.evaluationcontext.service.dto.EarnPointDTO;
import org.contextmapper.generated.evaluationcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.evaluationcontext.domain.EarnPoint}.
 */
@RestController
@RequestMapping("/api")
public class EarnPointResource {

    private final Logger log = LoggerFactory.getLogger(EarnPointResource.class);

    private static final String ENTITY_NAME = "evaluationContextEarnPoint";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EarnPointService earnPointService;

    private final EarnPointRepository earnPointRepository;

    public EarnPointResource(EarnPointService earnPointService, EarnPointRepository earnPointRepository) {
        this.earnPointService = earnPointService;
        this.earnPointRepository = earnPointRepository;
    }

    /**
     * {@code POST  /earn-points} : Create a new earnPoint.
     *
     * @param earnPointDTO the earnPointDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new earnPointDTO, or with status {@code 400 (Bad Request)} if the earnPoint has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/earn-points")
    public ResponseEntity<EarnPointDTO> createEarnPoint(@RequestBody EarnPointDTO earnPointDTO) throws URISyntaxException {
        log.debug("REST request to save EarnPoint : {}", earnPointDTO);
        if (earnPointDTO.getId() != null) {
            throw new BadRequestAlertException("A new earnPoint cannot already have an ID", ENTITY_NAME, "idexists");
        }
        EarnPointDTO result = earnPointService.save(earnPointDTO);
        return ResponseEntity
            .created(new URI("/api/earn-points/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /earn-points/:id} : Updates an existing earnPoint.
     *
     * @param id the id of the earnPointDTO to save.
     * @param earnPointDTO the earnPointDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated earnPointDTO,
     * or with status {@code 400 (Bad Request)} if the earnPointDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the earnPointDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/earn-points/{id}")
    public ResponseEntity<EarnPointDTO> updateEarnPoint(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody EarnPointDTO earnPointDTO
    ) throws URISyntaxException {
        log.debug("REST request to update EarnPoint : {}, {}", id, earnPointDTO);
        if (earnPointDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, earnPointDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!earnPointRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        EarnPointDTO result = earnPointService.update(earnPointDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, earnPointDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /earn-points/:id} : Partial updates given fields of an existing earnPoint, field will ignore if it is null
     *
     * @param id the id of the earnPointDTO to save.
     * @param earnPointDTO the earnPointDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated earnPointDTO,
     * or with status {@code 400 (Bad Request)} if the earnPointDTO is not valid,
     * or with status {@code 404 (Not Found)} if the earnPointDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the earnPointDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/earn-points/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<EarnPointDTO> partialUpdateEarnPoint(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody EarnPointDTO earnPointDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update EarnPoint partially : {}, {}", id, earnPointDTO);
        if (earnPointDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, earnPointDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!earnPointRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<EarnPointDTO> result = earnPointService.partialUpdate(earnPointDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, earnPointDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /earn-points} : get all the earnPoints.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of earnPoints in body.
     */
    @GetMapping("/earn-points")
    public List<EarnPointDTO> getAllEarnPoints() {
        log.debug("REST request to get all EarnPoints");
        return earnPointService.findAll();
    }

    /**
     * {@code GET  /earn-points/:id} : get the "id" earnPoint.
     *
     * @param id the id of the earnPointDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the earnPointDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/earn-points/{id}")
    public ResponseEntity<EarnPointDTO> getEarnPoint(@PathVariable Long id) {
        log.debug("REST request to get EarnPoint : {}", id);
        Optional<EarnPointDTO> earnPointDTO = earnPointService.findOne(id);
        return ResponseUtil.wrapOrNotFound(earnPointDTO);
    }

    /**
     * {@code DELETE  /earn-points/:id} : delete the "id" earnPoint.
     *
     * @param id the id of the earnPointDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/earn-points/{id}")
    public ResponseEntity<Void> deleteEarnPoint(@PathVariable Long id) {
        log.debug("REST request to delete EarnPoint : {}", id);
        earnPointService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
