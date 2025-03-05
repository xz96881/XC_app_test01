// 导包必须放在最前面！
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import com.zxz.xc_test01.R;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnForward = findViewById(R.id.btnForward); // 确保XML中有这个ID

        btnForward.setOnClickListener(v -> {
            Toast.makeText(this, "前进指令已发送", Toast.LENGTH_SHORT).show();
        });
    }
}