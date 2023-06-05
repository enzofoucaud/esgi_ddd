package org.contextmapper.generated.questioncontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.questioncontext.domain.ResourceRejectedAssociationEvent;
import org.contextmapper.generated.questioncontext.repository.ResourceRejectedAssociationEventRepository;
import org.contextmapper.generated.questioncontext.service.dto.ResourceRejectedAssociationEventDTO;
import org.contextmapper.generated.questioncontext.service.mapper.ResourceRejectedAssociationEventMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ResourceRejectedAssociationEvent}.
 */
@Service
@Transactional
public class ResourceRejectedAssociationEventService {

    private final Logger log = LoggerFactory.getLogger(ResourceRejectedAssociationEventService.class);

    private final ResourceRejectedAssociationEventRepository resourceRejectedAssociationEventRepository;

    private final ResourceRejectedAssociationEventMapper resourceRejectedAssociationEventMapper;

    public ResourceRejectedAssociationEventService(
        ResourceRejectedAssociationEventRepository resourceRejectedAssociationEventRepository,
        ResourceRejectedAssociationEventMapper resourceRejectedAssociationEventMapper
    ) {
        this.resourceRejectedAssociationEventRepository = resourceRejectedAssociationEventRepository;
        this.resourceRejectedAssociationEventMapper = resourceRejectedAssociationEventMapper;
    }

    /**
     * Save a resourceRejectedAssociationEvent.
     *
     * @param resourceRejectedAssociationEventDTO the entity to save.
     * @return the persisted entity.
     */
    public ResourceRejectedAssociationEventDTO save(ResourceRejectedAssociationEventDTO resourceRejectedAssociationEventDTO) {
        log.debug("Request to save ResourceRejectedAssociationEvent : {}", resourceRejectedAssociationEventDTO);
        ResourceRejectedAssociationEvent resourceRejectedAssociationEvent = resourceRejectedAssociationEventMapper.toEntity(
            resourceRejectedAssociationEventDTO
        );
        resourceRejectedAssociationEvent = resourceRejectedAssociationEventRepository.save(resourceRejectedAssociationEvent);
        return resourceRejectedAssociationEventMapper.toDto(resourceRejectedAssociationEvent);
    }

    /**
     * Update a resourceRejectedAssociationEvent.
     *
     * @param resourceRejectedAssociationEventDTO the entity to save.
     * @return the persisted entity.
     */
    public ResourceRejectedAssociationEventDTO update(ResourceRejectedAssociationEventDTO resourceRejectedAssociationEventDTO) {
        log.debug("Request to update ResourceRejectedAssociationEvent : {}", resourceRejectedAssociationEventDTO);
        ResourceRejectedAssociationEvent resourceRejectedAssociationEvent = resourceRejectedAssociationEventMapper.toEntity(
            resourceRejectedAssociationEventDTO
        );
        resourceRejectedAssociationEvent = resourceRejectedAssociationEventRepository.save(resourceRejectedAssociationEvent);
        return resourceRejectedAssociationEventMapper.toDto(resourceRejectedAssociationEvent);
    }

    /**
     * Partially update a resourceRejectedAssociationEvent.
     *
     * @param resourceRejectedAssociationEventDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ResourceRejectedAssociationEventDTO> partialUpdate(
        ResourceRejectedAssociationEventDTO resourceRejectedAssociationEventDTO
    ) {
        log.debug("Request to partially update ResourceRejectedAssociationEvent : {}", resourceRejectedAssociationEventDTO);

        return resourceRejectedAssociationEventRepository
            .findById(resourceRejectedAssociationEventDTO.getId())
            .map(existingResourceRejectedAssociationEvent -> {
                resourceRejectedAssociationEventMapper.partialUpdate(
                    existingResourceRejectedAssociationEvent,
                    resourceRejectedAssociationEventDTO
                );

                return existingResourceRejectedAssociationEvent;
            })
            .map(resourceRejectedAssociationEventRepository::save)
            .map(resourceRejectedAssociationEventMapper::toDto);
    }

    /**
     * Get all the resourceRejectedAssociationEvents.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ResourceRejectedAssociationEventDTO> findAll() {
        log.debug("Request to get all ResourceRejectedAssociationEvents");
        return resourceRejectedAssociationEventRepository
            .findAll()
            .stream()
            .map(resourceRejectedAssociationEventMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one resourceRejectedAssociationEvent by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ResourceRejectedAssociationEventDTO> findOne(Long id) {
        log.debug("Request to get ResourceRejectedAssociationEvent : {}", id);
        return resourceRejectedAssociationEventRepository.findById(id).map(resourceRejectedAssociationEventMapper::toDto);
    }

    /**
     * Delete the resourceRejectedAssociationEvent by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ResourceRejectedAssociationEvent : {}", id);
        resourceRejectedAssociationEventRepository.deleteById(id);
    }
}
