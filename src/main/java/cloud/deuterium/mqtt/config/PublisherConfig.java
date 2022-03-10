package cloud.deuterium.mqtt.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.MessageChannels;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.messaging.MessageChannel;

/**
 * Created by Milan Stojkovic 06-Mar-2022
 */

@Configuration
public class PublisherConfig {

    @Bean
    MessageChannel out() {
        return MessageChannels.direct().get();
    }

    @Bean
    MqttPahoMessageHandler outboundAdapter(@Value("${mqtt.topic.publisher}") String topic,
                                           MqttPahoClientFactory factory) {
        MqttPahoMessageHandler outboundAdapter = new MqttPahoMessageHandler("producer", factory);
        outboundAdapter.setDefaultTopic(topic);
        return outboundAdapter;
    }

    @Bean
    IntegrationFlow outboundFlow(MessageChannel out, MqttPahoMessageHandler outboundAdapter) {
        return IntegrationFlows
                .from(out)
                .handle(outboundAdapter)
                .get();
    }
}
