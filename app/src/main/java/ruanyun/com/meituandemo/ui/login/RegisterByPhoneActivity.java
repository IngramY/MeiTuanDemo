package ruanyun.com.meituandemo.ui.login;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.androidquery.AQuery;

import ruanyun.com.meituandemo.R;

public class RegisterByPhoneActivity extends Activity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_by_phone);

        AQuery aq = new AQuery(this);
        aq.id(R.id.register_by_phone_back).clicked(this);
        aq.id(R.id.edittext_login_username).clicked(this);
        aq.id(R.id.edittext_login_password).clicked(this);
        aq.id(R.id.button_login).clicked(this);



    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.register_by_phone_back:
                finish();
                break;
            case R.id.edittext_login_username:

                break;
            case R.id.edittext_login_password:

                break;
            case R.id.button_login:

                break;


        }
    }
}
