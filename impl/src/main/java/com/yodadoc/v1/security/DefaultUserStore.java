package com.yodadoc.v1.security;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

@Component
public class DefaultUserStore implements InitializingBean {
	private Map<String, String> userMap = new HashMap<String, String>();
	private static Map<String, String> tokenMap = new HashMap<String, String>();

	public String auth(String user, String password) throws AuthenticationException {
		if (!password.equals(userMap.get(user))) {
			throw new BadCredentialsException(user);
		}
		String token = tokenMap.get(user);
		if (token == null) {
			token = DigestUtils.md5Hex(UUID.randomUUID().toString());
			tokenMap.put(user, token);
		}
		return token;
	}

	public String valildateToken(String token) throws AuthenticationException {
		for (Map.Entry<String, String> entry : tokenMap.entrySet()) {
			if (token.equals(entry.getValue())) {
				return entry.getKey();
			}
		}
		throw new BadCredentialsException(token);
	}

	public void afterPropertiesSet() throws Exception {
		userMap.put("admin", "admin");
		userMap.put("user", "user");
	}
}
