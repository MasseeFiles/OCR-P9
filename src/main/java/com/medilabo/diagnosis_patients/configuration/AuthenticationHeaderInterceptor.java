package com.medilabo.diagnosis_patients.configuration;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * Interceptor de toutes les requetes http entrantes du
 * microservice. Utilisé pour vérifier si un header Authorization
 * a été reçu et l'afficher.
 */
@Component
public class AuthenticationHeaderInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String headerAuthorization = request.getHeader("Authorization");
        if (headerAuthorization != null && !headerAuthorization.isEmpty()) {
            System.out.println("Authorization header found in incoming request: " + headerAuthorization);

        } else {
            System.out.println("No authorization header found in incoming request.");
        }
        return true;
    }

}
