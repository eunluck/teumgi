package com.teumgi.api.domain.terms;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.teumgi.api.domain.UserInfoEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity(name = "terms_history")
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(value = AuditingEntityListener.class)
public class TermsHistoryEntity {

    @EmbeddedId
    private TermsHistoryId id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @MapsId("userId")
    private UserInfoEntity user;
    private Boolean agreed;
    private Long termsId;
    @CreatedDate
    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public TermsHistoryEntity() {
    }

    public static TermsHistoryEntity of(TermsType termsType, UserInfoEntity user,Long termsId, String version,
        boolean termsAgreed) {
        return new TermsHistoryEntity(
            new TermsHistoryId(
                user.getId(), termsType, version),
            user,
            termsAgreed,
            termsId,
            LocalDateTime.now());
    }

    @AllArgsConstructor
    @Getter
    @Setter
    @Embeddable
    @EqualsAndHashCode
    @NoArgsConstructor
    public static class TermsHistoryId implements Serializable {

        private Long userId;
        @Enumerated(EnumType.STRING)
        private TermsType termsType;
        private String version;
    }

}
