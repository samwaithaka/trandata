package com.trandata.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.trandata.dto.TranDataDto;
import com.trandata.services.CBSService;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@RequestMapping("trandata/api")
public class TrandataController {
	
	private CBSService cbsService;	
	
	@GetMapping("/trandata/{customerNumber}")
	public List<TranDataDto> getTrandata(@PathVariable String customberNumber) {
		return cbsService.getTransactionData(customberNumber);
	}
}
