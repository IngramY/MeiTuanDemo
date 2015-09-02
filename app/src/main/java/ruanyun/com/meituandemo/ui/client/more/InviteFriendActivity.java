package ruanyun.com.meituandemo.ui.client.more;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.androidquery.AQuery;

import ruanyun.com.meituandemo.R;

public class InviteFriendActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invite_friend);
        AQuery aq = new AQuery(this);
        aq.id(R.id.imageview_back_invitefriend).clicked(this);

    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.imageview_back_invitefriend:
                finish();
                break;

        }
    }
}
