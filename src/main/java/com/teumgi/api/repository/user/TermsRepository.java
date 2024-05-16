package com.teumgi.api.repository.user;

import com.teumgi.api.domain.terms.TermsEntity;
import com.teumgi.api.domain.terms.TermsType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TermsRepository extends
    JpaRepository<TermsEntity, Long> {

    @Query(value = "select * from terms where id in (select MAX(id) from terms group by type)", nativeQuery = true)
    List<TermsEntity> findAllByMaxId();
    List<TermsEntity> findAllByIdIn(List<Long> ids);
    List<TermsEntity> findByTypeOrderByCreatedAtDesc(TermsType type);


    Optional<TermsEntity> findFirstByTypeOrderByCreatedAtDesc(TermsType termsType);


}
