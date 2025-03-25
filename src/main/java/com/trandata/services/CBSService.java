package com.trandata.services;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.trandata.dto.TranDataDto;
import com.trandata.enums.Format;
import com.trandata.http.HttpClient;
import com.trandata.soap.SoapMessage;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Service
public class CBSService {

	@Value("${app.constants.ws.tran-data-url}")
	private String tranDataEndpoint;
	private HttpClient httpClient;
	
	public TranDataDto getTransactionData(String customerNumber) {
		String message = SoapMessage.getTranDataRequestMessage(customerNumber);
		TranDataDto tranDataDto = new TranDataDto();
		try {
			XmlMapper xmlMapper = new XmlMapper();
			ObjectMapper jsonMapper = new ObjectMapper();
			jsonMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			//String xmlRes = httpClient.sendPostRequest(tranDataEndpoint, message, Format.SOAP, null);
			String xmlRes = "<?xml version=\"1.0\" encoding=\"UTF-8\"?> <soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"                   xmlns:tns=\"http://credable.io/cbs/transaction\">    <soapenv:Header/>    <soapenv:Body>       <tns:TransactionsResponse>          <transactions>             <accountNumber>9876543210</accountNumber>             <alternativechanneltrnscrAmount>200.50</alternativechanneltrnscrAmount>             <alternativechanneltrnscrNumber>5</alternativechanneltrnscrNumber>             <alternativechanneltrnsdebitAmount>150.75</alternativechanneltrnsdebitAmount>             <alternativechanneltrnsdebitNumber>3</alternativechanneltrnsdebitNumber>             <atmTransactionsNumber>2</atmTransactionsNumber>             <atmtransactionsAmount>500.00</atmtransactionsAmount>             <bouncedChequesDebitNumber>1</bouncedChequesDebitNumber>             <bouncedchequescreditNumber>0</bouncedchequescreditNumber>             <bouncedchequetransactionscrAmount>0.00</bouncedchequetransactionscrAmount>             <bouncedchequetransactionsdrAmount>300.00</bouncedchequetransactionsdrAmount>             <chequeDebitTransactionsAmount>1000.00</chequeDebitTransactionsAmount>             <chequeDebitTransactionsNumber>4</chequeDebitTransactionsNumber>             <createdAt>2025-03-24T10:15:30Z</createdAt>             <createdDate>2025-03-24T00:00:00Z</createdDate>             <credittransactionsAmount>2500.00</credittransactionsAmount>             <debitcardpostransactionsAmount>1200.00</debitcardpostransactionsAmount>             <debitcardpostransactionsNumber>8</debitcardpostransactionsNumber>             <fincominglocaltransactioncrAmount>900.00</fincominglocaltransactioncrAmount>             <id>123456</id>             <incominginternationaltrncrAmount>1500.00</incominginternationaltrncrAmount>             <incominginternationaltrncrNumber>2</incominginternationaltrncrNumber>             <incominglocaltransactioncrNumber>5</incominglocaltransactioncrNumber>             <intrestAmount>50</intrestAmount>             <lastTransactionDate>2025-03-23T18:45:00Z</lastTransactionDate>             <lastTransactionType>Credit</lastTransactionType>             <lastTransactionValue>200</lastTransactionValue>             <maxAtmTransactions>5</maxAtmTransactions>             <maxMonthlyBebitTransactions>3000.00</maxMonthlyBebitTransactions>             <maxalternativechanneltrnscr>500.00</maxalternativechanneltrnscr>             <maxalternativechanneltrnsdebit>400.00</maxalternativechanneltrnsdebit>             <maxbouncedchequetransactionscr>0.00</maxbouncedchequetransactionscr>             <maxchequedebittransactions>2000.00</maxchequedebittransactions>             <maxdebitcardpostransactions>1500.00</maxdebitcardpostransactions>             <maxincominginternationaltrncr>3000.00</maxincominginternationaltrncr>             <maxincominglocaltransactioncr>2000.00</maxincominglocaltransactioncr>             <maxmobilemoneycredittrn>500.00</maxmobilemoneycredittrn>             <maxmobilemoneydebittransaction>450.00</maxmobilemoneydebittransaction>             <maxmonthlycredittransactions>4000.00</maxmonthlycredittransactions>             <maxoutgoinginttrndebit>3500.00</maxoutgoinginttrndebit>             <maxoutgoinglocaltrndebit>2500.00</maxoutgoinglocaltrndebit>             <maxoverthecounterwithdrawals>500.00</maxoverthecounterwithdrawals>             <mobilemoneycredittransactionAmount>250.00</mobilemoneycredittransactionAmount>             <mobilemoneycredittransactionNumber>2</mobilemoneycredittransactionNumber>             <mobilemoneydebittransactionAmount>200.00</mobilemoneydebittransactionAmount>             <mobilemoneydebittransactionNumber>1</mobilemoneydebittransactionNumber>             <monthlyBalance>5000.00</monthlyBalance>             <monthlydebittransactionsAmount>3000.00</monthlydebittransactionsAmount>             <outgoinginttransactiondebitAmount>2000.00</outgoinginttransactiondebitAmount>             <outgoinginttrndebitNumber>1</outgoinginttrndebitNumber>             <outgoinglocaltransactiondebitAmount>1000.00</outgoinglocaltransactiondebitAmount>             <outgoinglocaltransactiondebitNumber>2</outgoinglocaltransactiondebitNumber>             <overdraftLimit>1000.00</overdraftLimit>             <overthecounterwithdrawalsAmount>500.00</overthecounterwithdrawalsAmount>             <overthecounterwithdrawalsNumber>3</overthecounterwithdrawalsNumber>             <transactionValue>10000.00</transactionValue>             <updatedAt>2025-03-24T12:00:00Z</updatedAt>          </transactions>       </tns:TransactionsResponse>    </soapenv:Body> </soapenv:Envelope>";
			JsonNode jsonFromSoap = xmlMapper.readTree(xmlRes.getBytes());
			JsonNode responseObject = jsonFromSoap.get("Body").get("TransactionsResponse").get("transactions");
			tranDataDto = jsonMapper.readValue(responseObject.toString(), new TypeReference<TranDataDto>() {});
		} catch (Exception e) {
			e.printStackTrace();
		}
		return tranDataDto;
	}
}