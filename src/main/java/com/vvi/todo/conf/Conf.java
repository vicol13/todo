package com.vvi.todo.conf;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vvi.todo.exception.UserClientExceptionHandler;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Conf {

	@Value("${client.user.uri}")
	private String userRootUri;

	@Bean("user-client")
	public RestTemplate userServiceConfiguration(
		final RestTemplateBuilder builder,
		final UserClientExceptionHandler handler
	) {
		return builder
			.rootUri(userRootUri)
			.errorHandler(handler)
			.build();
	}

	@Bean("user-mapper")
	public ObjectMapper mapper() {
		return new ObjectMapper()
			.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}

	@Bean("projection-factory")
	public ProjectionFactory pf() {
		return new SpelAwareProxyProjectionFactory();
	}

	@Bean
	public OpenAPI springShopOpenAPI() {
		return new OpenAPI()
			.info(new Info().title("Sample board managing app API")
				.description("Sample which with board and tasks can be created")
				.version("v0.0.1"));
	}
}
