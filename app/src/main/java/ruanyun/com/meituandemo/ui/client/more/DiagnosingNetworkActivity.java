package ruanyun.com.meituandemo.ui.client.more;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import ruanyun.com.meituandemo.R;

public class DiagnosingNetworkActivity extends Activity implements View.OnClickListener{

    private ImageView imageView_back;
    private Button button_diagnosing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosing_network);
        imageView_back = (ImageView) findViewById(R.id.imageView_back_network);
        button_diagnosing = (Button) findViewById(R.id.btn_diagnosing);

        imageView_back.setOnClickListener(this);
        button_diagnosing.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageView_back_network:
                finish();
                break;
            case R.id.btn_diagnosing:

                break;
        }
    }
}
