package com.imran.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.imran.InventoryResponse;
import com.imran.controller.RestService;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@RunWith(MockitoJUnitRunner.class)
public class RestServiceTest {
	
	@InjectMocks
	RestService restService;
	 
	
	private MockMvc mockMvc;
	
	@Before
    public void setup() { 

        
        MockitoAnnotations.initMocks(this);
      //  mockMvc = MockMvcBuilders.standaloneSetup().build();
        mockMvc = MockMvcBuilders.standaloneSetup(restService).build();
    }
	
	@Test
	public void restServiceTest() throws Exception
	
	{
	InventoryResponse inventoryResponseinput=new InventoryResponse();
	
	inventoryResponseinput.setProductId("test_product id");
	
	inventoryResponseinput.setComment("test comment");
	
	inventoryResponseinput.setReturnCode(1);
	
	ObjectMapper mapper = new ObjectMapper();
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    byte ary[]=mapper.writeValueAsBytes(inventoryResponseinput);
	
	
	 mockMvc.perform(
             post("/receive/receive")
                     .contentType(MediaType.APPLICATION_JSON)
                    .content(ary)
     )
             .andExpect(MockMvcResultMatchers.status().isOk())
             .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
             ;
	
	
            
	
	}

}
	
	
