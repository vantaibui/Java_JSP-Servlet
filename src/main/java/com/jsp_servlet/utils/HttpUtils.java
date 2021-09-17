package com.jsp_servlet.utils;

import java.io.BufferedReader;
import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class HttpUtils {

	private String value;

	public HttpUtils(String value) {
		this.value = value;
	}

	public static HttpUtils of(BufferedReader bufferedReader) {
		StringBuffer sb = new StringBuffer();
		String line;
		try {
			while ((line = bufferedReader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		return new HttpUtils(sb.toString());
	}
	
	public <T> T toModel(Class<T> toClass) {
		try {
			return new ObjectMapper().readValue(value, toClass);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
}
