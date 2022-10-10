package com.springboot.ecommerce.products.response;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.springboot.ecommerce.products.enums.Response;

public class ResponseHandler {
	public static ResponseEntity<Object> generateResponse(Response response, String message, Object responseObj, HttpStatus status) {
        Map<String, Object> map = new LinkedHashMap<String, Object>();
        	map.put("status", response);
            map.put("message", message);
            map.put("data", responseObj);

            return new ResponseEntity<Object>(map,status);
    }
}
