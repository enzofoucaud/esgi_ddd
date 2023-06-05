package org.contextmapper.generated.statcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.statcontext.repository.RankingEntryRepository;
import org.contextmapper.generated.statcontext.service.RankingEntryService;
import org.contextmapper.generated.statcontext.service.dto.RankingEntryDTO;
import org.contextmapper.generated.statcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.statcontext.domain.RankingEntry}.
 */
@RestController
@RequestMapping("/api")
public class RankingEntryResource {

    private final Logger log = LoggerFactory.getLogger(RankingEntryResource.class);

    private static final String ENTITY_NAME = "statContextRankingEntry";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RankingEntryService rankingEntryService;

    private final RankingEntryRepository rankingEntryRepository;

    public RankingEntryResource(RankingEntryService rankingEntryService, RankingEntryRepository rankingEntryRepository) {
        this.rankingEntryService = rankingEntryService;
        this.rankingEntryRepository = rankingEntryRepository;
    }

    /**
     * {@code POST  /ranking-entries} : Create a new rankingEntry.
     *
     * @param rankingEntryDTO the rankingEntryDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rankingEntryDTO, or with status {@code 400 (Bad Request)} if the rankingEntry has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ranking-entries")
    public ResponseEntity<RankingEntryDTO> createRankingEntry(@RequestBody RankingEntryDTO rankingEntryDTO) throws URISyntaxException {
        log.debug("REST request to save RankingEntry : {}", rankingEntryDTO);
        if (rankingEntryDTO.getId() != null) {
            throw new BadRequestAlertException("A new rankingEntry cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RankingEntryDTO result = rankingEntryService.save(rankingEntryDTO);
        return ResponseEntity
            .created(new URI("/api/ranking-entries/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ranking-entries/:id} : Updates an existing rankingEntry.
     *
     * @param id the id of the rankingEntryDTO to save.
     * @param rankingEntryDTO the rankingEntryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rankingEntryDTO,
     * or with status {@code 400 (Bad Request)} if the rankingEntryDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rankingEntryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ranking-entries/{id}")
    public ResponseEntity<RankingEntryDTO> updateRankingEntry(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RankingEntryDTO rankingEntryDTO
    ) throws URISyntaxException {
        log.debug("REST request to update RankingEntry : {}, {}", id, rankingEntryDTO);
        if (rankingEntryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rankingEntryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rankingEntryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RankingEntryDTO result = rankingEntryService.update(rankingEntryDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rankingEntryDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /ranking-entries/:id} : Partial updates given fields of an existing rankingEntry, field will ignore if it is null
     *
     * @param id the id of the rankingEntryDTO to save.
     * @param rankingEntryDTO the rankingEntryDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rankingEntryDTO,
     * or with status {@code 400 (Bad Request)} if the rankingEntryDTO is not valid,
     * or with status {@code 404 (Not Found)} if the rankingEntryDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the rankingEntryDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/ranking-entries/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RankingEntryDTO> partialUpdateRankingEntry(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RankingEntryDTO rankingEntryDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update RankingEntry partially : {}, {}", id, rankingEntryDTO);
        if (rankingEntryDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rankingEntryDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rankingEntryRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RankingEntryDTO> result = rankingEntryService.partialUpdate(rankingEntryDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rankingEntryDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /ranking-entries} : get all the rankingEntries.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rankingEntries in body.
     */
    @GetMapping("/ranking-entries")
    public List<RankingEntryDTO> getAllRankingEntries() {
        log.debug("REST request to get all RankingEntries");
        return rankingEntryService.findAll();
    }

    /**
     * {@code GET  /ranking-entries/:id} : get the "id" rankingEntry.
     *
     * @param id the id of the rankingEntryDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rankingEntryDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ranking-entries/{id}")
    public ResponseEntity<RankingEntryDTO> getRankingEntry(@PathVariable Long id) {
        log.debug("REST request to get RankingEntry : {}", id);
        Optional<RankingEntryDTO> rankingEntryDTO = rankingEntryService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rankingEntryDTO);
    }

    /**
     * {@code DELETE  /ranking-entries/:id} : delete the "id" rankingEntry.
     *
     * @param id the id of the rankingEntryDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ranking-entries/{id}")
    public ResponseEntity<Void> deleteRankingEntry(@PathVariable Long id) {
        log.debug("REST request to delete RankingEntry : {}", id);
        rankingEntryService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
