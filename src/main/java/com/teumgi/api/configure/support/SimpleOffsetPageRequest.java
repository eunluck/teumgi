package com.teumgi.api.configure.support;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.ToString;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.util.Objects;

@ParameterObject
@ToString
public class SimpleOffsetPageRequest implements Pageable {

    @Parameter(description = "페이지 번호", example = "0")
    @Schema(description = "페이지 번호", defaultValue = "0")
    private final int offset;
    @Schema(description = "페이지 당 데이터 수", defaultValue = "5")
    @Parameter(description = "페이지 당 데이터 수", example = "5")

    private final int limit;
    @Schema(description = "정렬 기준", defaultValue = "id")
    private final String sortedBy;

    @Schema(description = "정렬 방식", defaultValue = "DESC")
    private final String direction;

    public SimpleOffsetPageRequest() {
        this(0, 5, "id", "DESC");
    }

    public SimpleOffsetPageRequest(int offset, int limit, String sortedBy, String direction) {

        this.offset = offset;
        this.limit = limit;
        this.sortedBy = sortedBy;
        this.direction = direction;
    }

    @Override
    public int offset() {
        return offset;
    }

    @Override
    public int limit() {
        return limit;
    }

    @Override
    public String sortedBy() {
        return sortedBy;
    }

    @Override
    public String direction() {
        return direction;
    }

    @Override
    public PageRequest toPageRequest() {
        return PageRequest.of(offset, limit, makeDirection(direction), sortedBy);
    }


    public Sort.Direction makeDirection(String direction) {
        if (Objects.isNull(direction) || direction.isEmpty()) {
            return Sort.Direction.ASC;
        }
        return Sort.Direction.fromOptionalString(direction)
            .orElse(Sort.Direction.DESC);
    }
}