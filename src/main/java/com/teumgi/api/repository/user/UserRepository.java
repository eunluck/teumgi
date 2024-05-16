package com.teumgi.api.repository.user;

import com.teumgi.api.domain.UserInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UserInfoEntity, Long> {

}
