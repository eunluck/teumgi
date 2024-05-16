package com.teumgi.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BaseDto {

    @Schema(description = "id")
    private Long id;
    @Schema(description = "생성일시")
    private String createdAt;
    @Schema(description = "수정일시")
    private String updatedAt;
    @Schema(description = "삭제일시")
    private String deletedAt;

}
