package org.contextmapper.generated.newquestioncontext.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicLong;
import javax.persistence.EntityManager;
import org.contextmapper.generated.newquestioncontext.IntegrationTest;
import org.contextmapper.generated.newquestioncontext.domain.NewQuestion;
import org.contextmapper.generated.newquestioncontext.domain.enumeration.QuestionNotificationStatus;
import org.contextmapper.generated.newquestioncontext.repository.NewQuestionRepository;
import org.contextmapper.generated.newquestioncontext.service.dto.NewQuestionDTO;
import org.contextmapper.generated.newquestioncontext.service.mapper.NewQuestionMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

/**
 * Integration tests for the {@link NewQuestionResource} REST controller.
 */
@IntegrationTest
@AutoConfigureMockMvc
@WithMockUser
class NewQuestionResourceIT {

    private static final LocalDate DEFAULT_SENT_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_SENT_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_VIEWED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_VIEWED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_ANSWERED_DATE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_ANSWERED_DATE = LocalDate.now(ZoneId.systemDefault());

    private static final QuestionNotificationStatus DEFAULT_STATUS = QuestionNotificationStatus.PREPARING;
    private static final QuestionNotificationStatus UPDATED_STATUS = QuestionNotificationStatus.SENT;

    private static final String ENTITY_API_URL = "/api/new-questions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private NewQuestionRepository newQuestionRepository;

    @Autowired
    private NewQuestionMapper newQuestionMapper;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restNewQuestionMockMvc;

    private NewQuestion newQuestion;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NewQuestion createEntity(EntityManager em) {
        NewQuestion newQuestion = new NewQuestion()
            .sentDate(DEFAULT_SENT_DATE)
            .viewedDate(DEFAULT_VIEWED_DATE)
            .answeredDate(DEFAULT_ANSWERED_DATE)
            .status(DEFAULT_STATUS);
        return newQuestion;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static NewQuestion createUpdatedEntity(EntityManager em) {
        NewQuestion newQuestion = new NewQuestion()
            .sentDate(UPDATED_SENT_DATE)
            .viewedDate(UPDATED_VIEWED_DATE)
            .answeredDate(UPDATED_ANSWERED_DATE)
            .status(UPDATED_STATUS);
        return newQuestion;
    }

    @BeforeEach
    public void initTest() {
        newQuestion = createEntity(em);
    }

    @Test
    @Transactional
    void createNewQuestion() throws Exception {
        int databaseSizeBeforeCreate = newQuestionRepository.findAll().size();
        // Create the NewQuestion
        NewQuestionDTO newQuestionDTO = newQuestionMapper.toDto(newQuestion);
        restNewQuestionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(newQuestionDTO))
            )
            .andExpect(status().isCreated());

        // Validate the NewQuestion in the database
        List<NewQuestion> newQuestionList = newQuestionRepository.findAll();
        assertThat(newQuestionList).hasSize(databaseSizeBeforeCreate + 1);
        NewQuestion testNewQuestion = newQuestionList.get(newQuestionList.size() - 1);
        assertThat(testNewQuestion.getSentDate()).isEqualTo(DEFAULT_SENT_DATE);
        assertThat(testNewQuestion.getViewedDate()).isEqualTo(DEFAULT_VIEWED_DATE);
        assertThat(testNewQuestion.getAnsweredDate()).isEqualTo(DEFAULT_ANSWERED_DATE);
        assertThat(testNewQuestion.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    void createNewQuestionWithExistingId() throws Exception {
        // Create the NewQuestion with an existing ID
        newQuestion.setId(1L);
        NewQuestionDTO newQuestionDTO = newQuestionMapper.toDto(newQuestion);

        int databaseSizeBeforeCreate = newQuestionRepository.findAll().size();

        // An entity with an existing ID cannot be created, so this API call must fail
        restNewQuestionMockMvc
            .perform(
                post(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(newQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NewQuestion in the database
        List<NewQuestion> newQuestionList = newQuestionRepository.findAll();
        assertThat(newQuestionList).hasSize(databaseSizeBeforeCreate);
    }

    @Test
    @Transactional
    void getAllNewQuestions() throws Exception {
        // Initialize the database
        newQuestionRepository.saveAndFlush(newQuestion);

        // Get all the newQuestionList
        restNewQuestionMockMvc
            .perform(get(ENTITY_API_URL + "?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(newQuestion.getId().intValue())))
            .andExpect(jsonPath("$.[*].sentDate").value(hasItem(DEFAULT_SENT_DATE.toString())))
            .andExpect(jsonPath("$.[*].viewedDate").value(hasItem(DEFAULT_VIEWED_DATE.toString())))
            .andExpect(jsonPath("$.[*].answeredDate").value(hasItem(DEFAULT_ANSWERED_DATE.toString())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.toString())));
    }

    @Test
    @Transactional
    void getNewQuestion() throws Exception {
        // Initialize the database
        newQuestionRepository.saveAndFlush(newQuestion);

        // Get the newQuestion
        restNewQuestionMockMvc
            .perform(get(ENTITY_API_URL_ID, newQuestion.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(newQuestion.getId().intValue()))
            .andExpect(jsonPath("$.sentDate").value(DEFAULT_SENT_DATE.toString()))
            .andExpect(jsonPath("$.viewedDate").value(DEFAULT_VIEWED_DATE.toString()))
            .andExpect(jsonPath("$.answeredDate").value(DEFAULT_ANSWERED_DATE.toString()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.toString()));
    }

    @Test
    @Transactional
    void getNonExistingNewQuestion() throws Exception {
        // Get the newQuestion
        restNewQuestionMockMvc.perform(get(ENTITY_API_URL_ID, Long.MAX_VALUE)).andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    void putExistingNewQuestion() throws Exception {
        // Initialize the database
        newQuestionRepository.saveAndFlush(newQuestion);

        int databaseSizeBeforeUpdate = newQuestionRepository.findAll().size();

        // Update the newQuestion
        NewQuestion updatedNewQuestion = newQuestionRepository.findById(newQuestion.getId()).get();
        // Disconnect from session so that the updates on updatedNewQuestion are not directly saved in db
        em.detach(updatedNewQuestion);
        updatedNewQuestion
            .sentDate(UPDATED_SENT_DATE)
            .viewedDate(UPDATED_VIEWED_DATE)
            .answeredDate(UPDATED_ANSWERED_DATE)
            .status(UPDATED_STATUS);
        NewQuestionDTO newQuestionDTO = newQuestionMapper.toDto(updatedNewQuestion);

        restNewQuestionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, newQuestionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(newQuestionDTO))
            )
            .andExpect(status().isOk());

        // Validate the NewQuestion in the database
        List<NewQuestion> newQuestionList = newQuestionRepository.findAll();
        assertThat(newQuestionList).hasSize(databaseSizeBeforeUpdate);
        NewQuestion testNewQuestion = newQuestionList.get(newQuestionList.size() - 1);
        assertThat(testNewQuestion.getSentDate()).isEqualTo(UPDATED_SENT_DATE);
        assertThat(testNewQuestion.getViewedDate()).isEqualTo(UPDATED_VIEWED_DATE);
        assertThat(testNewQuestion.getAnsweredDate()).isEqualTo(UPDATED_ANSWERED_DATE);
        assertThat(testNewQuestion.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    void putNonExistingNewQuestion() throws Exception {
        int databaseSizeBeforeUpdate = newQuestionRepository.findAll().size();
        newQuestion.setId(count.incrementAndGet());

        // Create the NewQuestion
        NewQuestionDTO newQuestionDTO = newQuestionMapper.toDto(newQuestion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNewQuestionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, newQuestionDTO.getId())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(newQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NewQuestion in the database
        List<NewQuestion> newQuestionList = newQuestionRepository.findAll();
        assertThat(newQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithIdMismatchNewQuestion() throws Exception {
        int databaseSizeBeforeUpdate = newQuestionRepository.findAll().size();
        newQuestion.setId(count.incrementAndGet());

        // Create the NewQuestion
        NewQuestionDTO newQuestionDTO = newQuestionMapper.toDto(newQuestion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNewQuestionMockMvc
            .perform(
                put(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(TestUtil.convertObjectToJsonBytes(newQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NewQuestion in the database
        List<NewQuestion> newQuestionList = newQuestionRepository.findAll();
        assertThat(newQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void putWithMissingIdPathParamNewQuestion() throws Exception {
        int databaseSizeBeforeUpdate = newQuestionRepository.findAll().size();
        newQuestion.setId(count.incrementAndGet());

        // Create the NewQuestion
        NewQuestionDTO newQuestionDTO = newQuestionMapper.toDto(newQuestion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNewQuestionMockMvc
            .perform(put(ENTITY_API_URL).contentType(MediaType.APPLICATION_JSON).content(TestUtil.convertObjectToJsonBytes(newQuestionDTO)))
            .andExpect(status().isMethodNotAllowed());

        // Validate the NewQuestion in the database
        List<NewQuestion> newQuestionList = newQuestionRepository.findAll();
        assertThat(newQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void partialUpdateNewQuestionWithPatch() throws Exception {
        // Initialize the database
        newQuestionRepository.saveAndFlush(newQuestion);

        int databaseSizeBeforeUpdate = newQuestionRepository.findAll().size();

        // Update the newQuestion using partial update
        NewQuestion partialUpdatedNewQuestion = new NewQuestion();
        partialUpdatedNewQuestion.setId(newQuestion.getId());

        partialUpdatedNewQuestion
            .sentDate(UPDATED_SENT_DATE)
            .viewedDate(UPDATED_VIEWED_DATE)
            .answeredDate(UPDATED_ANSWERED_DATE)
            .status(UPDATED_STATUS);

        restNewQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNewQuestion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNewQuestion))
            )
            .andExpect(status().isOk());

        // Validate the NewQuestion in the database
        List<NewQuestion> newQuestionList = newQuestionRepository.findAll();
        assertThat(newQuestionList).hasSize(databaseSizeBeforeUpdate);
        NewQuestion testNewQuestion = newQuestionList.get(newQuestionList.size() - 1);
        assertThat(testNewQuestion.getSentDate()).isEqualTo(UPDATED_SENT_DATE);
        assertThat(testNewQuestion.getViewedDate()).isEqualTo(UPDATED_VIEWED_DATE);
        assertThat(testNewQuestion.getAnsweredDate()).isEqualTo(UPDATED_ANSWERED_DATE);
        assertThat(testNewQuestion.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    void fullUpdateNewQuestionWithPatch() throws Exception {
        // Initialize the database
        newQuestionRepository.saveAndFlush(newQuestion);

        int databaseSizeBeforeUpdate = newQuestionRepository.findAll().size();

        // Update the newQuestion using partial update
        NewQuestion partialUpdatedNewQuestion = new NewQuestion();
        partialUpdatedNewQuestion.setId(newQuestion.getId());

        partialUpdatedNewQuestion
            .sentDate(UPDATED_SENT_DATE)
            .viewedDate(UPDATED_VIEWED_DATE)
            .answeredDate(UPDATED_ANSWERED_DATE)
            .status(UPDATED_STATUS);

        restNewQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, partialUpdatedNewQuestion.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(partialUpdatedNewQuestion))
            )
            .andExpect(status().isOk());

        // Validate the NewQuestion in the database
        List<NewQuestion> newQuestionList = newQuestionRepository.findAll();
        assertThat(newQuestionList).hasSize(databaseSizeBeforeUpdate);
        NewQuestion testNewQuestion = newQuestionList.get(newQuestionList.size() - 1);
        assertThat(testNewQuestion.getSentDate()).isEqualTo(UPDATED_SENT_DATE);
        assertThat(testNewQuestion.getViewedDate()).isEqualTo(UPDATED_VIEWED_DATE);
        assertThat(testNewQuestion.getAnsweredDate()).isEqualTo(UPDATED_ANSWERED_DATE);
        assertThat(testNewQuestion.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    void patchNonExistingNewQuestion() throws Exception {
        int databaseSizeBeforeUpdate = newQuestionRepository.findAll().size();
        newQuestion.setId(count.incrementAndGet());

        // Create the NewQuestion
        NewQuestionDTO newQuestionDTO = newQuestionMapper.toDto(newQuestion);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restNewQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, newQuestionDTO.getId())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(newQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NewQuestion in the database
        List<NewQuestion> newQuestionList = newQuestionRepository.findAll();
        assertThat(newQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithIdMismatchNewQuestion() throws Exception {
        int databaseSizeBeforeUpdate = newQuestionRepository.findAll().size();
        newQuestion.setId(count.incrementAndGet());

        // Create the NewQuestion
        NewQuestionDTO newQuestionDTO = newQuestionMapper.toDto(newQuestion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNewQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL_ID, count.incrementAndGet())
                    .contentType("application/merge-patch+json")
                    .content(TestUtil.convertObjectToJsonBytes(newQuestionDTO))
            )
            .andExpect(status().isBadRequest());

        // Validate the NewQuestion in the database
        List<NewQuestion> newQuestionList = newQuestionRepository.findAll();
        assertThat(newQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void patchWithMissingIdPathParamNewQuestion() throws Exception {
        int databaseSizeBeforeUpdate = newQuestionRepository.findAll().size();
        newQuestion.setId(count.incrementAndGet());

        // Create the NewQuestion
        NewQuestionDTO newQuestionDTO = newQuestionMapper.toDto(newQuestion);

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        restNewQuestionMockMvc
            .perform(
                patch(ENTITY_API_URL).contentType("application/merge-patch+json").content(TestUtil.convertObjectToJsonBytes(newQuestionDTO))
            )
            .andExpect(status().isMethodNotAllowed());

        // Validate the NewQuestion in the database
        List<NewQuestion> newQuestionList = newQuestionRepository.findAll();
        assertThat(newQuestionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    void deleteNewQuestion() throws Exception {
        // Initialize the database
        newQuestionRepository.saveAndFlush(newQuestion);

        int databaseSizeBeforeDelete = newQuestionRepository.findAll().size();

        // Delete the newQuestion
        restNewQuestionMockMvc
            .perform(delete(ENTITY_API_URL_ID, newQuestion.getId()).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<NewQuestion> newQuestionList = newQuestionRepository.findAll();
        assertThat(newQuestionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
