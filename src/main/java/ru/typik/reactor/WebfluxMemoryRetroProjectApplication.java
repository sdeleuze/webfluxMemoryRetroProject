package ru.typik.reactor;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@SpringBootApplication
public class WebfluxMemoryRetroProjectApplication {

	private CsvRepository csvRepository;

	public WebfluxMemoryRetroProjectApplication(CsvRepository csvRepository) {
		this.csvRepository = csvRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(WebfluxMemoryRetroProjectApplication.class, args);
	}

	@Bean
	public RouterFunction<ServerResponse> routing() {
		return RouterFunctions.nest(RequestPredicates.accept(MediaType.ALL),
				RouterFunctions.route().GET("/test", request -> csvRepository.findAll()
						.reduce(new ByteArrayOutputStream(), (output, el) -> {
							try {
								output.write(el.toString().getBytes());
								output.write("\n".getBytes());
							}
							catch (IOException e) {
								e.printStackTrace();
							}
							return output;
						})
						.map(foo -> foo.toByteArray())
						.flatMap(result ->
								ServerResponse.ok().headers(httpHeaders -> {
											httpHeaders.setContentType(new MediaType("application", "force-download"));
											httpHeaders.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=test.txt");
										})
										.bodyValue(new ByteArrayResource(result)))).build());
	}
}



