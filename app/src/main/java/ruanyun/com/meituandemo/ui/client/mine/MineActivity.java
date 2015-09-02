package ruanyun.com.meituandemo.ui.client.mine;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import ruanyun.com.meituandemo.R;
import ruanyun.com.meituandemo.ui.client.CollectActivity;
import ruanyun.com.meituandemo.ui.login.LoginActivity;

/**
 * Created by Administrator on 2015/7/6.
 */
public class MineActivity extends Activity implements View.OnClickListener{
    private ImageView iv_mine1_new;
    private Button button_register_new;//登录
    private LinearLayout lL_mine1_new, lL_mine1_new1, dingdan, yudingdingdao,
            lL_mine3_new, huiyuanjifen, wodechoujiang, wodediyongquan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine_new);

        initview();
    }

    private void initview() {
        iv_mine1_new = (ImageView) findViewById(R.id.iv_mine1_new);
        button_register_new = (Button) findViewById(R.id.button_register_new);
        lL_mine1_new = (LinearLayout) findViewById(R.id.lL_mine1_new);
        lL_mine1_new1 = (LinearLayout) findViewById(R.id.lL_mine1_new1);
        dingdan = (LinearLayout) findViewById(R.id.dingdan);
        yudingdingdao = (LinearLayout) findViewById(R.id.yudingdingdao);
        lL_mine3_new = (LinearLayout) findViewById(R.id.lL_mine3_new );
        huiyuanjifen = (LinearLayout) findViewById(R.id.huiyuanjifen);
        wodechoujiang = (LinearLayout) findViewById(R.id.wodechoujiang);
        wodediyongquan = (LinearLayout) findViewById(R.id.wodediyongquan);

        iv_mine1_new.setOnClickListener(this);
        button_register_new.setOnClickListener(this);
        lL_mine1_new.setOnClickListener(this);
        lL_mine1_new1.setOnClickListener(this);
        dingdan.setOnClickListener(this);
        yudingdingdao.setOnClickListener(this);
        lL_mine3_new.setOnClickListener(this);
        huiyuanjifen.setOnClickListener(this);
        wodechoujiang.setOnClickListener(this);
        wodediyongquan.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.lL_mine1_new1:
                startActivity(new Intent(MineActivity.this,  CollectActivity.class));
                break;

            case R.id.iv_mine1_new://信息反馈

            case R.id.button_register_new:

            case R.id.lL_mine1_new:

            case R.id.dingdan:

            case R.id.yudingdingdao:

            case R.id.lL_mine3_new:

            case R.id.huiyuanjifen:

            case R.id.wodechoujiang:

            case R.id.wodediyongquan:
                startActivity(new Intent(MineActivity.this, LoginActivity.class));
                break;
            default:
                break;
        }
    }
}
