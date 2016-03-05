package com.yodadoc.v1.security;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

@Component
public class DefaultAuthenticationManager implements AuthenticationManager {
	@Autowired
	private DefaultUserStore userStore;

	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		AuthenticationToken at = (AuthenticationToken) authentication;
		String userName = userStore.valildateToken((String) at.getCredentials());
		return new AuthenticationToken(Arrays.asList(new SimpleGrantedAuthority("ROLE_USER")), userName);
	}

}
