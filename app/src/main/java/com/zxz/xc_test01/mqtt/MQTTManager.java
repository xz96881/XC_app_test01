package com.zxz.xc_test01.mqtt;

import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import android.content.Context;

// MQTT连接管理器
public class MQTTManager {
    private static final String BROKER = "tcp://your.broker.ip:1883";

    // 初始化连接
    public void connect(Context context) {
        try {
            MqttClient client = new MqttClient(BROKER, "ClientID");
            MqttConnectOptions options = new MqttConnectOptions();
            options.setCleanSession(true);
            client.connect(options);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}