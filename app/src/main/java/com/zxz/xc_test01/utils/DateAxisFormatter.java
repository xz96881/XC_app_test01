// 在 utils 包下新建 DateAxisFormatter.java
package com.zxz.xc_test01.utils;

import com.github.mikephil.charting.formatter.ValueFormatter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAxisFormatter extends ValueFormatter {
    private final SimpleDateFormat mFormat = new SimpleDateFormat("HH:mm");

    @Override
    public String getFormattedValue(float value) {
        return mFormat.format(new Date((long) value));
    }
}