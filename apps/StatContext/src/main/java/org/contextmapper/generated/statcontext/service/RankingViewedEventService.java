package org.contextmapper.generated.statcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.statcontext.domain.RankingViewedEvent;
import org.contextmapper.generated.statcontext.repository.RankingViewedEventRepository;
import org.contextmapper.generated.statcontext.service.dto.RankingViewedEventDTO;
import org.contextmapper.generated.statcontext.service.mapper.RankingViewedEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link RankingViewedEvent}.
 */
@Service
@Transactional
public class RankingViewedEventService {

    private final Logger log = LoggerFactory.getLogger(RankingViewedEventService.class);

    private final RankingViewedEventRepository rankingViewedEventRepository;

    private final RankingViewedEventMapper rankingViewedEventMapper;

    public RankingViewedEventService(
        RankingViewedEventRepository rankingViewedEventRepository,
        RankingViewedEventMapper rankingViewedEventMapper
    ) {
        this.rankingViewedEventRepository = rankingViewedEventRepository;
        this.rankingViewedEventMapper = rankingViewedEventMapper;
    }

    /**
     * Save a rankingViewedEvent.
     *
     * @param rankingViewedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public RankingViewedEventDTO save(RankingViewedEventDTO rankingViewedEventDTO) {
        log.debug("Request to save RankingViewedEvent : {}", rankingViewedEventDTO);
        RankingViewedEvent rankingViewedEvent = rankingViewedEventMapper.toEntity(rankingViewedEventDTO);
        rankingViewedEvent = rankingViewedEventRepository.save(rankingViewedEvent);
        return rankingViewedEventMapper.toDto(rankingViewedEvent);
    }

    /**
     * Update a rankingViewedEvent.
     *
     * @param rankingViewedEventDTO the entity to save.
     * @return the persisted entity.
     */
    public RankingViewedEventDTO update(RankingViewedEventDTO rankingViewedEventDTO) {
        log.debug("Request to update RankingViewedEvent : {}", rankingViewedEventDTO);
        RankingViewedEvent rankingViewedEvent = rankingViewedEventMapper.toEntity(rankingViewedEventDTO);
        rankingViewedEvent = rankingViewedEventRepository.save(rankingViewedEvent);
        return rankingViewedEventMapper.toDto(rankingViewedEvent);
    }

    /**
     * Partially update a rankingViewedEvent.
     *
     * @param rankingViewedEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RankingViewedEventDTO> partialUpdate(RankingViewedEventDTO rankingViewedEventDTO) {
        log.debug("Request to partially update RankingViewedEvent : {}", rankingViewedEventDTO);

        return rankingViewedEventRepository
            .findById(rankingViewedEventDTO.getId())
            .map(existingRankingViewedEvent -> {
                rankingViewedEventMapper.partialUpdate(existingRankingViewedEvent, rankingViewedEventDTO);

                return existingRankingViewedEvent;
            })
            .map(rankingViewedEventRepository::save)
            .map(rankingViewedEventMapper::toDto);
    }

    /**
     * Get all the rankingViewedEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RankingViewedEventDTO> findAll() {
        log.debug("Request to get all RankingViewedEvents");
        return rankingViewedEventRepository
            .findAll()
            .stream()
            .map(rankingViewedEventMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one rankingViewedEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RankingViewedEventDTO> findOne(Long id) {
        log.debug("Request to get RankingViewedEvent : {}", id);
        return rankingViewedEventRepository.findById(id).map(rankingViewedEventMapper::toDto);
    }

    /**
     * Delete the rankingViewedEvent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete RankingViewedEvent : {}", id);
        rankingViewedEventRepository.deleteById(id);
    }
}
