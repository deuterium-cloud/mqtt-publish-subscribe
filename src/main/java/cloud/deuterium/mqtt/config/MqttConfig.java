package cloud.deuterium.mqtt.config;

import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;

/**
 * Created by Milan Stojkovic 06-Mar-2022
 */

@Configuration
public class MqttConfig {

    @Bean
    MqttPahoClientFactory clientFactory(@Value("${mqtt.host.uri}") String host,
                                        @Value("${mqtt.host.username}") String username,
                                        @Value("${mqtt.host.password}") String password) {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        MqttConnectOptions options = new MqttConnectOptions();
        options.setServerURIs(new String[]{host});
        options.setUserName(username);
        options.setPassword(password.toCharArray());
        factory.setConnectionOptions(options);
        return factory;
    }

}
