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
import org.contextmapper.generated.statcontext.domain.Ranking;
import org.contextmapper.generated.statcontext.repository.RankingRepository;
import org.contextmapper.generated.statcontext.service.dto.RankingDTO;
import org.contextmapper.generated.statcontext.service.mapper.RankingMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link RankingResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RankingResourceIT {

    private static final String ENTITY_API_URL = "/api/rankings";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private RankingRepository rankingRepository;

    @Autowired
    private RankingMapper rankingMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRankingMockMvc;

    private Ranking ranking;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ranking createEntity(EntityManager em) {
        Ranking ranking = new Ranking();
        return ranking;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Ranking createUpdatedEntity(EntityManager em) {
        Ranking ranking = new Ranking();
        return ranking;
    }

    @BeforeEach
    public void initTest() {
        ranking = createEntity(em);
    }

    @Test
    @Transactional
    void createRanking() throws Exception {
        int databaseSizeBeforeCreate = rankingRepository.findAll().size();
        // Create the Ranking
        RankingDTO rankingDTO = rankingMapper.toDto(ranking);
        restRankingMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rankingDTO)))
            .andExpect(status().isCreated());

        // Validate the Ranking in the database
        List<Ranking> rankingList = rankingRepository.findAll();
        assertThat(rankingList).hasSize(databaseSizeBeforeCreate + 1);
        Ranking testRanking = rankingList.get(rankingList.size() - 1);
    }

    @Test
    @Transactional
    void createRankingWithExistingId() throws Exception {
        // Create the Ranking with an existing ID
        ranking.setId(1L);
        RankingDTO rankingDTO = rankingMapper.toDto(ranking);

        int databaseSizeBeforeCreate = rankingRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRankingMockMvc
            .perform(post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rankingDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Ranking in the database
        List<Ranking> rankingList = rankingRepository.findAll();
        assertThat(rankingList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRankings() throws Exception {
        // Initialize the database
        rankingRepository.saveAndFlush(ranking);

        // Get all the rankingList
        restRankingMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ranking.getId().intValue())));
    }

    @Test
    @Transactional
    void getRanking() throws Exception {
        // Initialize the database
        rankingRepository.saveAndFlush(ranking);

        // Get the ranking
        restRankingMockMvc
            .perform(get(ENTITY_API_URL_ID, ranking.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ranking.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingRanking() throws Exception {
        // Get the ranking
        restRankingMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingRanking() throws Exception {
        // Initialize the database
        rankingRepository.saveAndFlush(ranking);

        int databaseSizeBeforeUpdate = rankingRepository.findAll().size();

        // Update the ranking
        Ranking updatedRanking = rankingRepository.findById(ranking.getId()).get();
        // Disconnect from session so that the updates on updatedRanking are not directly saved in db
        em.detach(updatedRanking);
        RankingDTO rankingDTO = rankingMapper.toDto(updatedRanking);

        restRankingMockMvc
            .perform(
                put(ENTITY_API_URL_ID, rankingDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rankingDTO))
            )
            .andExpect(status().isOk());

        // Validate the Ranking in the database
        List<Ranking> rankingList = rankingRepository.findAll();
        assertThat(rankingList).hasSize(databaseSizeBeforeUpdate);
        Ranking testRanking = rankingList.get(rankingList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingRanking() throws Exception {
        int databaseSizeBeforeUpdate = rankingRepository.findAll().size();
        ranking.setId(count.incrementAndGet());

        // Create the Ranking
        RankingDTO rankingDTO = rankingMapper.toDto(ranking);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRankingMockMvc
            .perform(
                put(ENTITY_API_URL_ID, rankingDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rankingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Ranking in the database
        List<Ranking> rankingList = rankingRepository.findAll();
        assertThat(rankingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRanking() throws Exception {
        int databaseSizeBeforeUpdate = rankingRepository.findAll().size();
        ranking.setId(count.incrementAndGet());

        // Create the Ranking
        RankingDTO rankingDTO = rankingMapper.toDto(ranking);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRankingMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rankingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Ranking in the database
        List<Ranking> rankingList = rankingRepository.findAll();
        assertThat(rankingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRanking() throws Exception {
        int databaseSizeBeforeUpdate = rankingRepository.findAll().size();
        ranking.setId(count.incrementAndGet());

        // Create the Ranking
        RankingDTO rankingDTO = rankingMapper.toDto(ranking);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRankingMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rankingDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the Ranking in the database
        List<Ranking> rankingList = rankingRepository.findAll();
        assertThat(rankingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRankingWithPatch() throws Exception {
        // Initialize the database
        rankingRepository.saveAndFlush(ranking);

        int databaseSizeBeforeUpdate = rankingRepository.findAll().size();

        // Update the ranking using partial update
        Ranking partialUpdatedRanking = new Ranking();
        partialUpdatedRanking.setId(ranking.getId());

        restRankingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRanking.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRanking))
            )
            .andExpect(status().isOk());

        // Validate the Ranking in the database
        List<Ranking> rankingList = rankingRepository.findAll();
        assertThat(rankingList).hasSize(databaseSizeBeforeUpdate);
        Ranking testRanking = rankingList.get(rankingList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateRankingWithPatch() throws Exception {
        // Initialize the database
        rankingRepository.saveAndFlush(ranking);

        int databaseSizeBeforeUpdate = rankingRepository.findAll().size();

        // Update the ranking using partial update
        Ranking partialUpdatedRanking = new Ranking();
        partialUpdatedRanking.setId(ranking.getId());

        restRankingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRanking.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRanking))
            )
            .andExpect(status().isOk());

        // Validate the Ranking in the database
        List<Ranking> rankingList = rankingRepository.findAll();
        assertThat(rankingList).hasSize(databaseSizeBeforeUpdate);
        Ranking testRanking = rankingList.get(rankingList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingRanking() throws Exception {
        int databaseSizeBeforeUpdate = rankingRepository.findAll().size();
        ranking.setId(count.incrementAndGet());

        // Create the Ranking
        RankingDTO rankingDTO = rankingMapper.toDto(ranking);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRankingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, rankingDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(rankingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Ranking in the database
        List<Ranking> rankingList = rankingRepository.findAll();
        assertThat(rankingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRanking() throws Exception {
        int databaseSizeBeforeUpdate = rankingRepository.findAll().size();
        ranking.setId(count.incrementAndGet());

        // Create the Ranking
        RankingDTO rankingDTO = rankingMapper.toDto(ranking);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRankingMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(rankingDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the Ranking in the database
        List<Ranking> rankingList = rankingRepository.findAll();
        assertThat(rankingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRanking() throws Exception {
        int databaseSizeBeforeUpdate = rankingRepository.findAll().size();
        ranking.setId(count.incrementAndGet());

        // Create the Ranking
        RankingDTO rankingDTO = rankingMapper.toDto(ranking);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRankingMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(rankingDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the Ranking in the database
        List<Ranking> rankingList = rankingRepository.findAll();
        assertThat(rankingList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRanking() throws Exception {
        // Initialize the database
        rankingRepository.saveAndFlush(ranking);

        int databaseSizeBeforeDelete = rankingRepository.findAll().size();

        // Delete the ranking
        restRankingMockMvc
            .perform(delete(ENTITY_API_URL_ID, ranking.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Ranking> rankingList = rankingRepository.findAll();
        assertThat(rankingList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
