package com.imran.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.imran.InventoryResponse;

@RestController
@RequestMapping("/receive")
public class RestService {
	
	 static final Logger LOG = LoggerFactory.getLogger(RestService.class);
	
	@RequestMapping(value="/receive", method=RequestMethod.POST,produces=MediaType.APPLICATION_JSON_VALUE)
	
	public InventoryResponse restService(@RequestBody InventoryResponse inventoryResponse)
	{
		
		
		LOG.info(inventoryResponse.toString());
		
		return inventoryResponse;
		
		
	}

}
