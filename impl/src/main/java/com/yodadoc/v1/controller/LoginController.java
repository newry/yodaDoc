package com.yodadoc.v1.controller;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
	private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public ResponseEntity<Map<String, String>> login(@RequestParam String user, @RequestParam String password) {
		LOG.info("user={}", user);
		if ("admin".equals(user) && "admin".equals(password)) {
			Map<String, String> map = new HashMap<String, String>();
			map.put("token_type", "Basic");
			map.put("access_token", Base64Utils.encodeToString((user + ":" + password).getBytes()));
			return new ResponseEntity<Map<String, String>>(map, HttpStatus.OK);
		}
		Map<String, String> map = new HashMap<String, String>();
		map.put("error", "true");
		return new ResponseEntity<Map<String, String>>(map, HttpStatus.UNAUTHORIZED);
	}
}
