package com.vvi.todo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResponseErrorHandler;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@Slf4j
public class UserClientExceptionHandler implements ResponseErrorHandler {

	@Override
	public boolean hasError(final ClientHttpResponse httpResponse) throws IOException {
		return httpResponse.getStatusCode().is4xxClientError() || httpResponse.getStatusCode().is5xxServerError();
	}

	@Override
	public void handleError(final ClientHttpResponse httpResponse) throws IOException {
		String response = new String(httpResponse.getBody().readAllBytes(), StandardCharsets.UTF_8);
		log.error("Error while requesting user-service with status-code [{}] message {}", httpResponse.getStatusCode(), response);
	}
}
