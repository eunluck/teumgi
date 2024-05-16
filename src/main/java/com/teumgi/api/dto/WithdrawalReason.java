package com.teumgi.api.dto;


import io.swagger.v3.oas.annotations.media.Schema;

public record WithdrawalReason(@Schema(description = "탈퇴사유") String reason) {
}
