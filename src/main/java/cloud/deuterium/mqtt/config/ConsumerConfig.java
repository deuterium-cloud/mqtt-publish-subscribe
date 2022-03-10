package cloud.deuterium.mqtt.config;

import cloud.deuterium.mqtt.handler.MessageHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.handler.GenericHandler;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;

/**
 * Created by Milan Stojkovic 06-Mar-2022
 */

@Configuration
public class ConsumerConfig {

    @Bean
    MqttPahoMessageDrivenChannelAdapter inboundAdapter(@Value("${mqtt.topic.consumer}") String topic,
                                                       MqttPahoClientFactory factory) {

        return new MqttPahoMessageDrivenChannelAdapter("consumer", factory, topic);
    }

    @Bean
    IntegrationFlow inboundFlow(MqttPahoMessageDrivenChannelAdapter inboundAdapter,
                                MessageHandler messageHandler) {
        return IntegrationFlows
                .from(inboundAdapter)
                .handle((GenericHandler<String>) (payload, headers) -> {
                    messageHandler.consumeTemperature(payload);
                    headers.forEach((k, v) -> System.out.println(k + "-" + v));
                    return null;
                })
                .get();
    }
}
