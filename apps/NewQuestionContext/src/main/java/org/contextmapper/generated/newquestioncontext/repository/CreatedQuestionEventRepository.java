package org.contextmapper.generated.newquestioncontext.repository;

import org.contextmapper.generated.newquestioncontext.domain.CreatedQuestionEvent;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the CreatedQuestionEvent entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CreatedQuestionEventRepository extends JpaRepository<CreatedQuestionEvent, Long> {}
