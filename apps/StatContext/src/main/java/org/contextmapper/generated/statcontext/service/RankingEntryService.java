package org.contextmapper.generated.statcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.statcontext.domain.RankingEntry;
import org.contextmapper.generated.statcontext.repository.RankingEntryRepository;
import org.contextmapper.generated.statcontext.service.dto.RankingEntryDTO;
import org.contextmapper.generated.statcontext.service.mapper.RankingEntryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RankingEntry}.
 */
@Service
@Transactional
public class RankingEntryService {

    private final Logger log = LoggerFactory.getLogger(RankingEntryService.class);

    private final RankingEntryRepository rankingEntryRepository;

    private final RankingEntryMapper rankingEntryMapper;

    public RankingEntryService(RankingEntryRepository rankingEntryRepository, RankingEntryMapper rankingEntryMapper) {
        this.rankingEntryRepository = rankingEntryRepository;
        this.rankingEntryMapper = rankingEntryMapper;
    }

    /**
     * Save a rankingEntry.
     *
     * @param rankingEntryDTO the entity to save.
     * @return the persisted entity.
     */
    public RankingEntryDTO save(RankingEntryDTO rankingEntryDTO) {
        log.debug("Request to save RankingEntry : {}", rankingEntryDTO);
        RankingEntry rankingEntry = rankingEntryMapper.toEntity(rankingEntryDTO);
        rankingEntry = rankingEntryRepository.save(rankingEntry);
        return rankingEntryMapper.toDto(rankingEntry);
    }

    /**
     * Update a rankingEntry.
     *
     * @param rankingEntryDTO the entity to save.
     * @return the persisted entity.
     */
    public RankingEntryDTO update(RankingEntryDTO rankingEntryDTO) {
        log.debug("Request to update RankingEntry : {}", rankingEntryDTO);
        RankingEntry rankingEntry = rankingEntryMapper.toEntity(rankingEntryDTO);
        rankingEntry = rankingEntryRepository.save(rankingEntry);
        return rankingEntryMapper.toDto(rankingEntry);
    }

    /**
     * Partially update a rankingEntry.
     *
     * @param rankingEntryDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RankingEntryDTO> partialUpdate(RankingEntryDTO rankingEntryDTO) {
        log.debug("Request to partially update RankingEntry : {}", rankingEntryDTO);

        return rankingEntryRepository
            .findById(rankingEntryDTO.getId())
            .map(existingRankingEntry -> {
                rankingEntryMapper.partialUpdate(existingRankingEntry, rankingEntryDTO);

                return existingRankingEntry;
            })
            .map(rankingEntryRepository::save)
            .map(rankingEntryMapper::toDto);
    }

    /**
     * Get all the rankingEntries.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RankingEntryDTO> findAll() {
        log.debug("Request to get all RankingEntries");
        return rankingEntryRepository.findAll().stream().map(rankingEntryMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one rankingEntry by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RankingEntryDTO> findOne(Long id) {
        log.debug("Request to get RankingEntry : {}", id);
        return rankingEntryRepository.findById(id).map(rankingEntryMapper::toDto);
    }

    /**
     * Delete the rankingEntry by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RankingEntry : {}", id);
        rankingEntryRepository.deleteById(id);
    }
}
