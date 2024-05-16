package com.teumgi.api.domain.terms;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.Arrays;
import java.util.Set;

@JsonFormat(shape = Shape.STRING)
public enum TermsType {
    ACCOUNT_TERMS("계정 약관",true),
    USE_OF_PERSONAL_INFO("개인정보 수집 및 이용",true),
    PRIVACY_POLICY("개인정보 처리 방침",false),
    MARKETING("마케팅 활용 동의",false),
    OPEN_SOURCE_LICENSE("오픈 소스 라이선스",false),
    USE_LOCATION_INFO("장소 정보 수집 및 이용",false),
    ACCOUNT_ACTIVATION("계정 활성화",false);
    @Schema(description = "약관제목")
    private String kor;
    @Schema(description = "약관 필수 동의 여부")
    private Boolean isRequired;
    TermsType(String kor, Boolean isRequired) {
        this.kor = kor;
        this.isRequired = isRequired;
    }

    public static Set<TermsType> getTermsTypeList() {
        return Set.of(values());
    }

    
    public Boolean isRequired(){
        return isRequired;
        
    }

    public static TermsType of(String code){
        return Arrays.stream(TermsType.values())
            .filter(personalityType -> personalityType.name().equalsIgnoreCase(code))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 코드입니다."));
    }
}