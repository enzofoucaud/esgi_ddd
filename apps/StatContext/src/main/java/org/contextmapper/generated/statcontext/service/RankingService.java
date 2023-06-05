package org.contextmapper.generated.statcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.statcontext.domain.Ranking;
import org.contextmapper.generated.statcontext.repository.RankingRepository;
import org.contextmapper.generated.statcontext.service.dto.RankingDTO;
import org.contextmapper.generated.statcontext.service.mapper.RankingMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link Ranking}.
 */
@Service
@Transactional
public class RankingService {

    private final Logger log = LoggerFactory.getLogger(RankingService.class);

    private final RankingRepository rankingRepository;

    private final RankingMapper rankingMapper;

    public RankingService(RankingRepository rankingRepository, RankingMapper rankingMapper) {
        this.rankingRepository = rankingRepository;
        this.rankingMapper = rankingMapper;
    }

    /**
     * Save a ranking.
     *
     * @param rankingDTO the entity to save.
     * @return the persisted entity.
     */
    public RankingDTO save(RankingDTO rankingDTO) {
        log.debug("Request to save Ranking : {}", rankingDTO);
        Ranking ranking = rankingMapper.toEntity(rankingDTO);
        ranking = rankingRepository.save(ranking);
        return rankingMapper.toDto(ranking);
    }

    /**
     * Update a ranking.
     *
     * @param rankingDTO the entity to save.
     * @return the persisted entity.
     */
    public RankingDTO update(RankingDTO rankingDTO) {
        log.debug("Request to update Ranking : {}", rankingDTO);
        Ranking ranking = rankingMapper.toEntity(rankingDTO);
        // no save call needed as we have no fields that can be updated
        return rankingMapper.toDto(ranking);
    }

    /**
     * Partially update a ranking.
     *
     * @param rankingDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<RankingDTO> partialUpdate(RankingDTO rankingDTO) {
        log.debug("Request to partially update Ranking : {}", rankingDTO);

        return rankingRepository
            .findById(rankingDTO.getId())
            .map(existingRanking -> {
                rankingMapper.partialUpdate(existingRanking, rankingDTO);

                return existingRanking;
            })
            // .map(rankingRepository::save)
            .map(rankingMapper::toDto);
    }

    /**
     * Get all the rankings.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<RankingDTO> findAll() {
        log.debug("Request to get all Rankings");
        return rankingRepository.findAll().stream().map(rankingMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one ranking by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<RankingDTO> findOne(Long id) {
        log.debug("Request to get Ranking : {}", id);
        return rankingRepository.findById(id).map(rankingMapper::toDto);
    }

    /**
     * Delete the ranking by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Ranking : {}", id);
        rankingRepository.deleteById(id);
    }
}
