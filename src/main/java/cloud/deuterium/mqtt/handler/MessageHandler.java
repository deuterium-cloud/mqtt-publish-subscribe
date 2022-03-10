package cloud.deuterium.mqtt.handler;

import cloud.deuterium.mqtt.model.TemperaturePoint;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by Milan Stojkovic 06-Mar-2022
 */

@Service
public class MessageHandler {

    private final MessageChannel out;
    private final ObjectMapper objectMapper;

    public MessageHandler(MessageChannel out) {
        this.out = out;
        this.objectMapper = new ObjectMapper();
    }

    public void publishTemperature(String value){
        BigDecimal temperature = new BigDecimal(value);
        TemperaturePoint point = new TemperaturePoint(temperature, System.currentTimeMillis(), "dht22");
        Message<String> message = MessageBuilder
                .withPayload(point.toJson())
                .build();
        out.send(message);
    }

    public void consumeTemperature(String payload) {
        TemperaturePoint point = null;
        try {
            point = objectMapper.readValue(payload, TemperaturePoint.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println(point);
    }
}
