package org.contextmapper.generated.newquestioncontext.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.contextmapper.generated.newquestioncontext.IntegrationTest;
import org.contextmapper.generated.newquestioncontext.domain.NewQuestionTagInfosViewedEvent;
import org.contextmapper.generated.newquestioncontext.repository.NewQuestionTagInfosViewedEventRepository;
import org.contextmapper.generated.newquestioncontext.service.dto.NewQuestionTagInfosViewedEventDTO;
import org.contextmapper.generated.newquestioncontext.service.mapper.NewQuestionTagInfosViewedEventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link NewQuestionTagInfosViewedEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class NewQuestionTagInfosViewedEventResourceIT {

    private static final String ENTITY_API_URL = "/api/new-question-tag-infos-viewed-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private NewQuestionTagInfosViewedEventRepository newQuestionTagInfosViewedEventRepository;

    @Autowired
    private NewQuestionTagInfosViewedEventMapper newQuestionTagInfosViewedEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNewQuestionTagInfosViewedEventMockMvc;

    private NewQuestionTagInfosViewedEvent newQuestionTagInfosViewedEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NewQuestionTagInfosViewedEvent createEntity(EntityManager em) {
        NewQuestionTagInfosViewedEvent newQuestionTagInfosViewedEvent = new NewQuestionTagInfosViewedEvent();
        return newQuestionTagInfosViewedEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NewQuestionTagInfosViewedEvent createUpdatedEntity(EntityManager em) {
        NewQuestionTagInfosViewedEvent newQuestionTagInfosViewedEvent = new NewQuestionTagInfosViewedEvent();
        return newQuestionTagInfosViewedEvent;
    }

    @BeforeEach
    public void initTest() {
        newQuestionTagInfosViewedEvent = createEntity(em);
    }

    @Test
    @Transactional
    void getAllNewQuestionTagInfosViewedEvents() throws Exception {
        // Initialize the database
        newQuestionTagInfosViewedEventRepository.saveAndFlush(newQuestionTagInfosViewedEvent);

        // Get all the newQuestionTagInfosViewedEventList
        restNewQuestionTagInfosViewedEventMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(newQuestionTagInfosViewedEvent.getId().intValue())));
    }

    @Test
    @Transactional
    void getNewQuestionTagInfosViewedEvent() throws Exception {
        // Initialize the database
        newQuestionTagInfosViewedEventRepository.saveAndFlush(newQuestionTagInfosViewedEvent);

        // Get the newQuestionTagInfosViewedEvent
        restNewQuestionTagInfosViewedEventMockMvc
            .perform(get(ENTITY_API_URL_ID, newQuestionTagInfosViewedEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(newQuestionTagInfosViewedEvent.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingNewQuestionTagInfosViewedEvent() throws Exception {
        // Get the newQuestionTagInfosViewedEvent
        restNewQuestionTagInfosViewedEventMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
