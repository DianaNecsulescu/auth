package com.test.authentication.utils;

import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Base64.Encoder;

public class EncoderUtils {

	static Encoder encoder = Base64.getEncoder();
	static Decoder decoder = Base64.getDecoder();

	/*
	 * Method to encode a text in base64
	 */
	public static String encode(String text) {
		return encoder.encodeToString(text.getBytes());
	}

	/*
	 * Method to decode a text in base64
	 */
	public static String decode(String text) {
		return new String(decoder.decode(text.getBytes()));
	}
}
