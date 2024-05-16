package com.teumgi.api.domain;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.teumgi.api.configure.CryptoConverter;
import com.teumgi.api.domain.terms.TermsHistoryEntity;
import com.teumgi.api.dto.Email;
import com.teumgi.api.dto.Gender;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Entity(name = "user_info")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@SQLDelete(sql = "UPDATE user_info SET deleted_at = NOW() and status = 0 WHERE id = ?")
@Where(clause = "deleted_at IS NULL")
public class UserInfoEntity extends BaseEntity  {

    private String name;
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "address", column = @Column(name = "email")),
        @AttributeOverride(name = "emailType", column = @Column(name = "email_type"))
    })
    private Email email;

    @Convert(converter = CryptoConverter.class)
    @Column(columnDefinition = "TEXT")
    private String credentials;
    private String password;
    private String phone;
    private int status;

    private String refreshToken;

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    @OneToMany(mappedBy = "user", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @OrderBy(value = "createdAt DESC")
    private List<TermsHistoryEntity> termsHistory = Lists.newArrayList();

    @Enumerated(value = EnumType.STRING)
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "user_roles")
    private Set<Role> roles = Sets.newHashSet();

    private String profileImage;
    @Embedded
    @AttributeOverrides({
        @AttributeOverride(name = "token", column = @Column(name = "fcm_token")),
        @AttributeOverride(name = "regDateTime", column = @Column(name = "fcm_reg_date_time")),
    })
    private PushToken pushToken;
    private String awsArn;
    private String withdrawalReason;

    private LocalDateTime lastAccessAt;

    public void access(){
        lastAccessAt = LocalDateTime.now();
        this.status = status == 2 ? 1 :  status;
    }

    public Optional<PushToken> getPushToken() {
        return Optional.ofNullable(pushToken);
    }

    public PushToken newPushToken(String pushToken) {
        this.pushToken = new PushToken(pushToken, LocalDateTime.now());
        return this.pushToken;
    }

    public boolean canSavePushToken(String pushToken) {
        return getPushToken().isEmpty() || getPushToken().map(PushToken::getToken).filter(pushToken::equals).isEmpty();
    }

    public void withdrawal(String reason) {
        this.withdrawalReason = reason;
        this.status = 0;
        setDeletedAt(LocalDateTime.now(ZoneId.of("UTC")));
    }

    public void dormantAccount() {
        this.status = 2;
    }
}
