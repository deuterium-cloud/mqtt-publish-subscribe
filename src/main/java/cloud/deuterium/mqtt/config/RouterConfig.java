package cloud.deuterium.mqtt.config;

import cloud.deuterium.mqtt.handler.MessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

/**
 * Created by Milan Stojkovic 06-Mar-2022
 */

@Configuration
public class RouterConfig {

    @Bean
    RouterFunction<ServerResponse> http(MessageHandler handler) {
        return RouterFunctions.route()
                .GET("/api/v1/temperature", req -> {
                    String value = req.queryParam("value").orElse("0");
                    handler.publishTemperature(value);
                    return ServerResponse.ok().build();
                })
                .build();
    }
}
