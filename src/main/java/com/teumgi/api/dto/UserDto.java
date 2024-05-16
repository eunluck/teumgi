package com.teumgi.api.dto;

import com.google.common.collect.Sets;
import com.teumgi.api.domain.Role;
import com.teumgi.api.domain.UserInfoEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class UserDto {


    @Schema(description = "PK", required = true)
    private Long id;
    @Schema(description = "사용자명", required = true)
    private String name;
    @Schema(description = "생일")
    private LocalDate birth;
    @Schema(description = "이메일", required = true)
    private Email email;
    @Schema(description = "성별", required = true)
    private Gender gender;
    @Schema(description = "주소", required = true)
    private Address address;
    @Schema(description = "전화번호")
    private String phone;
    @Schema(description = "회원 프로필 이미지")
    private String profileImage;
    @Schema(description = "계정상태(0=탈퇴, 1 정상, 2 휴면)")
    private int status;
    @Schema(description = "가입일자")
    private LocalDateTime createdAt;
    @Schema(description = "수정일자")
    private LocalDateTime updatedAt;
    @Schema(description = "탈퇴일자")
    private LocalDateTime deletedAt;
    @Schema(description = "권한목록")
    private Set<Role> roles = Sets.newHashSet();

    public UserDto(UserInfoEntity source) {
        BeanUtils.copyProperties(source, this);
    }
}