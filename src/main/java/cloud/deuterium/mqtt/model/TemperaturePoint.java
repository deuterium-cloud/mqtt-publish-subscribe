package cloud.deuterium.mqtt.model;

import java.math.BigDecimal;

/**
 * Created by Milan Stojkovic 06-Mar-2022
 */
public record TemperaturePoint(BigDecimal value, long time, String sensor) {

    public String toJson() {
        return """
                {
                    "value" : %s,
                    "time" : %d,
                    "sensor" : "%s"
                }
                """.formatted(value.toString(), time, sensor);
    }
}
