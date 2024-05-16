package com.teumgi.api.configure.support;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.PageRequest;

@ParameterObject
public interface Pageable {

    @Schema(description = "페이지 번호", defaultValue = "0")
    @Parameter(description = "페이지 번호", example = "0")
    int offset();

    @Schema(description = "페이지 당 데이터 수", defaultValue = "5")
    @Parameter(description = "페이지 당 데이터 수", example = "5")
    int limit();

    @Schema(description = "정렬 기준", defaultValue = "id")
    @Parameter(description = "정렬기준", example = "id")
    String sortedBy();

    @Schema(description = "정렬 방식", defaultValue = "DESC")
    @Parameter(description = "정렬 방식", example = "DESC")
    String direction();


    PageRequest toPageRequest();

}