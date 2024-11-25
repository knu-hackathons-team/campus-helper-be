package com.soft.campushelper.global.resolver;

import com.soft.campushelper.global.annotation.Authenticate;
import com.soft.campushelper.global.constants.MessageConstants;
import com.soft.campushelper.global.exception.AuthenticationException;
import jakarta.servlet.http.HttpServletRequest;
import org.aspectj.bridge.Message;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 컨트롤러 메소드의 파라미터를 처리
 * @Authenticate 어노테이션이 붙은 파라미터를 처리
 * JwtInterceptor가 request에 저장한 memberId를 컨트롤러에서 쉽게 사용할 수 있게 변환
 */
public class LoginMemberArgumentResolver implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(Authenticate.class);
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
        String memberId = (String) request.getAttribute("memberId");

        if (memberId == null) {
            throw new AuthenticationException(MessageConstants.LOGIN_REQUIRED);
        }

        return Long.parseLong(memberId);
    }
}
