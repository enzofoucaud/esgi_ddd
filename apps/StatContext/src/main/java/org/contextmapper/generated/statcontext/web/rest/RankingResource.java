package org.contextmapper.generated.statcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.statcontext.repository.RankingRepository;
import org.contextmapper.generated.statcontext.service.RankingService;
import org.contextmapper.generated.statcontext.service.dto.RankingDTO;
import org.contextmapper.generated.statcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.statcontext.domain.Ranking}.
 */
@RestController
@RequestMapping("/api")
public class RankingResource {

    private final Logger log = LoggerFactory.getLogger(RankingResource.class);

    private static final String ENTITY_NAME = "statContextRanking";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RankingService rankingService;

    private final RankingRepository rankingRepository;

    public RankingResource(RankingService rankingService, RankingRepository rankingRepository) {
        this.rankingService = rankingService;
        this.rankingRepository = rankingRepository;
    }

    /**
     * {@code POST  /rankings} : Create a new ranking.
     *
     * @param rankingDTO the rankingDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new rankingDTO, or with status {@code 400 (Bad Request)} if the ranking has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/rankings")
    public ResponseEntity<RankingDTO> createRanking(@RequestBody RankingDTO rankingDTO) throws URISyntaxException {
        log.debug("REST request to save Ranking : {}", rankingDTO);
        if (rankingDTO.getId() != null) {
            throw new BadRequestAlertException("A new ranking cannot already have an ID", ENTITY_NAME, "idexists");
        }
        RankingDTO result = rankingService.save(rankingDTO);
        return ResponseEntity
            .created(new URI("/api/rankings/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /rankings/:id} : Updates an existing ranking.
     *
     * @param id the id of the rankingDTO to save.
     * @param rankingDTO the rankingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rankingDTO,
     * or with status {@code 400 (Bad Request)} if the rankingDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the rankingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/rankings/{id}")
    public ResponseEntity<RankingDTO> updateRanking(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RankingDTO rankingDTO
    ) throws URISyntaxException {
        log.debug("REST request to update Ranking : {}, {}", id, rankingDTO);
        if (rankingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rankingDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rankingRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        RankingDTO result = rankingService.update(rankingDTO);
        return ResponseEntity
            .ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rankingDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code PATCH  /rankings/:id} : Partial updates given fields of an existing ranking, field will ignore if it is null
     *
     * @param id the id of the rankingDTO to save.
     * @param rankingDTO the rankingDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated rankingDTO,
     * or with status {@code 400 (Bad Request)} if the rankingDTO is not valid,
     * or with status {@code 404 (Not Found)} if the rankingDTO is not found,
     * or with status {@code 500 (Internal Server Error)} if the rankingDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/rankings/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public ResponseEntity<RankingDTO> partialUpdateRanking(
        @PathVariable(value = "id", required = false) final Long id,
        @RequestBody RankingDTO rankingDTO
    ) throws URISyntaxException {
        log.debug("REST request to partial update Ranking partially : {}, {}", id, rankingDTO);
        if (rankingDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, rankingDTO.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        if (!rankingRepository.existsById(id)) {
            throw new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound");
        }

        Optional<RankingDTO> result = rankingService.partialUpdate(rankingDTO);

        return ResponseUtil.wrapOrNotFound(
            result,
            HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, rankingDTO.getId().toString())
        );
    }

    /**
     * {@code GET  /rankings} : get all the rankings.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rankings in body.
     */
    @GetMapping("/rankings")
    public List<RankingDTO> getAllRankings() {
        log.debug("REST request to get all Rankings");
        return rankingService.findAll();
    }

    /**
     * {@code GET  /rankings/:id} : get the "id" ranking.
     *
     * @param id the id of the rankingDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rankingDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/rankings/{id}")
    public ResponseEntity<RankingDTO> getRanking(@PathVariable Long id) {
        log.debug("REST request to get Ranking : {}", id);
        Optional<RankingDTO> rankingDTO = rankingService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rankingDTO);
    }

    /**
     * {@code DELETE  /rankings/:id} : delete the "id" ranking.
     *
     * @param id the id of the rankingDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/rankings/{id}")
    public ResponseEntity<Void> deleteRanking(@PathVariable Long id) {
        log.debug("REST request to delete Ranking : {}", id);
        rankingService.delete(id);
        return ResponseEntity
            .noContent()
            .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
            .build();
    }
}
