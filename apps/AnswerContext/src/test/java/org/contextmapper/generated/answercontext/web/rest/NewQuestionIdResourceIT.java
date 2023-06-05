package org.contextmapper.generated.answercontext.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.contextmapper.generated.answercontext.IntegrationTest;
import org.contextmapper.generated.answercontext.domain.NewQuestionId;
import org.contextmapper.generated.answercontext.repository.NewQuestionIdRepository;
import org.contextmapper.generated.answercontext.service.dto.NewQuestionIdDTO;
import org.contextmapper.generated.answercontext.service.mapper.NewQuestionIdMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link NewQuestionIdResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class NewQuestionIdResourceIT {

    private static final Long DEFAULT_QUESTION_ID = 1L;
    private static final Long UPDATED_QUESTION_ID = 2L;

    private static final String ENTITY_API_URL = "/api/new-question-ids";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private NewQuestionIdRepository newQuestionIdRepository;

    @Autowired
    private NewQuestionIdMapper newQuestionIdMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNewQuestionIdMockMvc;

    private NewQuestionId newQuestionId;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NewQuestionId createEntity(EntityManager em) {
        NewQuestionId newQuestionId = new NewQuestionId().questionId(DEFAULT_QUESTION_ID);
        return newQuestionId;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NewQuestionId createUpdatedEntity(EntityManager em) {
        NewQuestionId newQuestionId = new NewQuestionId().questionId(UPDATED_QUESTION_ID);
        return newQuestionId;
    }

    @BeforeEach
    public void initTest() {
        newQuestionId = createEntity(em);
    }

    @Test
    @Transactional
    void getAllNewQuestionIds() throws Exception {
        // Initialize the database
        newQuestionIdRepository.saveAndFlush(newQuestionId);

        // Get all the newQuestionIdList
        restNewQuestionIdMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(newQuestionId.getId().intValue())))
            .andExpect(jsonPath("$.[*].questionId").value(hasItem(DEFAULT_QUESTION_ID.intValue())));
    }

    @Test
    @Transactional
    void getNewQuestionId() throws Exception {
        // Initialize the database
        newQuestionIdRepository.saveAndFlush(newQuestionId);

        // Get the newQuestionId
        restNewQuestionIdMockMvc
            .perform(get(ENTITY_API_URL_ID, newQuestionId.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(newQuestionId.getId().intValue()))
            .andExpect(jsonPath("$.questionId").value(DEFAULT_QUESTION_ID.intValue()));
    }

    @Test
    @Transactional
    void getNonExistingNewQuestionId() throws Exception {
        // Get the newQuestionId
        restNewQuestionIdMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
