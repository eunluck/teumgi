package com.teumgi.api.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Embeddable
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PushToken {
    @Schema(description = "앱에서 발급한 토큰", required = true)
    private String token;
    @Schema(description = "토큰을 등록한 날짜", required = true)
    private LocalDateTime regDateTime;
}
