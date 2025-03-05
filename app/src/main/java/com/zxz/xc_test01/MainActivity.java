// com.zxz.xc_test01.MainActivity
package com.zxz.xc_test01;
import androidx.core.graphics.Insets;  // 关键导包
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.zxz.xc_test01.R;
import com.zxz.xc_test01.utils.VideoProcessor;
import android.view.SurfaceView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 整合视频处理逻辑
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // 视频初始化
        SurfaceView surfaceView = findViewById(R.id.video_surface);
        VideoProcessor processor = new VideoProcessor(surfaceView);

        // 窗口适配逻辑
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 按钮事件
        Button btnForward = findViewById(R.id.btnForward);
        btnForward.setOnClickListener(v -> {
            Toast.makeText(this, "前进指令已发送", Toast.LENGTH_SHORT).show();
        });
    }
}