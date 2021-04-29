package com.stoom.address;

import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.text.ParseException;

import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.stoom.address.controller.dto.AddressDTO;
import com.stoom.address.model.Address;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AddressApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	private static AddressDTO address = new AddressDTO();

	@BeforeClass
	public static void setUp() throws ParseException, IOException {
		address.setStreetName("Amphitheatre Parkway");
		address.setNumber("1600");
		address.setNeighbourhood("Mountain View");
		address.setState("CA");
		address.setCountry("EUA");
		address.setZipcode("94043");
	}

	@Test
	public void test1PostCreateAddress() {

		try {
			MvcResult result = this.mockMvc.perform(MockMvcRequestBuilders
					.post("/v1/").contentType(MediaType.APPLICATION_JSON)
					.accept(MediaType.APPLICATION_JSON)
					.content(asJsonString(address)))
					.andDo(print())
					.andExpect(status().isCreated())
					.andReturn();

			address.setId(new ObjectMapper().readValue(result.getResponse().getContentAsString(), Address.class).getId());
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test2PostCreateAddressAlreadyRegistered() {
		try {
			
			this.mockMvc.perform(post("/v1/")
					.header("Accept-Language", "pt")
					.contentType(MediaType.APPLICATION_JSON)
					.content(asJsonString(address)))	
					.andDo(print())
					.andExpect(status().isConflict())					
					.andReturn();			

		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Test
	public void test3GetAddressAsJson() {
		try {			
			
			this.mockMvc.perform(MockMvcRequestBuilders
					.get("/v1/{id}", address.getId().intValue())
					.accept(MediaType.APPLICATION_JSON))
					.andDo(print())
					.andExpect(status().isOk())
					.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(address.getId().intValue()));
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test4PatchUpdateAddress() {
		try {		
			this.mockMvc.perform(patch("/v1/{id}", address.getId())
					.contentType(MediaType.APPLICATION_JSON)
					.content(asJsonString(address)))	
					.andDo(print())
					.andExpect(status().isOk())
					.andReturn();
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void test5DeleteAddress() {
		try {		
			this.mockMvc.perform(delete("/v1/{id}", address.getId())
					.contentType(MediaType.APPLICATION_JSON))
					.andExpect(status().isOk());
			
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
