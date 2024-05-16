package com.teumgi.api.controller;

import com.teumgi.api.dto.ApiResult;
import com.teumgi.api.dto.AuthenticationResultDto;
import com.teumgi.api.error.UnauthorizedException;
import com.teumgi.api.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.security.SecurityRequirements;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${apiPrefix}")
@Tag(name = "인증 APIs")
@SecurityRequirements
public class AuthenticationRestController {

    private final Logger log = LoggerFactory.getLogger(getClass());
    private final UserService userService;

    public AuthenticationRestController( UserService userService) {
        this.userService = userService;
    }


    @DeleteMapping(path = "/auth/description")
    @Tag(name = "인증 APIs")
    @Operation(summary = "Jwt 토큰 로직 설명", description = """
        * 인증 API들을 제외한 모든 API는 헤더에 Authorization: Bearer {jwt} 를 포함해야 합니다.*
          아래는 jwt토큰 발급과 검증 과정을 설명합니다.
           
        1. 로그인을 성공하거나 회원가입을 하면 accessToken과 refreshToken을 응답받습니다. (뭐가 더 편할지 몰라서 바디,헤더에 둘다 넣어서 응답해줌)
        2. 클라이언트에서는 응답받은 accessToken과 refreshToken을 각각 저장해둡니다.
        3. 요청 헤더의 Authorization 필드에 발급받은 accessToken을 담아 요청합니다. *
            -- 만료 시 --
        4. 만약 만료시간이 지난 토큰으로 API를 호출하면 401 에러가 응답되며, 이 경우 '토큰 갱신 API'를 통해 accessToken을 갱신해야 합니다.
        5. 401에러를 응답받았다면, 저장해둔 accessToken과 refreshToken을 각각 'Authorization'헤더와 'Refresh-Token'헤더에 담아 refresh API에 토큰 갱신을 요청합니다.
        6. 갱신에 성공하면 만료된 accessToken을 새로운 accessToken으로 교체합니다.  
        7. 만약 refreshToken도 만료되었다면 '갱신 API'에서 401 에러가 응답되고 다시 로그인을 해야합니다.
        """)
    public void quizDescription() {
    }

    @PostMapping("auth")
    @Tag(name = "인증 APIs")
    @Operation(summary = "사용자 로그인 (API 토큰 필요없음)")
    public ApiResult<AuthenticationResultDto> authentication(HttpServletResponse response) {
        return null;
    }

    @Tag(name = "인증 APIs")
    @Operation(summary = "refreshToken으로 accessToken 갱신")
    @GetMapping("auth/refresh")
    @Parameters({
            @Parameter(name = "Refresh-Token", description = "로그인or회원가입 시 제공한 Refresh-Token", required = true, in = ParameterIn.HEADER),
            @Parameter(name = "Authorization", description = "로그인or회원가입 시 제공한 Access-Token", required = true, in = ParameterIn.HEADER)
    })
    public ApiResult<AuthenticationResultDto> refreshToken(HttpServletResponse response,
                                                           HttpServletRequest request) throws UnauthorizedException {

        return ApiResult.OK(new AuthenticationResultDto(null, null));

    }


}