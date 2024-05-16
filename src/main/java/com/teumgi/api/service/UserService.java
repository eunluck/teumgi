package com.teumgi.api.service;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.teumgi.api.domain.UserInfoEntity;
import com.teumgi.api.domain.terms.TermsEntity;
import com.teumgi.api.dto.Email;
import com.teumgi.api.repository.user.TermsHistoryRepository;
import com.teumgi.api.repository.user.TermsRepository;
import com.teumgi.api.repository.user.UserRepository;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;
import static org.apache.commons.lang3.StringUtils.isNotBlank;

@Service
@RequiredArgsConstructor
public class UserService {
    //private final PasswordEncoder passwordEncoder;
    private final TermsHistoryRepository termsHistoryRepository;
    private final TermsRepository termsRepository;
    private final UserRepository userRepository;

    @Transactional
    public void accessUser(UserInfoEntity userInfo) {
        userInfo.access();
    }

    @Transactional
    public UserInfoEntity updateName(UserInfoEntity user, String name) {
        checkArgument(isNotBlank(name), "name must be provided.");
        user.setName(name);
        return user;
    }

    @Transactional
    public UserInfoEntity updateProfileImage(UserInfoEntity user, String imageUrl) {
        checkArgument(isNotBlank(imageUrl), "imageUrl must be provided.");
        user.setProfileImage(imageUrl);
        return user;
    }


    @Transactional(readOnly = true)
    public List<TermsEntity> loadTerms() {

        return termsRepository.findAllByMaxId();

    }
}
