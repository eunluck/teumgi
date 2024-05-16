package com.teumgi.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Data
public class Address {

    @Schema(description = "지번 주소", required = true)
    private String addressJb;
    @Schema(description = "도로명 주소", required = true)
    private String addressSt;
    @Schema(description = "기타")
    private String addressEtc;


}
