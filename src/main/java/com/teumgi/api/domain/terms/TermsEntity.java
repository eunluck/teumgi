package com.teumgi.api.domain.terms;

import com.teumgi.api.domain.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.format.DateTimeFormatter;

@Entity(name = "terms")
@AllArgsConstructor
@Getter
@Setter
@EntityListeners(value = AuditingEntityListener.class)
@NoArgsConstructor
public class TermsEntity extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private TermsType type;
    private String writer;
    private String modifier;
    @Column(columnDefinition = "TEXT")
    private String content;

    public void update(String modifier, String content) {
        this.modifier = modifier;
        this.content = content;

    }

    public String getVersion() {
        return getCreatedAt().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) +"_"+ getId().toString();
    }
}
