package com.teumgi.api.dto;
import static org.springframework.beans.BeanUtils.copyProperties;

import com.teumgi.api.domain.UserInfoEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

public class AuthenticationResultDto {

    @Schema(description = "refresh 토큰", required = true)
    private String refreshToken;
    @Schema(description = "access 토큰", required = true)
    private String accessToken;
    @Schema(description = "사용자 정보", required = true)
    private UserDto user;

    public AuthenticationResultDto(UserInfoEntity userInfo, String accessToken) {

        this.user = new UserDto(userInfo);
        this.accessToken = accessToken;
        this.refreshToken = userInfo.getRefreshToken();
    }


    public String getAccessToken() {
        return accessToken;
    }


    public String getRefreshToken() {
        return refreshToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
            .append("accessToken", accessToken)
            .append("user", user)
            .toString();
    }

}