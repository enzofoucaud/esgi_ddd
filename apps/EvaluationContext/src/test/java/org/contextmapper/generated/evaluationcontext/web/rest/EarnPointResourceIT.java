package org.contextmapper.generated.evaluationcontext.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.contextmapper.generated.evaluationcontext.IntegrationTest;
import org.contextmapper.generated.evaluationcontext.domain.EarnPoint;
import org.contextmapper.generated.evaluationcontext.domain.enumeration.Difficulty;
import org.contextmapper.generated.evaluationcontext.domain.enumeration.UserLevel;
import org.contextmapper.generated.evaluationcontext.repository.EarnPointRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.EarnPointDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.EarnPointMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link EarnPointResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class EarnPointResourceIT {

    private static final Integer DEFAULT_SCORE_EVOLUTION = 1;
    private static final Integer UPDATED_SCORE_EVOLUTION = 2;

    private static final Difficulty DEFAULT_DIFFICULTY = Difficulty.BEGINNER;
    private static final Difficulty UPDATED_DIFFICULTY = Difficulty.INTERMEDIATE;

    private static final UserLevel DEFAULT_USER_LEVEL = UserLevel.NEW;
    private static final UserLevel UPDATED_USER_LEVEL = UserLevel.REGULAR;

    private static final String ENTITY_API_URL = "/api/earn-points";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private EarnPointRepository earnPointRepository;

    @Autowired
    private EarnPointMapper earnPointMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEarnPointMockMvc;

    private EarnPoint earnPoint;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EarnPoint createEntity(EntityManager em) {
        EarnPoint earnPoint = new EarnPoint()
            .scoreEvolution(DEFAULT_SCORE_EVOLUTION)
            .difficulty(DEFAULT_DIFFICULTY)
            .userLevel(DEFAULT_USER_LEVEL);
        return earnPoint;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static EarnPoint createUpdatedEntity(EntityManager em) {
        EarnPoint earnPoint = new EarnPoint()
            .scoreEvolution(UPDATED_SCORE_EVOLUTION)
            .difficulty(UPDATED_DIFFICULTY)
            .userLevel(UPDATED_USER_LEVEL);
        return earnPoint;
    }

    @BeforeEach
    public void initTest() {
        earnPoint = createEntity(em);
    }

    @Test
    @Transactional
    void createEarnPoint() throws Exception {
        int databaseSizeBeforeCreate = earnPointRepository.findAll().size();
        // Create the EarnPoint
        EarnPointDTO earnPointDTO = earnPointMapper.toDto(earnPoint);
        restEarnPointMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(earnPointDTO)))
            .andExpect(status().isCreated());

        // Validate the EarnPoint in the database
        List<EarnPoint> earnPointList = earnPointRepository.findAll();
        assertThat(earnPointList).hasSize(databaseSizeBeforeCreate + 1);
        EarnPoint testEarnPoint = earnPointList.get(earnPointList.size() - 1);
        assertThat(testEarnPoint.getScoreEvolution()).isEqualTo(DEFAULT_SCORE_EVOLUTION);
        assertThat(testEarnPoint.getDifficulty()).isEqualTo(DEFAULT_DIFFICULTY);
        assertThat(testEarnPoint.getUserLevel()).isEqualTo(DEFAULT_USER_LEVEL);
    }

    @Test
    @Transactional
    void createEarnPointWithExistingId() throws Exception {
        // Create the EarnPoint with an existing ID
        earnPoint.setId(1L);
        EarnPointDTO earnPointDTO = earnPointMapper.toDto(earnPoint);

        int databaseSizeBeforeCreate = earnPointRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restEarnPointMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(earnPointDTO)))
            .andExpect(status().isBadRequest());

        // Validate the EarnPoint in the database
        List<EarnPoint> earnPointList = earnPointRepository.findAll();
        assertThat(earnPointList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllEarnPoints() throws Exception {
        // Initialize the database
        earnPointRepository.saveAndFlush(earnPoint);

        // Get all the earnPointList
        restEarnPointMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(earnPoint.getId().intValue())))
            .andExpect(jsonPath("$.[*].scoreEvolution").value(hasItem(DEFAULT_SCORE_EVOLUTION)))
            .andExpect(jsonPath("$.[*].difficulty").value(hasItem(DEFAULT_DIFFICULTY.toString())))
            .andExpect(jsonPath("$.[*].userLevel").value(hasItem(DEFAULT_USER_LEVEL.toString())));
    }

    @Test
    @Transactional
    void getEarnPoint() throws Exception {
        // Initialize the database
        earnPointRepository.saveAndFlush(earnPoint);

        // Get the earnPoint
        restEarnPointMockMvc
            .perform(get(ENTITY_API_URL_ID, earnPoint.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(earnPoint.getId().intValue()))
            .andExpect(jsonPath("$.scoreEvolution").value(DEFAULT_SCORE_EVOLUTION))
            .andExpect(jsonPath("$.difficulty").value(DEFAULT_DIFFICULTY.toString()))
            .andExpect(jsonPath("$.userLevel").value(DEFAULT_USER_LEVEL.toString()));
    }

    @Test
    @Transactional
    void getNonExistingEarnPoint() throws Exception {
        // Get the earnPoint
        restEarnPointMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingEarnPoint() throws Exception {
        // Initialize the database
        earnPointRepository.saveAndFlush(earnPoint);

        int databaseSizeBeforeUpdate = earnPointRepository.findAll().size();

        // Update the earnPoint
        EarnPoint updatedEarnPoint = earnPointRepository.findById(earnPoint.getId()).get();
        // Disconnect from session so that the updates on updatedEarnPoint are not directly saved in db
        em.detach(updatedEarnPoint);
        updatedEarnPoint.scoreEvolution(UPDATED_SCORE_EVOLUTION).difficulty(UPDATED_DIFFICULTY).userLevel(UPDATED_USER_LEVEL);
        EarnPointDTO earnPointDTO = earnPointMapper.toDto(updatedEarnPoint);

        restEarnPointMockMvc
            .perform(
                put(ENTITY_API_URL_ID, earnPointDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(earnPointDTO))
            )
            .andExpect(status().isOk());

        // Validate the EarnPoint in the database
        List<EarnPoint> earnPointList = earnPointRepository.findAll();
        assertThat(earnPointList).hasSize(databaseSizeBeforeUpdate);
        EarnPoint testEarnPoint = earnPointList.get(earnPointList.size() - 1);
        assertThat(testEarnPoint.getScoreEvolution()).isEqualTo(UPDATED_SCORE_EVOLUTION);
        assertThat(testEarnPoint.getDifficulty()).isEqualTo(UPDATED_DIFFICULTY);
        assertThat(testEarnPoint.getUserLevel()).isEqualTo(UPDATED_USER_LEVEL);
    }

    @Test
    @Transactional
    void putNonExistingEarnPoint() throws Exception {
        int databaseSizeBeforeUpdate = earnPointRepository.findAll().size();
        earnPoint.setId(count.incrementAndGet());

        // Create the EarnPoint
        EarnPointDTO earnPointDTO = earnPointMapper.toDto(earnPoint);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEarnPointMockMvc
            .perform(
                put(ENTITY_API_URL_ID, earnPointDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(earnPointDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EarnPoint in the database
        List<EarnPoint> earnPointList = earnPointRepository.findAll();
        assertThat(earnPointList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchEarnPoint() throws Exception {
        int databaseSizeBeforeUpdate = earnPointRepository.findAll().size();
        earnPoint.setId(count.incrementAndGet());

        // Create the EarnPoint
        EarnPointDTO earnPointDTO = earnPointMapper.toDto(earnPoint);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEarnPointMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(earnPointDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EarnPoint in the database
        List<EarnPoint> earnPointList = earnPointRepository.findAll();
        assertThat(earnPointList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamEarnPoint() throws Exception {
        int databaseSizeBeforeUpdate = earnPointRepository.findAll().size();
        earnPoint.setId(count.incrementAndGet());

        // Create the EarnPoint
        EarnPointDTO earnPointDTO = earnPointMapper.toDto(earnPoint);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEarnPointMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(earnPointDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the EarnPoint in the database
        List<EarnPoint> earnPointList = earnPointRepository.findAll();
        assertThat(earnPointList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateEarnPointWithPatch() throws Exception {
        // Initialize the database
        earnPointRepository.saveAndFlush(earnPoint);

        int databaseSizeBeforeUpdate = earnPointRepository.findAll().size();

        // Update the earnPoint using partial update
        EarnPoint partialUpdatedEarnPoint = new EarnPoint();
        partialUpdatedEarnPoint.setId(earnPoint.getId());

        partialUpdatedEarnPoint.userLevel(UPDATED_USER_LEVEL);

        restEarnPointMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEarnPoint.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEarnPoint))
            )
            .andExpect(status().isOk());

        // Validate the EarnPoint in the database
        List<EarnPoint> earnPointList = earnPointRepository.findAll();
        assertThat(earnPointList).hasSize(databaseSizeBeforeUpdate);
        EarnPoint testEarnPoint = earnPointList.get(earnPointList.size() - 1);
        assertThat(testEarnPoint.getScoreEvolution()).isEqualTo(DEFAULT_SCORE_EVOLUTION);
        assertThat(testEarnPoint.getDifficulty()).isEqualTo(DEFAULT_DIFFICULTY);
        assertThat(testEarnPoint.getUserLevel()).isEqualTo(UPDATED_USER_LEVEL);
    }

    @Test
    @Transactional
    void fullUpdateEarnPointWithPatch() throws Exception {
        // Initialize the database
        earnPointRepository.saveAndFlush(earnPoint);

        int databaseSizeBeforeUpdate = earnPointRepository.findAll().size();

        // Update the earnPoint using partial update
        EarnPoint partialUpdatedEarnPoint = new EarnPoint();
        partialUpdatedEarnPoint.setId(earnPoint.getId());

        partialUpdatedEarnPoint.scoreEvolution(UPDATED_SCORE_EVOLUTION).difficulty(UPDATED_DIFFICULTY).userLevel(UPDATED_USER_LEVEL);

        restEarnPointMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedEarnPoint.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedEarnPoint))
            )
            .andExpect(status().isOk());

        // Validate the EarnPoint in the database
        List<EarnPoint> earnPointList = earnPointRepository.findAll();
        assertThat(earnPointList).hasSize(databaseSizeBeforeUpdate);
        EarnPoint testEarnPoint = earnPointList.get(earnPointList.size() - 1);
        assertThat(testEarnPoint.getScoreEvolution()).isEqualTo(UPDATED_SCORE_EVOLUTION);
        assertThat(testEarnPoint.getDifficulty()).isEqualTo(UPDATED_DIFFICULTY);
        assertThat(testEarnPoint.getUserLevel()).isEqualTo(UPDATED_USER_LEVEL);
    }

    @Test
    @Transactional
    void patchNonExistingEarnPoint() throws Exception {
        int databaseSizeBeforeUpdate = earnPointRepository.findAll().size();
        earnPoint.setId(count.incrementAndGet());

        // Create the EarnPoint
        EarnPointDTO earnPointDTO = earnPointMapper.toDto(earnPoint);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEarnPointMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, earnPointDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(earnPointDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EarnPoint in the database
        List<EarnPoint> earnPointList = earnPointRepository.findAll();
        assertThat(earnPointList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchEarnPoint() throws Exception {
        int databaseSizeBeforeUpdate = earnPointRepository.findAll().size();
        earnPoint.setId(count.incrementAndGet());

        // Create the EarnPoint
        EarnPointDTO earnPointDTO = earnPointMapper.toDto(earnPoint);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEarnPointMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(earnPointDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the EarnPoint in the database
        List<EarnPoint> earnPointList = earnPointRepository.findAll();
        assertThat(earnPointList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamEarnPoint() throws Exception {
        int databaseSizeBeforeUpdate = earnPointRepository.findAll().size();
        earnPoint.setId(count.incrementAndGet());

        // Create the EarnPoint
        EarnPointDTO earnPointDTO = earnPointMapper.toDto(earnPoint);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restEarnPointMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(earnPointDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the EarnPoint in the database
        List<EarnPoint> earnPointList = earnPointRepository.findAll();
        assertThat(earnPointList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteEarnPoint() throws Exception {
        // Initialize the database
        earnPointRepository.saveAndFlush(earnPoint);

        int databaseSizeBeforeDelete = earnPointRepository.findAll().size();

        // Delete the earnPoint
        restEarnPointMockMvc
            .perform(delete(ENTITY_API_URL_ID, earnPoint.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<EarnPoint> earnPointList = earnPointRepository.findAll();
        assertThat(earnPointList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
