package org.contextmapper.generated.statcontext.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.contextmapper.generated.statcontext.repository.RankingViewedEventRepository;
import org.contextmapper.generated.statcontext.service.RankingViewedEventService;
import org.contextmapper.generated.statcontext.service.dto.RankingViewedEventDTO;
import org.contextmapper.generated.statcontext.web.rest.errors.BadRequestAlertException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.ResponseUtil;

/**
 * REST controller for managing {@link org.contextmapper.generated.statcontext.domain.RankingViewedEvent}.
 */
@RestController
@RequestMapping("/api")
public class RankingViewedEventResource {

    private final Logger log = LoggerFactory.getLogger(RankingViewedEventResource.class);

    private final RankingViewedEventService rankingViewedEventService;

    private final RankingViewedEventRepository rankingViewedEventRepository;

    public RankingViewedEventResource(
        RankingViewedEventService rankingViewedEventService,
        RankingViewedEventRepository rankingViewedEventRepository
    ) {
        this.rankingViewedEventService = rankingViewedEventService;
        this.rankingViewedEventRepository = rankingViewedEventRepository;
    }

    /**
     * {@code GET  /ranking-viewed-events} : get all the rankingViewedEvents.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of rankingViewedEvents in body.
     */
    @GetMapping("/ranking-viewed-events")
    public List<RankingViewedEventDTO> getAllRankingViewedEvents() {
        log.debug("REST request to get all RankingViewedEvents");
        return rankingViewedEventService.findAll();
    }

    /**
     * {@code GET  /ranking-viewed-events/:id} : get the "id" rankingViewedEvent.
     *
     * @param id the id of the rankingViewedEventDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the rankingViewedEventDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ranking-viewed-events/{id}")
    public ResponseEntity<RankingViewedEventDTO> getRankingViewedEvent(@PathVariable Long id) {
        log.debug("REST request to get RankingViewedEvent : {}", id);
        Optional<RankingViewedEventDTO> rankingViewedEventDTO = rankingViewedEventService.findOne(id);
        return ResponseUtil.wrapOrNotFound(rankingViewedEventDTO);
    }
}
