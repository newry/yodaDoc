package com.yodadoc.v1.security;

import java.util.Collection;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class AuthenticationToken extends AbstractAuthenticationToken {
	private static final long serialVersionUID = -7398902610137634200L;
	private String token;
	private Object principal;

	public AuthenticationToken(String token) {
		super(null);
		this.token = token;
		setAuthenticated(false);
	}

	public AuthenticationToken(Collection<? extends GrantedAuthority> authorities, Object principal) {
		super(authorities);
		this.principal = principal;
		setAuthenticated(true);
	}

	public Object getCredentials() {
		return token;
	}

	public Object getPrincipal() {
		return principal;
	}

}
