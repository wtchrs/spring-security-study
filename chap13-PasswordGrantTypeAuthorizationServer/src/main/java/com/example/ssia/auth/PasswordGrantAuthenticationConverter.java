package com.example.ssia.auth;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.AuthorizationGrantType;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2ErrorCodes;
import org.springframework.security.oauth2.core.endpoint.OAuth2ParameterNames;
import org.springframework.security.web.authentication.AuthenticationConverter;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.Arrays;
import java.util.Map;

public class PasswordGrantAuthenticationConverter implements AuthenticationConverter {

    public static final AuthorizationGrantType PASSWORD_GRANT_TYPE = new AuthorizationGrantType("password");

    @Override
    public Authentication convert(HttpServletRequest request) {
        String grantType = request.getParameter(OAuth2ParameterNames.GRANT_TYPE);
        if (!PASSWORD_GRANT_TYPE.getValue().equals(grantType)) {
            return null;
        }

        Authentication clientPrincipal = SecurityContextHolder.getContext().getAuthentication();

        if (clientPrincipal == null) {
            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_CLIENT);
        }

        MultiValueMap<String, String> parameters = getParameters(request);

//        if (!clientPrincipal.getName().equals(parameters.getFirst(OAuth2ParameterNames.CLIENT_ID))) {
//            throw new OAuth2AuthenticationException(OAuth2ErrorCodes.INVALID_CLIENT);
//        }

        return new PasswordGrantAuthenticationToken(
                clientPrincipal, parameters.getFirst(OAuth2ParameterNames.USERNAME),
                parameters.getFirst(OAuth2ParameterNames.PASSWORD));
    }

    private MultiValueMap<String, String> getParameters(HttpServletRequest request) {
        Map<String, String[]> parameterMap = request.getParameterMap();
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>(parameterMap.size());
        parameterMap.forEach(
                (key, values) -> Arrays.stream(values).forEachOrdered(value -> parameters.add(key, value)));
        return parameters;
    }
}
