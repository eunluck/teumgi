package com.teumgi.api.configure.support;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Schema;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Parameter(in = ParameterIn.QUERY
    , description = "페이지 번호"
    , name = "offset"
    , schema = @Schema(type = "integer", defaultValue = "0"))
@Parameter(in = ParameterIn.QUERY
    , description = "페이지 당 데이터 수"
    , name = "limit"
    , schema = @Schema(type = "integer", defaultValue = "5"))
@Parameter(in = ParameterIn.QUERY
    , description = "정렬기준을 정한다.(default: id)"
    , name = "sortedBy")
@Parameter(in = ParameterIn.QUERY
    , description = "정렬 방식을 정한다(ASC or DESC)"
    , name = "direction")
public @interface PageableParameter {

}