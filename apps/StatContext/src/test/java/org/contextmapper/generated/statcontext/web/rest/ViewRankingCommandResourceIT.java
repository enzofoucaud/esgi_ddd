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
import org.contextmapper.generated.statcontext.domain.ViewRankingCommand;
import org.contextmapper.generated.statcontext.repository.ViewRankingCommandRepository;
import org.contextmapper.generated.statcontext.service.dto.ViewRankingCommandDTO;
import org.contextmapper.generated.statcontext.service.mapper.ViewRankingCommandMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link ViewRankingCommandResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class ViewRankingCommandResourceIT {

    private static final String ENTITY_API_URL = "/api/view-ranking-commands";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private ViewRankingCommandRepository viewRankingCommandRepository;

    @Autowired
    private ViewRankingCommandMapper viewRankingCommandMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restViewRankingCommandMockMvc;

    private ViewRankingCommand viewRankingCommand;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ViewRankingCommand createEntity(EntityManager em) {
        ViewRankingCommand viewRankingCommand = new ViewRankingCommand();
        return viewRankingCommand;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static ViewRankingCommand createUpdatedEntity(EntityManager em) {
        ViewRankingCommand viewRankingCommand = new ViewRankingCommand();
        return viewRankingCommand;
    }

    @BeforeEach
    public void initTest() {
        viewRankingCommand = createEntity(em);
    }

    @Test
    @Transactional
    void createViewRankingCommand() throws Exception {
        int databaseSizeBeforeCreate = viewRankingCommandRepository.findAll().size();
        // Create the ViewRankingCommand
        ViewRankingCommandDTO viewRankingCommandDTO = viewRankingCommandMapper.toDto(viewRankingCommand);
        restViewRankingCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewRankingCommandDTO))
            )
            .andExpect(status().isCreated());

        // Validate the ViewRankingCommand in the database
        List<ViewRankingCommand> viewRankingCommandList = viewRankingCommandRepository.findAll();
        assertThat(viewRankingCommandList).hasSize(databaseSizeBeforeCreate + 1);
        ViewRankingCommand testViewRankingCommand = viewRankingCommandList.get(viewRankingCommandList.size() - 1);
    }

    @Test
    @Transactional
    void createViewRankingCommandWithExistingId() throws Exception {
        // Create the ViewRankingCommand with an existing ID
        viewRankingCommand.setId(1L);
        ViewRankingCommandDTO viewRankingCommandDTO = viewRankingCommandMapper.toDto(viewRankingCommand);

        int databaseSizeBeforeCreate = viewRankingCommandRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restViewRankingCommandMockMvc
            .perform(
                post(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewRankingCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewRankingCommand in the database
        List<ViewRankingCommand> viewRankingCommandList = viewRankingCommandRepository.findAll();
        assertThat(viewRankingCommandList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllViewRankingCommands() throws Exception {
        // Initialize the database
        viewRankingCommandRepository.saveAndFlush(viewRankingCommand);

        // Get all the viewRankingCommandList
        restViewRankingCommandMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(viewRankingCommand.getId().intValue())));
    }

    @Test
    @Transactional
    void getViewRankingCommand() throws Exception {
        // Initialize the database
        viewRankingCommandRepository.saveAndFlush(viewRankingCommand);

        // Get the viewRankingCommand
        restViewRankingCommandMockMvc
            .perform(get(ENTITY_API_URL_ID, viewRankingCommand.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(viewRankingCommand.getId().intValue()));
    }

    @Test
    @Transactional
    void getNonExistingViewRankingCommand() throws Exception {
        // Get the viewRankingCommand
        restViewRankingCommandMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingViewRankingCommand() throws Exception {
        // Initialize the database
        viewRankingCommandRepository.saveAndFlush(viewRankingCommand);

        int databaseSizeBeforeUpdate = viewRankingCommandRepository.findAll().size();

        // Update the viewRankingCommand
        ViewRankingCommand updatedViewRankingCommand = viewRankingCommandRepository.findById(viewRankingCommand.getId()).get();
        // Disconnect from session so that the updates on updatedViewRankingCommand are not directly saved in db
        em.detach(updatedViewRankingCommand);
        ViewRankingCommandDTO viewRankingCommandDTO = viewRankingCommandMapper.toDto(updatedViewRankingCommand);

        restViewRankingCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, viewRankingCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewRankingCommandDTO))
            )
            .andExpect(status().isOk());

        // Validate the ViewRankingCommand in the database
        List<ViewRankingCommand> viewRankingCommandList = viewRankingCommandRepository.findAll();
        assertThat(viewRankingCommandList).hasSize(databaseSizeBeforeUpdate);
        ViewRankingCommand testViewRankingCommand = viewRankingCommandList.get(viewRankingCommandList.size() - 1);
    }

    @Test
    @Transactional
    void putNonExistingViewRankingCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewRankingCommandRepository.findAll().size();
        viewRankingCommand.setId(count.incrementAndGet());

        // Create the ViewRankingCommand
        ViewRankingCommandDTO viewRankingCommandDTO = viewRankingCommandMapper.toDto(viewRankingCommand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restViewRankingCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, viewRankingCommandDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewRankingCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewRankingCommand in the database
        List<ViewRankingCommand> viewRankingCommandList = viewRankingCommandRepository.findAll();
        assertThat(viewRankingCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchViewRankingCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewRankingCommandRepository.findAll().size();
        viewRankingCommand.setId(count.incrementAndGet());

        // Create the ViewRankingCommand
        ViewRankingCommandDTO viewRankingCommandDTO = viewRankingCommandMapper.toDto(viewRankingCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewRankingCommandMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewRankingCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewRankingCommand in the database
        List<ViewRankingCommand> viewRankingCommandList = viewRankingCommandRepository.findAll();
        assertThat(viewRankingCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamViewRankingCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewRankingCommandRepository.findAll().size();
        viewRankingCommand.setId(count.incrementAndGet());

        // Create the ViewRankingCommand
        ViewRankingCommandDTO viewRankingCommandDTO = viewRankingCommandMapper.toDto(viewRankingCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewRankingCommandMockMvc
            .perform(
                put(ENTITY_API_URL)
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(viewRankingCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ViewRankingCommand in the database
        List<ViewRankingCommand> viewRankingCommandList = viewRankingCommandRepository.findAll();
        assertThat(viewRankingCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateViewRankingCommandWithPatch() throws Exception {
        // Initialize the database
        viewRankingCommandRepository.saveAndFlush(viewRankingCommand);

        int databaseSizeBeforeUpdate = viewRankingCommandRepository.findAll().size();

        // Update the viewRankingCommand using partial update
        ViewRankingCommand partialUpdatedViewRankingCommand = new ViewRankingCommand();
        partialUpdatedViewRankingCommand.setId(viewRankingCommand.getId());

        restViewRankingCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedViewRankingCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedViewRankingCommand))
            )
            .andExpect(status().isOk());

        // Validate the ViewRankingCommand in the database
        List<ViewRankingCommand> viewRankingCommandList = viewRankingCommandRepository.findAll();
        assertThat(viewRankingCommandList).hasSize(databaseSizeBeforeUpdate);
        ViewRankingCommand testViewRankingCommand = viewRankingCommandList.get(viewRankingCommandList.size() - 1);
    }

    @Test
    @Transactional
    void fullUpdateViewRankingCommandWithPatch() throws Exception {
        // Initialize the database
        viewRankingCommandRepository.saveAndFlush(viewRankingCommand);

        int databaseSizeBeforeUpdate = viewRankingCommandRepository.findAll().size();

        // Update the viewRankingCommand using partial update
        ViewRankingCommand partialUpdatedViewRankingCommand = new ViewRankingCommand();
        partialUpdatedViewRankingCommand.setId(viewRankingCommand.getId());

        restViewRankingCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedViewRankingCommand.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedViewRankingCommand))
            )
            .andExpect(status().isOk());

        // Validate the ViewRankingCommand in the database
        List<ViewRankingCommand> viewRankingCommandList = viewRankingCommandRepository.findAll();
        assertThat(viewRankingCommandList).hasSize(databaseSizeBeforeUpdate);
        ViewRankingCommand testViewRankingCommand = viewRankingCommandList.get(viewRankingCommandList.size() - 1);
    }

    @Test
    @Transactional
    void patchNonExistingViewRankingCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewRankingCommandRepository.findAll().size();
        viewRankingCommand.setId(count.incrementAndGet());

        // Create the ViewRankingCommand
        ViewRankingCommandDTO viewRankingCommandDTO = viewRankingCommandMapper.toDto(viewRankingCommand);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restViewRankingCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, viewRankingCommandDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(viewRankingCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewRankingCommand in the database
        List<ViewRankingCommand> viewRankingCommandList = viewRankingCommandRepository.findAll();
        assertThat(viewRankingCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchViewRankingCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewRankingCommandRepository.findAll().size();
        viewRankingCommand.setId(count.incrementAndGet());

        // Create the ViewRankingCommand
        ViewRankingCommandDTO viewRankingCommandDTO = viewRankingCommandMapper.toDto(viewRankingCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewRankingCommandMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(viewRankingCommandDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the ViewRankingCommand in the database
        List<ViewRankingCommand> viewRankingCommandList = viewRankingCommandRepository.findAll();
        assertThat(viewRankingCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamViewRankingCommand() throws Exception {
        int databaseSizeBeforeUpdate = viewRankingCommandRepository.findAll().size();
        viewRankingCommand.setId(count.incrementAndGet());

        // Create the ViewRankingCommand
        ViewRankingCommandDTO viewRankingCommandDTO = viewRankingCommandMapper.toDto(viewRankingCommand);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restViewRankingCommandMockMvc
            .perform(
                patch(ENTITY_API_URL)
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(viewRankingCommandDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the ViewRankingCommand in the database
        List<ViewRankingCommand> viewRankingCommandList = viewRankingCommandRepository.findAll();
        assertThat(viewRankingCommandList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteViewRankingCommand() throws Exception {
        // Initialize the database
        viewRankingCommandRepository.saveAndFlush(viewRankingCommand);

        int databaseSizeBeforeDelete = viewRankingCommandRepository.findAll().size();

        // Delete the viewRankingCommand
        restViewRankingCommandMockMvc
            .perform(delete(ENTITY_API_URL_ID, viewRankingCommand.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<ViewRankingCommand> viewRankingCommandList = viewRankingCommandRepository.findAll();
        assertThat(viewRankingCommandList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
