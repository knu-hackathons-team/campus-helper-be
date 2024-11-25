package com.soft.campushelper.global.interceptor;

import com.soft.campushelper.global.auth.JwtProvider;
import com.soft.campushelper.global.exception.AuthenticationException;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 모든 HTTP 요청에 대해 인증 처리를 수행
 * 요청 헤더에서 JWT 토큰을 추출하고 검증
 * 토큰에서 추출한 사용자 정보(memberId, role)를 request에 저장
 * 컨트롤러 실행 전에 동작 (preHandle)
 */
@Slf4j
@Component
public class JwtInterceptor implements HandlerInterceptor {

    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String TOKEN_PREFIX = "Bearer ";
    private final JwtProvider jwtProvider;

    public JwtInterceptor(JwtProvider jwtProvider) {
        this.jwtProvider = jwtProvider;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
                             Object handler) throws Exception {
        String authHeader = request.getHeader(HEADER_AUTHORIZATION);
        if (authHeader == null) {
            return true;
        }
        if (!authHeader.startsWith(TOKEN_PREFIX)) {
            throw new AuthenticationException("유효하지 않은 토큰입니다.");
        }

        Claims claim = jwtProvider.getClaim(authHeader.substring(7));
        request.setAttribute("memberId", claim.getSubject());
        request.setAttribute("role", claim.get("role"));

        log.info("member Id : {}", claim.getSubject());
        return true;
    }
}