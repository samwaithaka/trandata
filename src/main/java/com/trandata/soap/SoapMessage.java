package com.trandata.soap;

public class SoapMessage {
	public static String getTranDataRequestMessage(String customerNumber) {
		String soap = "<soapenv:Envelope \n"
				+ "    xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\n"
				+ "    xmlns:cus=\"http://credable.io/cbs/customer/\">\n"
				+ "    <soapenv:Header/>\n"
				+ "    <soapenv:Body>\n"
				+ "      <TransactionsRequest>\n"
				+ "        <customerNumber>" + customerNumber + "</customerNumber>\n"
				+ "      </TransactionsRequest>\n"
				+ "    </soapenv:Body>\n"
				+ "  </soapenv:Envelope>";
		return soap;
	}
}