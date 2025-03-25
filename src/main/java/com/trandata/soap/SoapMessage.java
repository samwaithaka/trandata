package com.trandata.soap;

public class SoapMessage {
	public static String getTranDataRequestMessage(String customerNumber) {
		String soap = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n"
				+ "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\"\n"
				+ "                  xmlns:tns=\"http://credable.io/cbs/transaction\">\n"
				+ "   <soapenv:Header/>\n"
				+ "   <soapenv:Body>\n"
				+ "      <tns:TransactionsRequest>\n"
				+ "         <customerNumber>" + customerNumber + "</customerNumber>\n"
				+ "      </tns:TransactionsRequest>\n"
				+ "   </soapenv:Body>\n"
				+ "</soapenv:Envelope>";
		return soap;
	}
}