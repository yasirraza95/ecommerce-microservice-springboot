package com.springboot.ecommerce.user.controller;

import org.springframework.test.web.servlet.RequestBuilder;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.springboot.ecommerce.user.service.UserService;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = UserController.class)
public class UserControllerTests {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	@Test
	public void whenLogin() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/login").accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String actual = result.getResponse().getContentAsString();
		
		System.out.println(actual);

		String expected = "{\r\n" + "    \"status\": \"TRUE\",\r\n"
				+ "    \"message\": \"eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJ0ZXN0IiwiaWF0IjoxNjY0NzI2MTI2LCJleHAiOjE2NjQ3MjYzMDZ9.qq-caPb-O35ck3HAAL9rv9qhy3dr8PAHD8eNVJpo-x7cVC3QIsGgvuXU9xV7Rv34aVv_wWvg_vKSTqzO2g0-VQ\"\r\n"
				+ "}";

		JSONAssert.assertEquals(expected, actual, false);
	}

}
