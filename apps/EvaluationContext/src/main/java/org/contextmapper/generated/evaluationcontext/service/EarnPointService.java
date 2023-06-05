package org.contextmapper.generated.evaluationcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.evaluationcontext.domain.EarnPoint;
import org.contextmapper.generated.evaluationcontext.repository.EarnPointRepository;
import org.contextmapper.generated.evaluationcontext.service.dto.EarnPointDTO;
import org.contextmapper.generated.evaluationcontext.service.mapper.EarnPointMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link EarnPoint}.
 */
@Service
@Transactional
public class EarnPointService {

    private final Logger log = LoggerFactory.getLogger(EarnPointService.class);

    private final EarnPointRepository earnPointRepository;

    private final EarnPointMapper earnPointMapper;

    public EarnPointService(EarnPointRepository earnPointRepository, EarnPointMapper earnPointMapper) {
        this.earnPointRepository = earnPointRepository;
        this.earnPointMapper = earnPointMapper;
    }

    /**
     * Save a earnPoint.
     *
     * @param earnPointDTO the entity to save.
     * @return the persisted entity.
     */
    public EarnPointDTO save(EarnPointDTO earnPointDTO) {
        log.debug("Request to save EarnPoint : {}", earnPointDTO);
        EarnPoint earnPoint = earnPointMapper.toEntity(earnPointDTO);
        earnPoint = earnPointRepository.save(earnPoint);
        return earnPointMapper.toDto(earnPoint);
    }

    /**
     * Update a earnPoint.
     *
     * @param earnPointDTO the entity to save.
     * @return the persisted entity.
     */
    public EarnPointDTO update(EarnPointDTO earnPointDTO) {
        log.debug("Request to update EarnPoint : {}", earnPointDTO);
        EarnPoint earnPoint = earnPointMapper.toEntity(earnPointDTO);
        earnPoint = earnPointRepository.save(earnPoint);
        return earnPointMapper.toDto(earnPoint);
    }

    /**
     * Partially update a earnPoint.
     *
     * @param earnPointDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<EarnPointDTO> partialUpdate(EarnPointDTO earnPointDTO) {
        log.debug("Request to partially update EarnPoint : {}", earnPointDTO);

        return earnPointRepository
            .findById(earnPointDTO.getId())
            .map(existingEarnPoint -> {
                earnPointMapper.partialUpdate(existingEarnPoint, earnPointDTO);

                return existingEarnPoint;
            })
            .map(earnPointRepository::save)
            .map(earnPointMapper::toDto);
    }

    /**
     * Get all the earnPoints.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<EarnPointDTO> findAll() {
        log.debug("Request to get all EarnPoints");
        return earnPointRepository.findAll().stream().map(earnPointMapper::toDto).collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one earnPoint by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<EarnPointDTO> findOne(Long id) {
        log.debug("Request to get EarnPoint : {}", id);
        return earnPointRepository.findById(id).map(earnPointMapper::toDto);
    }

    /**
     * Delete the earnPoint by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete EarnPoint : {}", id);
        earnPointRepository.deleteById(id);
    }
}
