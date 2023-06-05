package org.contextmapper.generated.questioncontext.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.contextmapper.generated.questioncontext.IntegrationTest;
import org.contextmapper.generated.questioncontext.domain.ResourceRejectedAssociationEvent;
import org.contextmapper.generated.questioncontext.repository.ResourceRejectedAssociationEventRepository;
import org.contextmapper.generated.questioncontext.service.dto.ResourceRejectedAssociationEventDTO;
import org.contextmapper.generated.questioncontext.service.mapper.ResourceRejectedAssociationEventMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ResourceRejectedAssociationEventResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ResourceRejectedAssociationEventResourceIT {

    private static final String ENTITY_API_URL = "/api/resource-rejected-association-events";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ResourceRejectedAssociationEventRepository resourceRejectedAssociationEventRepository;

    @Autowired
    private ResourceRejectedAssociationEventMapper resourceRejectedAssociationEventMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restResourceRejectedAssociationEventMockMvc;

    private ResourceRejectedAssociationEvent resourceRejectedAssociationEvent;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResourceRejectedAssociationEvent createEntity(EntityManager em) {
        ResourceRejectedAssociationEvent resourceRejectedAssociationEvent = new ResourceRejectedAssociationEvent();
        return resourceRejectedAssociationEvent;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ResourceRejectedAssociationEvent createUpdatedEntity(EntityManager em) {
        ResourceRejectedAssociationEvent resourceRejectedAssociationEvent = new ResourceRejectedAssociationEvent();
        return resourceRejectedAssociationEvent;
    }

    @BeforeEach
    public void initTest() {
        resourceRejectedAssociationEvent = createEntity(em);
    }

    @Test
    @Transactional
    void getAllResourceRejectedAssociationEvents() throws Exception {
        // Initialize the database
        resourceRejectedAssociationEventRepository.saveAndFlush(resourceRejectedAssociationEvent);

        // Get all the resourceRejectedAssociationEventList
        restResourceRejectedAssociationEventMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(resourceRejectedAssociationEvent.getId().intValue())));
    }

    @Test
    @Transactional
    void getResourceRejectedAssociationEvent() throws Exception {
        // Initialize the database
        resourceRejectedAssociationEventRepository.saveAndFlush(resourceRejectedAssociationEvent);

        // Get the resourceRejectedAssociationEvent
        restResourceRejectedAssociationEventMockMvc
            .perform(get(ENTITY_API_URL_ID, resourceRejectedAssociationEvent.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(resourceRejectedAssociationEvent.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingResourceRejectedAssociationEvent() throws Exception {
        // Get the resourceRejectedAssociationEvent
        restResourceRejectedAssociationEventMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }
}
