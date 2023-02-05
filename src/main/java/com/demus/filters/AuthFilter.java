package com.demus.filters;

import com.demus.model.user.Session;
import com.demus.model.user.User;
import com.demus.repository.SessionRepository;
import com.demus.repository.UserRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import java.io.IOException;
import java.util.Optional;

@Component
public class AuthFilter extends OncePerRequestFilter {
    final
    SessionRepository sessionRepository;
    final
    UserRepository userRepository;

    public AuthFilter(SessionRepository sessionRepository, UserRepository userRepository) {
        this.sessionRepository = sessionRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Order(1)
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Cookie cookie = WebUtils.getCookie(request, "SESSION");
        if (cookie != null) {
            Optional<Session> session = sessionRepository.findById(cookie.getValue());
            if (session.isEmpty())
                throw new ServletException("Unauthorized user");
            User user = userRepository.findById(session.get().getSpotifyId()).orElse(null);
            request.setAttribute("token", user.getAccessToken());
            request.setAttribute("user", user);
            filterChain.doFilter(request, response);
        }
        else
            throw new ServletException("There is no session. Please sign in!");
    }
}
