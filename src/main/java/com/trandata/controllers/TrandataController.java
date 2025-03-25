package com.trandata.controllers;

import java.util.ArrayList;
import java.util.List;

//import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.trandata.dto.TranDataDto;
import com.trandata.services.CBSService;

import lombok.AllArgsConstructor;

/**
 * TrandataController is a REST controller that provides an API 
 * endpoint to retrieve transaction data for a given customer number. 
 * It interacts with the CBSService to fetch transaction details.
 * @author Samuel
 *
 */
@AllArgsConstructor
@RestController
@RequestMapping("trans/api")
public class TrandataController {
	
	private CBSService cbsService;	
	
	@GetMapping("/data/{customerNumber}")
	public List<TranDataDto> getTrandata(@PathVariable String customerNumber) {
		TranDataDto tranDataDto = cbsService.getTransactionData(customerNumber);
		List<TranDataDto> trans = new ArrayList<>();
		trans.add(tranDataDto);
		return trans;
	}
}
