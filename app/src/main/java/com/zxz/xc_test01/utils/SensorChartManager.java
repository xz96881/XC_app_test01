package com.zxz.xc_test01.utils;
import com.zxz.xc_test01.SensorData; // 确保路径一致
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineDataSet; // 解决 LineDataSet 缺失
import java.util.ArrayList;  // 解决 ArrayList 缺失
public class SensorChartManager {
    private LineChart tempChart;
    private LineChart turbidityChart;

    public void initCharts() {
        // 温度图表配置
        tempChart.getDescription().setText("温度变化曲线");
        tempChart.getXAxis().setValueFormatter(new DateAxisFormatter());

        // 浑浊度图表配置
        turbidityChart.getDescription().setText("浑浊度监测");
        turbidityChart.setDrawMarkers(true);
    }

    // 替换 addEntry 方法
    public void updateData(SensorData data) {
        // 获取或创建 LineDataSet
        LineDataSet dataSet = (LineDataSet) tempChart.getData().getDataSetByIndex(0);
        if (dataSet == null) {
            dataSet = new LineDataSet(new ArrayList<>(), "温度");
            tempChart.getData().addDataSet(dataSet);
        }

        // 添加新数据点
        dataSet.addEntry(new Entry(
                System.currentTimeMillis(),
                data.getTemperature()
        ));

        // 刷新图表
        tempChart.notifyDataSetChanged();
        tempChart.moveViewToX(dataSet.getEntryCount() - 1);
    }
}