package com.teumgi.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;

public record SimpleRequest(@Schema(description = "내용") String content) {

}
