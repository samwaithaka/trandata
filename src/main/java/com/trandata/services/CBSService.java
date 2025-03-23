package com.trandata.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.trandata.dto.TranDataDto;
import com.trandata.enums.Format;
import com.trandata.http.HttpClient;
import com.trandata.soap.SoapMessage;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class CBSService {

	@Value("${app.constants.ws.url.tran-data}")
	private String tranDataEndpoint;
	
	private HttpClient httpClient;
	
	public List<TranDataDto> getTransactionData(String customerNumber) {
		String message = SoapMessage.getTranDataRequestMessage(customerNumber);
		try {
			XmlMapper xmlMapper = new XmlMapper();
			String response = httpClient.sendPostRequest(tranDataEndpoint, message, Format.SOAP, null);
			List<TranDataDto> tranDataDto = xmlMapper.readValue(response, new TypeReference<List<TranDataDto>>() {});
			return tranDataDto;
		} catch (Exception e) {
			e.printStackTrace();
			return new ArrayList<>();
		}
	}
}
