package com.hhl.foodtruck.service.impl;

import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.BasicHttpClientConnectionManager;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import com.hhl.foodtruck.service.RestService;

@Service
public class RestServiceImpl implements RestService {

	private static final int TIMEOUT_IN_SECS = 1000;
	private RestTemplate unsecureRestTemplate;

	public RestServiceImpl() {

	}

	public RestTemplate getUnsecureRestTemplate() {
		if (this.unsecureRestTemplate == null) {
			this.unsecureRestTemplate = createUnsecuredRestTemplateInternal();
		}
		return this.unsecureRestTemplate;
	}

	private RestTemplate createUnsecuredRestTemplateInternal() {
		// Create the HttpClient instance
		final HttpClient httpClient = getHttpClient();

		// Set that client into the Spring factory
		final HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory(
				httpClient);
		final RestTemplate restTemplate = new RestTemplate(factory);
		return restTemplate;
	}

	private HttpClient getHttpClient() {
		final Registry<ConnectionSocketFactory> socketFactoryRegistry;
		{
			socketFactoryRegistry = RegistryBuilder
					.<ConnectionSocketFactory> create()
					.register("http", new PlainConnectionSocketFactory())
					.build();
		}

		final BasicHttpClientConnectionManager cm = new BasicHttpClientConnectionManager(
				socketFactoryRegistry);

		{
			final SocketConfig socketConfig = SocketConfig.custom()
					.setSoKeepAlive(true).setSoTimeout(TIMEOUT_IN_SECS).build();

			cm.setSocketConfig(socketConfig);
		}

		final RequestConfig requestConfig;
		{
			final RequestConfig.Builder b = RequestConfig.custom()
					.setConnectionRequestTimeout(TIMEOUT_IN_SECS)
					.setConnectTimeout(TIMEOUT_IN_SECS)
					.setSocketTimeout(TIMEOUT_IN_SECS)
					.setAuthenticationEnabled(false);
			requestConfig = b.build();
		}

		final HttpClientBuilder b = HttpClientBuilder.create()
				.setConnectionManager(cm)
				.setDefaultRequestConfig(requestConfig);

		return b.build();
	}
}
