package com.example.shop.util;

import java.security.SecureRandom;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class UserUtil {
	private final Random RANDOM = new SecureRandom();
	private final String ALBPHABET = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
	private final int ITERATIONS = 10000;
	private final int KEY_LENGTH = 256;
	
	public String generatedUserId(int length) {
		return generatedRandomString(length);
	}
	
	private String generatedRandomString(int length) {
		StringBuilder returnValue = new StringBuilder(length);
		for(int i= 0; i<length;i++) {
			returnValue.append(ALBPHABET.charAt(RANDOM.nextInt(ALBPHABET.length())));
		}
		return new String(returnValue);
	}
}
