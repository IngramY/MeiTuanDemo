package ruanyun.com.meituandemo.ui.client.more;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.androidquery.AQuery;

import butterknife.ButterKnife;
import butterknife.InjectView;
import ruanyun.com.meituandemo.R;

public class MoreActivity extends Activity implements View.OnClickListener {

    private String TAG = "MoreActivity";

    @InjectView(R.id.iv_picturemode)
    ImageView ivpicturemode;
    private boolean ischeck = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more);
        ButterKnife.inject(this);
        findView();
        loadData(this);
    }


    private void findView() {
        AQuery aq = new AQuery(this);
        aq.id(R.id.btn_messageremind).clicked(this);
        aq.id(R.id.btn_shareset).clicked(this);
        aq.id(R.id.btn_invite_friends).clicked(this);
        aq.id(R.id.btn_richscan).clicked(this);
        aq.id(R.id.btn_feedback).clicked(this);
        aq.id(R.id.btn_payhelp).clicked(this);
        aq.id(R.id.btn_meituan).clicked(this);
        aq.id(R.id.btn_joinus).clicked(this);
        aq.id(R.id.btn_diagnosing_network).clicked(this);
        aq.id(R.id.llayout_picturemode).clicked(this);
        aq.id(R.id.llayout_adjust_textsize).clicked(this);
        aq.id(R.id.llayout_emptybuffer).clicked(this);
        aq.id(R.id.llayout_inspectupdate).clicked(this);
        aq.id(R.id.iv_picturemode).clicked(this);
    }

    @TargetApi(Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_messageremind://消息提醒
                startActivity(new Intent(MoreActivity.this, SettingMessageremindActivity.class));

                break;
            case R.id.btn_shareset:
                startActivity(new Intent(MoreActivity.this, SetShareActivity.class));

                break;
            case R.id.btn_invite_friends:
                startActivity(new Intent(MoreActivity.this, InviteFriendActivity.class));
                break;
            case R.id.btn_richscan:

                break;
            case R.id.btn_feedback:

                break;
            case R.id.btn_payhelp:

                break;
            case R.id.btn_meituan:

                break;
            case R.id.btn_joinus:

                break;
            case R.id.btn_diagnosing_network:
                startActivity(new Intent(MoreActivity.this, DiagnosingNetworkActivity.class));
                break;

//            case R.id.llayout_picturemode:
            case R.id.iv_picturemode:
                if (ischeck == true) {
                    ivpicturemode.setImageResource(R.mipmap.bg_cashier_use_credit_drag_on);
                    ischeck = false;
                } else {
                    ivpicturemode.setImageResource(R.mipmap.bg_cashier_use_credit_drag_off);
                    ischeck = true;
                }
                saveData(MoreActivity.this, ischeck);
                break;

            case R.id.llayout_adjust_textsize:
                startActivity(new Intent(MoreActivity.this, FontSizeActivity.class));
                break;

            case R.id.llayout_emptybuffer:

                break;

            case R.id.llayout_inspectupdate:

                break;
        }
    }

    private void loadData(Context context) {
        SharedPreferences sp = context.getSharedPreferences("config", MODE_PRIVATE);
        ischeck = sp.getBoolean("ischeck", true);
        if (ischeck == true) {
            ivpicturemode.setImageResource(R.mipmap.bg_cashier_use_credit_drag_off);
        } else {
            ivpicturemode.setImageResource(R.mipmap.bg_cashier_use_credit_drag_on);
        }
//        Toast.makeText(this, sp.getString("content", "").toString(), Toast.LENGTH_SHORT).show();
    }

    private void saveData(Context context, boolean ischeck1){
        SharedPreferences sp = context.getSharedPreferences("config", MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putBoolean("ischeck", ischeck1);
        editor.commit();
    }

}
