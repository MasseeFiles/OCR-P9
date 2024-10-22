package com.medilabo.diagnosis_patients.configuration;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Filter responsable de la gestion des tokens JWT transmis par les requetes HTTP entrantes.
 *
 * Il permet de vérifier si un token JWT est présent dans le Authorization header de la requete,
 * de le valider, et de retourner un objet Authentication dans le SecurityContextHolder
 * basé sur les claims du token.
 *
 * @see JwtUtil
 */
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final Logger logger = LogManager.getLogger("JwtAuthenticationFilter");

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        String authHeader = request.getHeader("Authorization");

        //JWT token non présent dans le header de la requete entrante
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {

            logger.info("No bearer token found in incoming request");

            chain.doFilter(request, response);
            return;
        }

        // JWT token présent dans le header de la requete entrante
        String jwt = authHeader.substring(7);

        logger.info("Bearer token found in incoming request: " + jwt);

        if (jwtUtil.isTokenExpired(jwt)) {
            Authentication authentication = jwtUtil.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
        chain.doFilter(request, response);
    }

}
