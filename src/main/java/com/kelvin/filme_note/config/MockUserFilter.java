package com.kelvin.filme_note.config;

import com.kelvin.filme_note.domain.model.Role;
import com.kelvin.filme_note.domain.model.User;
import jakarta.servlet.*;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.UUID;

@Component
public class MockUserFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Pega o header X-Mock-User-Id  se tiver
        String userIdHeader = request instanceof jakarta.servlet.http.HttpServletRequest
                ? ((jakarta.servlet.http.HttpServletRequest) request).getHeader("X-Mock-User-Id")
                : null;

        User mockUser = new User();
        mockUser.setId(userIdHeader != null ? UUID.fromString(userIdHeader) : UUID.randomUUID());
        mockUser.setName("Mock User");
        mockUser.setRole(Role.USER);
        mockUser.setEmail("mockuser@example.com");

        // Adiciona o usuário na requisição como atributo
        request.setAttribute("user", mockUser);

        chain.doFilter(request, response);
    }
}
