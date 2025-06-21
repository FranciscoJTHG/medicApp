package com.teleMedicina.teleMedicina.security;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.teleMedicina.teleMedicina.models.usuarios.Usuario;
import com.teleMedicina.teleMedicina.models.usuarios.UsuarioRepository;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilter extends OncePerRequestFilter {

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws IOException, ServletException {

       // Ignorar las rutas públicas
        // if ("/login".equals(request.getRequestURI()) || "/registro".equals(request.getRequestURI())) {
        //     filterChain.doFilter(request, response);
        //     return;
        // }

        // Lista de rutas públicas
        String[] publicPaths = {
            "/registro",
            "/login",
            "/v3/api-docs",
            "/v3/api-docs/swagger-config",
            "/swagger-ui/",
            "/swagger-ui.html",
            "/swagger-resources",
            "/swagger-resources/",
            "/webjars/"
        };

        // Verificar si la ruta actual es pública
        String requestPath = request.getRequestURI();
        for (String path : publicPaths) {
            if (requestPath.startsWith(path)) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.replace("Bearer ", "");

            try {
                String nombreUsuario = tokenService.getSubject(token);

                if (nombreUsuario != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                    var usuario = usuarioRepository.findByEmail(nombreUsuario);

                    if (usuario != null) {
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    } else {
                        // Usuario no encontrado en la base de datos
                        logger.error("Usuario no encontrado en la base de datos: " + nombreUsuario);
                        setResponse(response, HttpServletResponse.SC_NOT_FOUND, "Usuario no encontrado");
                        return;
                    }
                }
            } catch (TokenExpiredException ex) {
                logger.error("Token expirado: " + ex.getMessage());
                setResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "Token expirado");
                return;
            } catch (JWTVerificationException ex) {
                logger.error("Token JWT inválido: " + ex.getMessage());
                setResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "Token inválido");
                return;
            } catch (Exception ex) {
                logger.error("Error en la autenticación: " + ex.getMessage(), ex);
                setResponse(response, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error en la autenticación");
                return;
            }
        } else {
            // No se proporcionó token
            logger.warn("No se proporcionó token de autorización");
            setResponse(response, HttpServletResponse.SC_UNAUTHORIZED, "Token de autorización requerido");
            return;
        }
        
        filterChain.doFilter(request, response);
    }

    // Método auxiliar para manejar respuestas
    private void setResponse(HttpServletResponse response, int statusCode, String message) throws IOException {
        response.setStatus(statusCode);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        String jsonResponse = String.format("{\"error\": \"%s\", \"status\": %d}", message, statusCode);
        response.getWriter().write(jsonResponse);
    }
}
