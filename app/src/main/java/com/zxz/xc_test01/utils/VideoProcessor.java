package com.zxz.xc_test01.utils;

import android.media.MediaCodec;
import android.media.MediaFormat;
import android.util.Log;
import android.view.Surface;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import java.io.IOException;

public class VideoProcessor implements MqttCallback {
    private final SurfaceView videoSurface;
    private MediaCodec mediaCodec;

    // 构造函数：绑定SurfaceView
    public VideoProcessor(SurfaceView surfaceView) {
        this.videoSurface = surfaceView;
        setupSurfaceCallback();
    }

    // 配置Surface回调（关键！）
    private void setupSurfaceCallback() {
        videoSurface.getHolder().addCallback(new SurfaceHolder.Callback() {
            @Override
            public void surfaceCreated(SurfaceHolder holder) {
                initDecoder(holder.getSurface());
            }

            @Override
            public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
                // 当Surface尺寸变化时处理
            }

            @Override
            public void surfaceDestroyed(SurfaceHolder holder) {
                releaseDecoder(); // Surface销毁时释放解码器
            }
        });
    }

    // 初始化解码器（核心逻辑）
    private void initDecoder(Surface surface) {
        try {
            mediaCodec = MediaCodec.createDecoderByType(MediaFormat.MIMETYPE_VIDEO_AVC);
            MediaFormat format = MediaFormat.createVideoFormat(
                    MediaFormat.MIMETYPE_VIDEO_AVC,
                    640,  // 视频实际宽度
                    480   // 视频实际高度
            );
            format.setInteger(MediaFormat.KEY_FRAME_RATE, 30); // 帧率

            mediaCodec.configure(format, surface, null, 0);
            mediaCodec.start(); // 必须启动！
            Log.d("Decoder", "解码器初始化成功");
        } catch (IOException e) {
            Log.e("Decoder", "初始化失败", e);
        }
    }

    // 处理MQTT视频流（重点修改！）
    @Override
    public void messageArrived(String topic, MqttMessage message) {
        if (!"usv/video_stream".equals(topic)) return;

        byte[] frameData = message.getPayload();
        try {
            // 1. 获取输入缓冲区
            int inputBufferId = mediaCodec.dequeueInputBuffer(10000);
            if (inputBufferId >= 0) {
                // 2. 填充数据
                mediaCodec.getInputBuffer(inputBufferId).put(frameData);
                mediaCodec.queueInputBuffer(
                        inputBufferId,
                        0,
                        frameData.length,
                        System.nanoTime()/1000,
                        0
                );
            }
            // 3. 处理输出缓冲区
            MediaCodec.BufferInfo bufferInfo = new MediaCodec.BufferInfo();
            int outputBufferId = mediaCodec.dequeueOutputBuffer(bufferInfo, 10000);
            while (outputBufferId >= 0) {
                mediaCodec.releaseOutputBuffer(outputBufferId, true);
                outputBufferId = mediaCodec.dequeueOutputBuffer(bufferInfo, 0);
            }
        } catch (Exception e) {
            Log.e("Decoder", "视频帧处理失败", e);
        }
    }

    // 释放解码器资源
    private void releaseDecoder() {
        if (mediaCodec != null) {
            mediaCodec.stop();
            mediaCodec.release();
            mediaCodec = null;
            Log.d("Decoder", "解码器已释放");
        }
    }

    // 必须实现的MQTT回调（即使空着）
    @Override
    public void deliveryComplete(IMqttDeliveryToken token) {}

    @Override
    public void connectionLost(Throwable cause) {
        Log.e("MQTT", "连接断开", cause);
    }
}