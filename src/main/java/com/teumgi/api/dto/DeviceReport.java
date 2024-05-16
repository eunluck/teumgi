package com.teumgi.api.dto;

import lombok.Data;

import java.util.List;

@Data
public class DeviceReport {



    private String deviceId;

    private String timestamp;

    private String eventType;

    private String opMode;

    private List<SensorData> sensorData;

    @Data
    public static class SensorData {

        private String loadCell;
        private String temperature;
        private String humidity;
        private Long timestamp;

    }
}