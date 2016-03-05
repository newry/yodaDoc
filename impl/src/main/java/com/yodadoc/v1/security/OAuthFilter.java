package com.yodadoc.v1.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.Http401AuthenticationEntryPoint;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.NullRememberMeServices;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class OAuthFilter extends OncePerRequestFilter {

	private RememberMeServices rememberMeServices = new NullRememberMeServices();
	private AuthenticationEntryPoint authenticationEntryPoint = new Http401AuthenticationEntryPoint("yodaDoc");
	@Autowired
	private AuthenticationManager authenticationManager;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws ServletException, IOException {
		String header = request.getHeader("Authorization");

		if (header == null || !header.startsWith("Bearer ")) {
			// authenticationEntryPoint.commence(request, response,
			// new BadCredentialsException("no bearer token provided"));
			chain.doFilter(request, response);
			return;
		}

		try {
			String token = getToken(header);
			if (authenticationIsRequired(token)) {
				AuthenticationToken authRequest = new AuthenticationToken(token);
				Authentication authResult = authenticationManager.authenticate(authRequest);

				SecurityContextHolder.getContext().setAuthentication(authResult);

				rememberMeServices.loginSuccess(request, response, authResult);
			}

		} catch (AuthenticationException failed) {
			SecurityContextHolder.clearContext();
			rememberMeServices.loginFail(request, response);
			authenticationEntryPoint.commence(request, response, failed);
			return;
		}

		chain.doFilter(request, response);
	}

	private boolean authenticationIsRequired(String token) {
		// Only reauthenticate if username doesn't match SecurityContextHolder
		// and user
		// isn't authenticated
		// (see SEC-53)
		Authentication existingAuth = SecurityContextHolder.getContext().getAuthentication();

		if (existingAuth == null || !existingAuth.isAuthenticated()) {
			return true;
		}

		// Limit username comparison to providers which use usernames (ie
		// UsernamePasswordAuthenticationToken)
		// (see SEC-348)

		if (existingAuth instanceof AuthenticationToken && !existingAuth.getCredentials().equals(token)) {
			return true;
		}

		// Handle unusual condition where an AnonymousAuthenticationToken is
		// already
		// present
		// This shouldn't happen very often, as BasicProcessingFitler is meant
		// to be
		// earlier in the filter
		// chain than AnonymousAuthenticationFilter. Nevertheless, presence of
		// both an
		// AnonymousAuthenticationToken
		// together with a BASIC authentication request header should indicate
		// reauthentication using the
		// BASIC protocol is desirable. This behaviour is also consistent with
		// that
		// provided by form and digest,
		// both of which force re-authentication if the respective header is
		// detected (and
		// in doing so replace
		// any existing AnonymousAuthenticationToken). See SEC-610.
		if (existingAuth instanceof AnonymousAuthenticationToken) {
			return true;
		}

		return false;
	}

	private String getToken(String header) throws IOException {
		return header.substring(7);
	}
}
