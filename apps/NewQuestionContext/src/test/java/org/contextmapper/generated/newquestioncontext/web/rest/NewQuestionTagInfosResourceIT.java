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
import org.contextmapper.generated.newquestioncontext.domain.NewQuestionTagInfos;
import org.contextmapper.generated.newquestioncontext.repository.NewQuestionTagInfosRepository;
import org.contextmapper.generated.newquestioncontext.service.dto.NewQuestionTagInfosDTO;
import org.contextmapper.generated.newquestioncontext.service.mapper.NewQuestionTagInfosMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link NewQuestionTagInfosResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class NewQuestionTagInfosResourceIT {

    private static final Long DEFAULT_TAG_ID = 1L;
    private static final Long UPDATED_TAG_ID = 2L;

    private static final String DEFAULT_TAG_NAME = "AAAAAAAAAA";
    private static final String UPDATED_TAG_NAME = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/new-question-tag-infos";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private NewQuestionTagInfosRepository newQuestionTagInfosRepository;

    @Autowired
    private NewQuestionTagInfosMapper newQuestionTagInfosMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNewQuestionTagInfosMockMvc;

    private NewQuestionTagInfos newQuestionTagInfos;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NewQuestionTagInfos createEntity(EntityManager em) {
        NewQuestionTagInfos newQuestionTagInfos = new NewQuestionTagInfos().tagId(DEFAULT_TAG_ID).tagName(DEFAULT_TAG_NAME);
        return newQuestionTagInfos;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NewQuestionTagInfos createUpdatedEntity(EntityManager em) {
        NewQuestionTagInfos newQuestionTagInfos = new NewQuestionTagInfos().tagId(UPDATED_TAG_ID).tagName(UPDATED_TAG_NAME);
        return newQuestionTagInfos;
    }

    @BeforeEach
    public void initTest() {
        newQuestionTagInfos = createEntity(em);
    }

    @Test
    @Transactional
    void getAllNewQuestionTagInfos() throws Exception {
        // Initialize the database
        newQuestionTagInfosRepository.saveAndFlush(newQuestionTagInfos);

        // Get all the newQuestionTagInfosList
        restNewQuestionTagInfosMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(newQuestionTagInfos.getId().intValue())))
            .andExpect(jsonPath("$.[*].tagId").value(hasItem(DEFAULT_TAG_ID.intValue())))
            .andExpect(jsonPath("$.[*].tagName").value(hasItem(DEFAULT_TAG_NAME)));
    }

    @Test
    @Transactional
    void getNewQuestionTagInfos() throws Exception {
        // Initialize the database
        newQuestionTagInfosRepository.saveAndFlush(newQuestionTagInfos);

        // Get the newQuestionTagInfos
        restNewQuestionTagInfosMockMvc
            .perform(get(ENTITY_API_URL_ID, newQuestionTagInfos.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(newQuestionTagInfos.getId().intValue()))
            .andExpect(jsonPath("$.tagId").value(DEFAULT_TAG_ID.intValue()))
            .andExpect(jsonPath("$.tagName").value(DEFAULT_TAG_NAME));
    }

    @Test
    @Transactional
    void getNonExistingNewQuestionTagInfos() throws Exception {
        // Get the newQuestionTagInfos
        restNewQuestionTagInfosMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
