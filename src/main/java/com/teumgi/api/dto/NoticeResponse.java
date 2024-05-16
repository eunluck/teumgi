package com.teumgi.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;
import java.util.List;

public record NoticeResponse(
    @Schema(description = "제목")
    String title,
    @Schema(description = "본문")
    String contents,
    @Schema(description = "작성자")
    String writer,
    @Schema(description = "활성여부")
    Boolean isActive,
    @Schema(description = "게시일시")
    LocalDateTime startDateTime,
    @Schema(description = "생성일시")
    LocalDateTime createdAt,
    @Schema(description = "수정일시")
    LocalDateTime updatedAt,
    @Schema(description = "공지 Id")
    Long id) {

}
