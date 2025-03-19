package com.example.vti.security.filters;

import com.example.vti.exceptions.ErrorResponse;
import com.example.vti.exceptions.JwtAuthenticationException;
import com.example.vti.security.jwt.JwtUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

	@Autowired
	private JwtUtils jwtUtils;

	@Autowired
	private UserDetailsService userDetailsService;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		try {
			String header = request.getHeader(HttpHeaders.AUTHORIZATION);

			if (header == null || !header.startsWith("Bearer ")) {
				if (!isPublicEndpoint(request)) {
					throw new JwtAuthenticationException("Missing Authorization header. JWT token is required.");
				}
				// Allow request for public endpoints
				filterChain.doFilter(request, response);
				return;
			}

			String token = header.split(" ")[1].trim();

			if (!jwtUtils.validateAccessToken(token) || header == null) {
				throw new JwtAuthenticationException("Invalid or expired JWT token");
			}

			String username = jwtUtils.getSubject(token);
			List<SimpleGrantedAuthority> authorities = jwtUtils.getPermissions(token).stream()
					.map(SimpleGrantedAuthority::new)
					.toList();

			UserDetails userDetails = userDetailsService.loadUserByUsername(username);
			UsernamePasswordAuthenticationToken authentication =
					new UsernamePasswordAuthenticationToken(userDetails, null, authorities);

			authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(authentication);

			filterChain.doFilter(request, response);
		} catch (JwtAuthenticationException ex) {
			sendJsonErrorResponse(response, HttpStatus.UNAUTHORIZED, ex.getMessage(), request.getRequestURI());
		}
	}

	private boolean isPublicEndpoint(HttpServletRequest request) {
		String path = request.getRequestURI();

		return path.equals("/api/v1/users/login") || path.equals("/api/v1/users/register");
	}

	private void sendJsonErrorResponse(HttpServletResponse response, HttpStatus status, String message, String path)
			throws IOException {
		ErrorResponse errorResponse = new ErrorResponse(
				"JWT Authentication Error",
				message,
				null,
				status.value(),
				path
		);

		response.setStatus(status.value());
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");

		ObjectMapper mapper = new ObjectMapper();
		response.getWriter().write(mapper.writeValueAsString(errorResponse));
		response.getWriter().flush();
	}

}
