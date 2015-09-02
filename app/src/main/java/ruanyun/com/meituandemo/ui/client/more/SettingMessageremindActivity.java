package ruanyun.com.meituandemo.ui.client.more;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import ruanyun.com.meituandemo.R;

/**
 * Created by Administrator on 2015/7/21.
 */
public class SettingMessageremindActivity extends Activity implements View.OnClickListener{

    private ImageView imageView1, imageView2,imageView_back;
    private RelativeLayout relativeLayout;
    private boolean isopen = true;
    private boolean isopen1 = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting_messageremind);

        imageView1 = (ImageView) findViewById(R.id.imageview_settingmessager);
        imageView2 = (ImageView) findViewById(R.id.imageView_activityinfo);
        relativeLayout = (RelativeLayout) findViewById(R.id.rl_time);
        imageView_back = (ImageView) findViewById(R.id.imageview_back1);
        imageView_back.setOnClickListener(this);
        imageView1.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        relativeLayout.setOnClickListener(this);

    }

    @SuppressLint("NewApi")
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageview_settingmessager:
                if (isopen == true) {
                    relativeLayout.setVisibility(View.GONE);
                    imageView1.setBackground(getResources().getDrawable(R.mipmap.bg_cashier_use_credit_drag_off));
                    isopen = false;
                } else {
                    relativeLayout.setVisibility(View.VISIBLE);
                    imageView1.setBackground(getResources().getDrawable(R.mipmap.bg_cashier_use_credit_drag_on));
                    isopen = true;
                }
                break;
            case R.id.imageView_activityinfo:
                if (isopen1 == true) {
                    imageView2.setBackground(getResources().getDrawable(R.mipmap.bg_cashier_use_credit_drag_off));
                    isopen1 = false;
                } else {
                    imageView2.setBackground(getResources().getDrawable(R.mipmap.bg_cashier_use_credit_drag_on));
                    isopen1 = true;
                }
                break;
            case R.id.rl_time:
                startActivity(new Intent(SettingMessageremindActivity.this, RemindtimeActivity.class));
                break;
            case R.id.imageview_back:
                finish();
                break;

        }
    }
}
