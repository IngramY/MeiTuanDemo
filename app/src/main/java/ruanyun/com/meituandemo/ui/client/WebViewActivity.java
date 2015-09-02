package ruanyun.com.meituandemo.ui.client;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.androidquery.AQuery;

import butterknife.ButterKnife;
import ruanyun.com.meituandemo.R;

/**
 * Created by Administrator on 2015/7/14.
 */
public class WebViewActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.webview);
        ButterKnife.inject(this);

        AQuery aq = new AQuery(this);
        aq.id(R.id.imagebutton_web_back).clicked(this);
        aq.id(R.id.imagebutton_share_web).clicked(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imagebutton_web_back:
                finish();
                break;
            case R.id.imagebutton_share_web:

                break;
        }
    }
}
