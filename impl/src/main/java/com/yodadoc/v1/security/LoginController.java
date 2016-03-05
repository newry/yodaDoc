package com.yodadoc.v1.security;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private DefaultUserStore userStore;

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> login(@RequestParam String user, @RequestParam String password) {
		LOG.info("user={}", user);
		try {
			String token = userStore.auth(user, password);
			Map<String, String> map = new HashMap<String, String>();
			map.put("token_type", "Bearer");
			map.put("access_token", token);
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);

		} catch (AuthenticationException e) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("error", "true");
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.UNAUTHORIZED);
		}
		
	}
}
