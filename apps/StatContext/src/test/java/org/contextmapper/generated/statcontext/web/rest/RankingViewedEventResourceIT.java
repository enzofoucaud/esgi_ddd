package org.contextmapper.generated.statcontext.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.contextmapper.generated.statcontext.IntegrationTest;
import org.contextmapper.generated.statcontext.domain.RankingViewedEvent;
import org.contextmapper.generated.statcontext.repository.RankingViewedEventRepository;
import org.contextmapper.generated.statcontext.service.dto.RankingViewedEventDTO;
import org.contextmapper.generated.statcontext.service.mapper.RankingViewedEventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link RankingViewedEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RankingViewedEventResourceIT {

    private static final String ENTITY_API_URL = "/api/ranking-viewed-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private RankingViewedEventRepository rankingViewedEventRepository;

    @Autowired
    private RankingViewedEventMapper rankingViewedEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRankingViewedEventMockMvc;

    private RankingViewedEvent rankingViewedEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RankingViewedEvent createEntity(EntityManager em) {
        RankingViewedEvent rankingViewedEvent = new RankingViewedEvent();
        return rankingViewedEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RankingViewedEvent createUpdatedEntity(EntityManager em) {
        RankingViewedEvent rankingViewedEvent = new RankingViewedEvent();
        return rankingViewedEvent;
    }

    @BeforeEach
    public void initTest() {
        rankingViewedEvent = createEntity(em);
    }

    @Test
    @Transactional
    void getAllRankingViewedEvents() throws Exception {
        // Initialize the database
        rankingViewedEventRepository.saveAndFlush(rankingViewedEvent);

        // Get all the rankingViewedEventList
        restRankingViewedEventMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rankingViewedEvent.getId().intValue())));
    }

    @Test
    @Transactional
    void getRankingViewedEvent() throws Exception {
        // Initialize the database
        rankingViewedEventRepository.saveAndFlush(rankingViewedEvent);

        // Get the rankingViewedEvent
        restRankingViewedEventMockMvc
            .perform(get(ENTITY_API_URL_ID, rankingViewedEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(rankingViewedEvent.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingRankingViewedEvent() throws Exception {
        // Get the rankingViewedEvent
        restRankingViewedEventMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
