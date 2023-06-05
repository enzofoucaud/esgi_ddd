package org.contextmapper.generated.statcontext.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.contextmapper.generated.statcontext.domain.ViewRankingCommand;
import org.contextmapper.generated.statcontext.repository.ViewRankingCommandRepository;
import org.contextmapper.generated.statcontext.service.dto.ViewRankingCommandDTO;
import org.contextmapper.generated.statcontext.service.mapper.ViewRankingCommandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link ViewRankingCommand}.
 */
@Service
@Transactional
public class ViewRankingCommandService {

    private final Logger log = LoggerFactory.getLogger(ViewRankingCommandService.class);

    private final ViewRankingCommandRepository viewRankingCommandRepository;

    private final ViewRankingCommandMapper viewRankingCommandMapper;

    public ViewRankingCommandService(
        ViewRankingCommandRepository viewRankingCommandRepository,
        ViewRankingCommandMapper viewRankingCommandMapper
    ) {
        this.viewRankingCommandRepository = viewRankingCommandRepository;
        this.viewRankingCommandMapper = viewRankingCommandMapper;
    }

    /**
     * Save a viewRankingCommand.
     *
     * @param viewRankingCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public ViewRankingCommandDTO save(ViewRankingCommandDTO viewRankingCommandDTO) {
        log.debug("Request to save ViewRankingCommand : {}", viewRankingCommandDTO);
        ViewRankingCommand viewRankingCommand = viewRankingCommandMapper.toEntity(viewRankingCommandDTO);
        viewRankingCommand = viewRankingCommandRepository.save(viewRankingCommand);
        return viewRankingCommandMapper.toDto(viewRankingCommand);
    }

    /**
     * Update a viewRankingCommand.
     *
     * @param viewRankingCommandDTO the entity to save.
     * @return the persisted entity.
     */
    public ViewRankingCommandDTO update(ViewRankingCommandDTO viewRankingCommandDTO) {
        log.debug("Request to update ViewRankingCommand : {}", viewRankingCommandDTO);
        ViewRankingCommand viewRankingCommand = viewRankingCommandMapper.toEntity(viewRankingCommandDTO);
        viewRankingCommand = viewRankingCommandRepository.save(viewRankingCommand);
        return viewRankingCommandMapper.toDto(viewRankingCommand);
    }

    /**
     * Partially update a viewRankingCommand.
     *
     * @param viewRankingCommandDTO the entity to update partially.
     * @return the persisted entity.
     */
    public Optional<ViewRankingCommandDTO> partialUpdate(ViewRankingCommandDTO viewRankingCommandDTO) {
        log.debug("Request to partially update ViewRankingCommand : {}", viewRankingCommandDTO);

        return viewRankingCommandRepository
            .findById(viewRankingCommandDTO.getId())
            .map(existingViewRankingCommand -> {
                viewRankingCommandMapper.partialUpdate(existingViewRankingCommand, viewRankingCommandDTO);

                return existingViewRankingCommand;
            })
            .map(viewRankingCommandRepository::save)
            .map(viewRankingCommandMapper::toDto);
    }

    /**
     * Get all the viewRankingCommands.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<ViewRankingCommandDTO> findAll() {
        log.debug("Request to get all ViewRankingCommands");
        return viewRankingCommandRepository
            .findAll()
            .stream()
            .map(viewRankingCommandMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one viewRankingCommand by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<ViewRankingCommandDTO> findOne(Long id) {
        log.debug("Request to get ViewRankingCommand : {}", id);
        return viewRankingCommandRepository.findById(id).map(viewRankingCommandMapper::toDto);
    }

    /**
     * Delete the viewRankingCommand by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete ViewRankingCommand : {}", id);
        viewRankingCommandRepository.deleteById(id);
    }
}
