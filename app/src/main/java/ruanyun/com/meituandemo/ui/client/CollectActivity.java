package ruanyun.com.meituandemo.ui.client;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import ruanyun.com.meituandemo.R;
import ruanyun.com.meituandemo.adapter.MyViewPagerAdapter;

public class CollectActivity extends Activity implements View.OnClickListener{

    private ViewPager viewPager;
    private LinearLayout layoutTitle;
    private ArrayList<View> viewlist;
//    private CursorIndicator cursorIndicator;
    private TextView tv_collect_groupbuy, tv_collect_merchant;
    private ImageView imageView_back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collect);

        layoutTitle = (LinearLayout) findViewById(R.id.layout_title);
//        cursorIndicator = (CursorIndicator) findViewById(R.id.indicator1);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        tv_collect_groupbuy = (TextView) findViewById(R.id.collect_groupbuy);
        tv_collect_merchant = (TextView) findViewById(R.id.collect_merchant);
        imageView_back = (ImageView) findViewById(R.id.iv_back);
        tv_collect_groupbuy.setTag(0);
        tv_collect_merchant.setTag(1);

        LayoutInflater inflater = LayoutInflater.from(this);
        View view1 = inflater.inflate(R.layout.collect_view1, null);
        View view2 = inflater.inflate(R.layout.collect_view2, null);
        viewlist = new ArrayList<View>();
        viewlist.add(view1);
        viewlist.add(view2);

        viewPager.setAdapter(new MyViewPagerAdapter(viewlist));
//        cursorIndicator.setViewPager(viewPager);
        tv_collect_groupbuy.setOnClickListener(this);
        tv_collect_merchant.setOnClickListener(this);
        imageView_back.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.collect_groupbuy:
                viewPager.setCurrentItem((Integer) view.getTag());
                break;
            case R.id.collect_merchant:
                viewPager.setCurrentItem((Integer) view.getTag());
                break;
            case R.id.iv_back:
                finish();
                break;

        }
    }
}
