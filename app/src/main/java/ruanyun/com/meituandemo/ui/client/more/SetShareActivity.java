package ruanyun.com.meituandemo.ui.client.more;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.androidquery.AQuery;

import butterknife.ButterKnife;
import ruanyun.com.meituandemo.R;
import ruanyun.com.meituandemo.ui.client.WebViewActivity;

public class SetShareActivity extends Activity implements View.OnClickListener{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_share);
        ButterKnife.inject(this);
        AQuery aq = new AQuery(this);
        aq.id(R.id.imageview_back_share).clicked(this);
        aq.id(R.id.ll_share_sina_weibo).clicked(this);
        aq.id(R.id.ll_share_renren).clicked(this);
        aq.id(R.id.ll_share_tencent_weibo).clicked(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageview_back_share:
                finish();
                break;

            case R.id.ll_share_sina_weibo:

            case R.id.ll_share_renren:

            case R.id.ll_share_tencent_weibo:
                startActivity(new Intent(SetShareActivity.this, WebViewActivity.class));
                break;

        }
    }
}
