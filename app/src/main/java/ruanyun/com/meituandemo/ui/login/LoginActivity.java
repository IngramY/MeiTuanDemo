package ruanyun.com.meituandemo.ui.login;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.androidquery.AQuery;

import butterknife.ButterKnife;
import ruanyun.com.meituandemo.R;

public class LoginActivity extends Activity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.inject(this);

        AQuery aq = new AQuery(this);
        aq.id(R.id.button_back_login).clicked(this);
        aq.id(R.id.button_register).clicked(this);
        aq.id(R.id.edittext_login_username).clicked(this);
        aq.id(R.id.edittext_login_password).clicked(this);
        aq.id(R.id.button_login).clicked(this);
        aq.id(R.id.textview_login_password_back).clicked(this);
        aq.id(R.id.textview_login_byphone).clicked(this);
        aq.id(R.id.imageView_sina).clicked(this);
        aq.id(R.id.imageView_baidu).clicked(this);
        aq.id(R.id.imageView_tencent).clicked(this);


    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_back_login:
                finish();

                break;
            case R.id.button_register:


                break;
            case R.id.edittext_login_username:
                

                break;
            case R.id.edittext_login_password:


                break;
            case R.id.button_login:


                break;
            case R.id.textview_login_password_back:


                break;
            case R.id.textview_login_byphone:
                startActivity(new Intent(LoginActivity.this, RegisterByPhoneActivity.class));

                break;
            case R.id.imageView_sina:


                break;
            case R.id.imageView_baidu:


                break;
            case R.id.imageView_tencent:


                break;

        }
    }
}
