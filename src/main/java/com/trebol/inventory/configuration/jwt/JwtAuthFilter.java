package com.trebol.inventory.configuration.jwt;

import com.trebol.inventory.domain.model.Role;
import com.trebol.inventory.utils.Constants;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.List;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        String authorizationHeader = request.getHeader(Constants.HEADER_AUTH);

        if (authorizationHeader != null && authorizationHeader.startsWith(Constants.TOKEN_PREFIX)) {
            String token = authorizationHeader.substring(Constants.TOKEN_PREFIX.length());
            Claims claims = jwtService.getClaimsFromToken(token);
            System.out.println("Token: " + token);
            String username = claims.getSubject();
            System.out.println("Username: " + username);
            String roleStr = claims.get(Constants.ROLE, String.class);
            System.out.println("Role: " + roleStr);
            Role role = Role.valueOf(roleStr);

            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    username, null, List.of(new SimpleGrantedAuthority(Constants.ROLE_ +  role.name())));

            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());

        }
        filterChain.doFilter(request, response);
    }
}
