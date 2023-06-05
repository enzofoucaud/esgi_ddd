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
import org.contextmapper.generated.statcontext.domain.RankingEntry;
import org.contextmapper.generated.statcontext.repository.RankingEntryRepository;
import org.contextmapper.generated.statcontext.service.dto.RankingEntryDTO;
import org.contextmapper.generated.statcontext.service.mapper.RankingEntryMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link RankingEntryResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class RankingEntryResourceIT {

    private static final String DEFAULT_USER_LEVEL = "AAAAAAAAAA";
    private static final String UPDATED_USER_LEVEL = "BBBBBBBBBB";

    private static final Integer DEFAULT_SCORE = 1;
    private static final Integer UPDATED_SCORE = 2;

    private static final String ENTITY_API_URL = "/api/ranking-entries";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private RankingEntryRepository rankingEntryRepository;

    @Autowired
    private RankingEntryMapper rankingEntryMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restRankingEntryMockMvc;

    private RankingEntry rankingEntry;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RankingEntry createEntity(EntityManager em) {
        RankingEntry rankingEntry = new RankingEntry().userLevel(DEFAULT_USER_LEVEL).score(DEFAULT_SCORE);
        return rankingEntry;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static RankingEntry createUpdatedEntity(EntityManager em) {
        RankingEntry rankingEntry = new RankingEntry().userLevel(UPDATED_USER_LEVEL).score(UPDATED_SCORE);
        return rankingEntry;
    }

    @BeforeEach
    public void initTest() {
        rankingEntry = createEntity(em);
    }

    @Test
    @Transactional
    void createRankingEntry() throws Exception {
        int databaseSizeBeforeCreate = rankingEntryRepository.findAll().size();
        // Create the RankingEntry
        RankingEntryDTO rankingEntryDTO = rankingEntryMapper.toDto(rankingEntry);
        restRankingEntryMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rankingEntryDTO))
            )
            .andExpect(status().isCreated());

        // Validate the RankingEntry in the database
        List<RankingEntry> rankingEntryList = rankingEntryRepository.findAll();
        assertThat(rankingEntryList).hasSize(databaseSizeBeforeCreate + 1);
        RankingEntry testRankingEntry = rankingEntryList.get(rankingEntryList.size() - 1);
        assertThat(testRankingEntry.getUserLevel()).isEqualTo(DEFAULT_USER_LEVEL);
        assertThat(testRankingEntry.getScore()).isEqualTo(DEFAULT_SCORE);
    }

    @Test
    @Transactional
    void createRankingEntryWithExistingId() throws Exception {
        // Create the RankingEntry with an existing ID
        rankingEntry.setId(1L);
        RankingEntryDTO rankingEntryDTO = rankingEntryMapper.toDto(rankingEntry);

        int databaseSizeBeforeCreate = rankingEntryRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restRankingEntryMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rankingEntryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RankingEntry in the database
        List<RankingEntry> rankingEntryList = rankingEntryRepository.findAll();
        assertThat(rankingEntryList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllRankingEntries() throws Exception {
        // Initialize the database
        rankingEntryRepository.saveAndFlush(rankingEntry);

        // Get all the rankingEntryList
        restRankingEntryMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(rankingEntry.getId().intValue())))
            .andExpect(jsonPath("$.[*].userLevel").value(hasItem(DEFAULT_USER_LEVEL)))
            .andExpect(jsonPath("$.[*].score").value(hasItem(DEFAULT_SCORE)));
    }

    @Test
    @Transactional
    void getRankingEntry() throws Exception {
        // Initialize the database
        rankingEntryRepository.saveAndFlush(rankingEntry);

        // Get the rankingEntry
        restRankingEntryMockMvc
            .perform(get(ENTITY_API_URL_ID, rankingEntry.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(rankingEntry.getId().intValue()))
            .andExpect(jsonPath("$.userLevel").value(DEFAULT_USER_LEVEL))
            .andExpect(jsonPath("$.score").value(DEFAULT_SCORE));
    }

    @Test
    @Transactional
    void getNonExistingRankingEntry() throws Exception {
        // Get the rankingEntry
        restRankingEntryMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingRankingEntry() throws Exception {
        // Initialize the database
        rankingEntryRepository.saveAndFlush(rankingEntry);

        int databaseSizeBeforeUpdate = rankingEntryRepository.findAll().size();

        // Update the rankingEntry
        RankingEntry updatedRankingEntry = rankingEntryRepository.findById(rankingEntry.getId()).get();
        // Disconnect from session so that the updates on updatedRankingEntry are not directly saved in db
        em.detach(updatedRankingEntry);
        updatedRankingEntry.userLevel(UPDATED_USER_LEVEL).score(UPDATED_SCORE);
        RankingEntryDTO rankingEntryDTO = rankingEntryMapper.toDto(updatedRankingEntry);

        restRankingEntryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, rankingEntryDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rankingEntryDTO))
            )
            .andExpect(status().isOk());

        // Validate the RankingEntry in the database
        List<RankingEntry> rankingEntryList = rankingEntryRepository.findAll();
        assertThat(rankingEntryList).hasSize(databaseSizeBeforeUpdate);
        RankingEntry testRankingEntry = rankingEntryList.get(rankingEntryList.size() - 1);
        assertThat(testRankingEntry.getUserLevel()).isEqualTo(UPDATED_USER_LEVEL);
        assertThat(testRankingEntry.getScore()).isEqualTo(UPDATED_SCORE);
    }

    @Test
    @Transactional
    void putNonExistingRankingEntry() throws Exception {
        int databaseSizeBeforeUpdate = rankingEntryRepository.findAll().size();
        rankingEntry.setId(count.incrementAndGet());

        // Create the RankingEntry
        RankingEntryDTO rankingEntryDTO = rankingEntryMapper.toDto(rankingEntry);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRankingEntryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, rankingEntryDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rankingEntryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RankingEntry in the database
        List<RankingEntry> rankingEntryList = rankingEntryRepository.findAll();
        assertThat(rankingEntryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchRankingEntry() throws Exception {
        int databaseSizeBeforeUpdate = rankingEntryRepository.findAll().size();
        rankingEntry.setId(count.incrementAndGet());

        // Create the RankingEntry
        RankingEntryDTO rankingEntryDTO = rankingEntryMapper.toDto(rankingEntry);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRankingEntryMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(rankingEntryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RankingEntry in the database
        List<RankingEntry> rankingEntryList = rankingEntryRepository.findAll();
        assertThat(rankingEntryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamRankingEntry() throws Exception {
        int databaseSizeBeforeUpdate = rankingEntryRepository.findAll().size();
        rankingEntry.setId(count.incrementAndGet());

        // Create the RankingEntry
        RankingEntryDTO rankingEntryDTO = rankingEntryMapper.toDto(rankingEntry);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRankingEntryMockMvc
            .perform(
                put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(rankingEntryDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RankingEntry in the database
        List<RankingEntry> rankingEntryList = rankingEntryRepository.findAll();
        assertThat(rankingEntryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateRankingEntryWithPatch() throws Exception {
        // Initialize the database
        rankingEntryRepository.saveAndFlush(rankingEntry);

        int databaseSizeBeforeUpdate = rankingEntryRepository.findAll().size();

        // Update the rankingEntry using partial update
        RankingEntry partialUpdatedRankingEntry = new RankingEntry();
        partialUpdatedRankingEntry.setId(rankingEntry.getId());

        partialUpdatedRankingEntry.userLevel(UPDATED_USER_LEVEL).score(UPDATED_SCORE);

        restRankingEntryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRankingEntry.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRankingEntry))
            )
            .andExpect(status().isOk());

        // Validate the RankingEntry in the database
        List<RankingEntry> rankingEntryList = rankingEntryRepository.findAll();
        assertThat(rankingEntryList).hasSize(databaseSizeBeforeUpdate);
        RankingEntry testRankingEntry = rankingEntryList.get(rankingEntryList.size() - 1);
        assertThat(testRankingEntry.getUserLevel()).isEqualTo(UPDATED_USER_LEVEL);
        assertThat(testRankingEntry.getScore()).isEqualTo(UPDATED_SCORE);
    }

    @Test
    @Transactional
    void fullUpdateRankingEntryWithPatch() throws Exception {
        // Initialize the database
        rankingEntryRepository.saveAndFlush(rankingEntry);

        int databaseSizeBeforeUpdate = rankingEntryRepository.findAll().size();

        // Update the rankingEntry using partial update
        RankingEntry partialUpdatedRankingEntry = new RankingEntry();
        partialUpdatedRankingEntry.setId(rankingEntry.getId());

        partialUpdatedRankingEntry.userLevel(UPDATED_USER_LEVEL).score(UPDATED_SCORE);

        restRankingEntryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedRankingEntry.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedRankingEntry))
            )
            .andExpect(status().isOk());

        // Validate the RankingEntry in the database
        List<RankingEntry> rankingEntryList = rankingEntryRepository.findAll();
        assertThat(rankingEntryList).hasSize(databaseSizeBeforeUpdate);
        RankingEntry testRankingEntry = rankingEntryList.get(rankingEntryList.size() - 1);
        assertThat(testRankingEntry.getUserLevel()).isEqualTo(UPDATED_USER_LEVEL);
        assertThat(testRankingEntry.getScore()).isEqualTo(UPDATED_SCORE);
    }

    @Test
    @Transactional
    void patchNonExistingRankingEntry() throws Exception {
        int databaseSizeBeforeUpdate = rankingEntryRepository.findAll().size();
        rankingEntry.setId(count.incrementAndGet());

        // Create the RankingEntry
        RankingEntryDTO rankingEntryDTO = rankingEntryMapper.toDto(rankingEntry);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restRankingEntryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, rankingEntryDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(rankingEntryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RankingEntry in the database
        List<RankingEntry> rankingEntryList = rankingEntryRepository.findAll();
        assertThat(rankingEntryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchRankingEntry() throws Exception {
        int databaseSizeBeforeUpdate = rankingEntryRepository.findAll().size();
        rankingEntry.setId(count.incrementAndGet());

        // Create the RankingEntry
        RankingEntryDTO rankingEntryDTO = rankingEntryMapper.toDto(rankingEntry);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRankingEntryMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(rankingEntryDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the RankingEntry in the database
        List<RankingEntry> rankingEntryList = rankingEntryRepository.findAll();
        assertThat(rankingEntryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamRankingEntry() throws Exception {
        int databaseSizeBeforeUpdate = rankingEntryRepository.findAll().size();
        rankingEntry.setId(count.incrementAndGet());

        // Create the RankingEntry
        RankingEntryDTO rankingEntryDTO = rankingEntryMapper.toDto(rankingEntry);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restRankingEntryMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(rankingEntryDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the RankingEntry in the database
        List<RankingEntry> rankingEntryList = rankingEntryRepository.findAll();
        assertThat(rankingEntryList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteRankingEntry() throws Exception {
        // Initialize the database
        rankingEntryRepository.saveAndFlush(rankingEntry);

        int databaseSizeBeforeDelete = rankingEntryRepository.findAll().size();

        // Delete the rankingEntry
        restRankingEntryMockMvc
            .perform(delete(ENTITY_API_URL_ID, rankingEntry.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<RankingEntry> rankingEntryList = rankingEntryRepository.findAll();
        assertThat(rankingEntryList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
