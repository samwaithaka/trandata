package com.trandata.http;

import okhttp3.*;
import javax.net.ssl.*;

import com.trandata.enums.Format;

import java.io.IOException;
import java.security.cert.X509Certificate;

/**
 * @author Samuel
 * HTTP client class responsible for making http and https get and post requests. It uses the okhttp3 library
 * This implementation also ignores SSL certificate validation.
 */
public class HttpClient {
	private static final OkHttpClient client = getUnsafeOkHttpClient();

	/**
	 * Sends a GET request to the specified URL.
	 *
	 * @param url the URL to send the request to
	 * @return the response body as a string
	 * @throws IOException if an I/O error occurs
	 */
	public String sendGetRequest(String url) throws IOException {
		Request request = new Request.Builder()
				.url(url)
				.build();

		try (Response response = client.newCall(request).execute()) {
			return response.body() != null ? response.body().string() : null;
		}
	}

	/**
	 * Sends a POST request to the specified URL with a JSON payload.
	 *
	 * @param url the URL to send the request to
	 * @param payload the data to send in the request body
	 * @return the response body as a string
	 * @throws IOException if an I/O error occurs
	 */
	public static String sendPostRequest(String url, String payload, Format format, String authorization) throws IOException {
		System.out.println(format);
		MediaType mediaType = MediaType.get("application/json; charset=utf-8");
		switch(format) {
		case TEXT :
			mediaType = MediaType.get("text/plain; charset=utf-8");
		case SOAP :
			mediaType = MediaType.get("application/soap+xml; charset=utf-8");
		default:
			mediaType = MediaType.get("application/soap+xml; charset=utf-8");
		}
        
		System.out.println(mediaType);
		RequestBody body = RequestBody.create(payload, mediaType);
		Request request = new Request.Builder()
				.url(url)
				//.addHeader("Authorization", authorization)
				.post(body)
				.build();

		try (Response response = client.newCall(request).execute()) {
			return response.body() != null ? response.body().string() : null;
		}
	}

	/**
	 * Creates an OkHttpClient instance that trusts all SSL certificates.
	 *
	 * @return an OkHttpClient instance with disabled SSL verification
	 */
	private static OkHttpClient getUnsafeOkHttpClient() {
		try {
			// Create a trust manager that does not validate certificate chains
			final TrustManager[] trustAllCerts = new TrustManager[] {
					new X509TrustManager() {
						@Override
						public void checkClientTrusted(X509Certificate[] chain, String authType) {}

						@Override
						public void checkServerTrusted(X509Certificate[] chain, String authType) {}

						@Override
						public X509Certificate[] getAcceptedIssuers() {
							return new X509Certificate[]{};
						}
					}
			};

			// Install the all-trusting trust manager
			final SSLContext sslContext = SSLContext.getInstance("SSL");
			sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

			// Create an ssl socket factory with our all-trusting manager
			final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

			return new OkHttpClient.Builder()
					.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0])
					.hostnameVerifier((hostname, session) -> true)
					.build();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
