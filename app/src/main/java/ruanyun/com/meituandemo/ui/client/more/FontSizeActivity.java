package ruanyun.com.meituandemo.ui.client.more;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.androidquery.AQuery;

import butterknife.ButterKnife;
import ruanyun.com.meituandemo.R;

public class FontSizeActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_font_size);
        ButterKnife.inject(this);
        AQuery aq = new AQuery(this);
        aq.id(R.id.imageview_back_fontsize).clicked(this);
        aq.id(R.id.ll_fontsize_small).clicked(this);
        aq.id(R.id.ll_fontsize_medium).clicked(this);
        aq.id(R.id.ll_fontsize_large).clicked(this);
        aq.id(R.id.ll_fontsize_extra_large).clicked(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageview_back_fontsize:
                finish();
                break;
            case R.id.ll_fontsize_small:
                finish();
                break;
            case R.id.ll_fontsize_medium:
                finish();
                break;
            case R.id.ll_fontsize_large:
                finish();
                break;
            case R.id.ll_fontsize_extra_large:
                finish();
                break;

        }
    }
}
