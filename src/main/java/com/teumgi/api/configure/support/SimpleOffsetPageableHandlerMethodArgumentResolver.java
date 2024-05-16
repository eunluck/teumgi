package com.teumgi.api.configure.support;

import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import java.util.Objects;

import static java.lang.Math.min;
import static org.apache.commons.lang3.math.NumberUtils.toInt;
import static org.springframework.util.StringUtils.hasText;

public class SimpleOffsetPageableHandlerMethodArgumentResolver implements
    HandlerMethodArgumentResolver {

    private static final String DEFAULT_OFFSET_PARAMETER = "offset";

    private static final String DEFAULT_LIMIT_PARAMETER = "limit";
    private static final String DEFAULT_DEFAULT_SORTED_PARAMETER = "sortedBy";
    private static final String DEFAULT_DIRECTION_PARAMETER = "direction";

    private static final int DEFAULT_MAX_LIMIT_SIZE = 100;
    private static final String DEFAULT_SORTED_BY = "id";
    private final String sortedParameterName = DEFAULT_DEFAULT_SORTED_PARAMETER;
    private final String directionParameterName = DEFAULT_DIRECTION_PARAMETER;
    private SimpleOffsetPageRequest fallbackPageable;
    private String offsetParameterName = DEFAULT_OFFSET_PARAMETER;
    private String limitParameterName = DEFAULT_LIMIT_PARAMETER;

    public static String makeSortedBy(String sortedBy) {
        if (Objects.isNull(sortedBy) || sortedBy.isEmpty()) {
            return DEFAULT_SORTED_BY;
        }
        return sortedBy;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Pageable.class.isAssignableFrom(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(
        MethodParameter methodParameter,
        ModelAndViewContainer mavContainer,
        NativeWebRequest webRequest,
        WebDataBinderFactory binderFactory
    ) {
        String offsetString = webRequest.getParameter(offsetParameterName);
        String limitString = webRequest.getParameter(limitParameterName);
        String sortedString = webRequest.getParameter(sortedParameterName);
        String directionString = webRequest.getParameter(directionParameterName);

        boolean pageAndLimitGiven =
            hasText(offsetString) && hasText(limitString) && hasText(sortedString) && hasText(
                directionString);

        if (!pageAndLimitGiven && fallbackPageable == null) {
            return null;
        }

        int offset =
            hasText(offsetString) ? parseAndApplyBoundaries(offsetString, Integer.MAX_VALUE)
                : fallbackPageable.offset();
        int limit =
            hasText(limitString) ? parseAndApplyBoundaries(limitString, DEFAULT_MAX_LIMIT_SIZE)
                : fallbackPageable.limit();
        String sorted = hasText(sortedString) ? sortedString : fallbackPageable.sortedBy();
        String direction =
            hasText(directionString) ? directionString : fallbackPageable.direction();

        limit = limit < 1 ? fallbackPageable.limit() : limit;
        limit = min(limit, DEFAULT_MAX_LIMIT_SIZE);

        if (offset < 0) {
            offset = 0;
        }
        if (limit < 1 ) {
            limit = 1;
        }

        return new SimpleOffsetPageRequest(offset, limit, makeSortedBy(sorted), direction);
    }

    private int parseAndApplyBoundaries(String parameter, int upper) {
        int parsed = toInt(parameter, 0);
        return parsed < 0 ? 0 : min(parsed, upper);
    }

    public void setFallbackPageable(SimpleOffsetPageRequest fallbackPageable) {
        this.fallbackPageable = fallbackPageable;
    }

    public void setOffsetParameterName(String offsetParameterName) {
        this.offsetParameterName = offsetParameterName;
    }

    public void setLimitParameterName(String limitParameterName) {
        this.limitParameterName = limitParameterName;
    }

}