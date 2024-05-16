package com.teumgi.api.repository.user;

import com.teumgi.api.domain.terms.TermsHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TermsHistoryRepository extends
    JpaRepository<TermsHistoryEntity, TermsHistoryEntity.TermsHistoryId> {




}
