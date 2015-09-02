package ruanyun.com.meituandemo.ui.client.merchant;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import ruanyun.com.meituandemo.R;
import ruanyun.com.meituandemo.adapter.MerchantAllAdapter;
import ruanyun.com.meituandemo.adapter.MyViewPagerAdapter;
import ruanyun.com.meituandemo.data.LocationBean;
import ruanyun.com.meituandemo.listener.MyLocationListennerControl;
import ruanyun.com.meituandemo.ui.client.MyMapActivity;
import ruanyun.com.meituandemo.ui.client.SearchActivity;

public class MerchantActivity extends Activity implements View.OnClickListener {

    private Button button_salemerchant;//打折商家
    private Button button_allmerchant;//所有商家
    private ImageButton imagebutton_map;//地图imagebutton
    private ImageButton imagebutton_search;//查找imagebutton
    public static ListView aListView,sListView;//商家列表
    private String title[]= {"全部分类","美食","酒店","电影","休闲娱乐","生活服务","丽人"};

    private ViewPager viewPager;
    private ArrayList<View> viewlist = new ArrayList<View>();
    private View mask;
    private TextView chooseType, chooseLabel, chooseOrder;
    private LinearLayout dropdownType, dropdownLabel, dropdownOrder;
    private FrameLayout frameLayout;
    private boolean hasMeasured = false;

    private LocationBean locationBean = new LocationBean();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_merchant);

        MyLocationListennerControl myLocationListennerControl = new MyLocationListennerControl(this);
        myLocationListennerControl.initLocationListenner();
        myLocationListennerControl.start();
        locationBean = myLocationListennerControl.getLocationBean();

        initview();
    }


    private void initview() {
        LayoutInflater inflater = LayoutInflater.from(this);
        View v1 = inflater.inflate(R.layout.merchantall_listview, null);
        View v2 = inflater.inflate(R.layout.merchantsale_listview, null);
        View view_location = inflater.inflate(R.layout.merchant_list_header, null);
        TextView textView = (TextView) view_location.findViewById(R.id.textview_location);
        textView.setText(locationBean.getAddrStr());

        viewlist.add(v1);
        viewlist.add(v2);
        viewPager = (ViewPager) findViewById(R.id.merchant_viewpager);
        aListView = (ListView) v1.findViewById(R.id.listview_merchantall);//所有商家listview
        sListView = (ListView) v2.findViewById(R.id.listview_merchantsale);//优惠商家listview

        button_allmerchant = (Button) findViewById(R.id.button_allmerchant);//所有商家
        button_salemerchant = (Button) findViewById(R.id.button_salemerchant);//优惠商家
        imagebutton_map = (ImageButton) findViewById(R.id.imagebutton_map);//地图
        imagebutton_search = (ImageButton) findViewById(R.id.imagebutton_search);//搜索

        mask = findViewById(R.id.mask);//图层
        chooseType = (TextView) findViewById(R.id.chooseType);//分类按钮
        chooseLabel = (TextView) findViewById(R.id.chooseLabel);//距离按钮
        chooseOrder = (TextView) findViewById(R.id.chooseOrder);//排序按钮

        dropdownType = (LinearLayout) findViewById(R.id.dropdownType);//分类按钮对应视图
        dropdownLabel = (LinearLayout) findViewById(R.id.dropdownLabel);//距离按钮对应视图
        dropdownOrder = (LinearLayout) findViewById(R.id.dropdownOrder);//排序按钮对应视图

        frameLayout = (FrameLayout) findViewById(R.id.framelayout);
        ViewTreeObserver vto = frameLayout.getViewTreeObserver();
        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            public boolean onPreDraw() {
                if (hasMeasured == false) {
                    int  height_framelayout = frameLayout.getMeasuredHeight();//保存framelayout 的高度
                    //获取到宽度和高度后，可用于计算
                    ViewGroup.LayoutParams params = dropdownType.getLayoutParams();
                    params.height = height_framelayout * 4/5;
                    dropdownType.setLayoutParams(params);

                    ViewGroup.LayoutParams params1 = dropdownLabel.getLayoutParams();
                    params1.height = height_framelayout * 4/5;
                    dropdownLabel.setLayoutParams(params1);
                    hasMeasured = true;
                }
                return true;
            }
        });

        aListView.setAdapter(new MerchantAllAdapter(this));
        sListView.setAdapter(new MerchantAllAdapter(this));
        aListView.addHeaderView(view_location);
        sListView.addHeaderView(view_location);

        viewPager.setAdapter(new MyViewPagerAdapter(viewlist));
        viewPager.setCurrentItem(0);
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                Toast.makeText(MerchantActivity.this,"当前页面是" + position,Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 0:
                        // 刷新全部商家和优惠商家的radioButton下端的图片
                        button_allmerchant
                                .setCompoundDrawablesWithIntrinsicBounds(0, 0,
                                        0, R.drawable.bottom_divider);
                        button_salemerchant
                                .setCompoundDrawablesWithIntrinsicBounds(0, 0,
                                        0, R.color.green);
                        break;
                    case 1:
                        //被点击的视优惠商家按钮
                        button_salemerchant
                                .setCompoundDrawablesWithIntrinsicBounds(0, 0,
                                        0, R.drawable.bottom_divider);
                        button_allmerchant
                                .setCompoundDrawablesWithIntrinsicBounds(0, 0,
                                        0, R.color.green);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        chooseType.setOnClickListener(this);
        chooseLabel.setOnClickListener(this);
        chooseOrder.setOnClickListener(this);
        mask.setOnClickListener(this);
        imagebutton_map.setOnClickListener(this);
        imagebutton_search.setOnClickListener(this);
        button_allmerchant.setOnClickListener(this);
        button_salemerchant.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.chooseType :
                if (dropdownType.getVisibility() == View.GONE) {
                    dropdownType.setVisibility(View.VISIBLE);
                    mask.setVisibility(View.VISIBLE);
                }else{
                    dropdownType.setVisibility(View.GONE);
                    mask.setVisibility(View.GONE);
                }
                dropdownLabel.setVisibility(View.GONE);
                dropdownOrder.setVisibility(View.GONE);

                break;
            case R.id.chooseLabel :
                if (dropdownLabel.getVisibility() == View.GONE) {
                    dropdownLabel.setVisibility(View.VISIBLE);
                    mask.setVisibility(View.VISIBLE);
                }else{
                    dropdownLabel.setVisibility(View.GONE);
                    mask.setVisibility(View.GONE);
                }
                dropdownType.setVisibility(View.GONE);
                dropdownOrder.setVisibility(View.GONE);

                break;
            case R.id.chooseOrder :
                if (dropdownOrder.getVisibility() == View.GONE) {
                    dropdownOrder.setVisibility(View.VISIBLE);
                    mask.setVisibility(View.VISIBLE);
                }else{
                    dropdownOrder.setVisibility(View.GONE);
                    mask.setVisibility(View.GONE);
                }
                dropdownLabel.setVisibility(View.GONE);
                dropdownType.setVisibility(View.GONE);
                break;
            case R.id.mask:
                HideView();
                break;
            case R.id.imagebutton_map:
                HideView();
                startActivity(new Intent(MerchantActivity.this, MyMapActivity.class));
                break;

            case R.id.imagebutton_search:
                HideView();
                startActivity(new Intent(MerchantActivity.this, SearchActivity.class));
                break;
            case R.id.button_salemerchant:
                //被点击的视优惠商家按钮
                button_salemerchant
                        .setCompoundDrawablesWithIntrinsicBounds(0, 0,
                                0, R.drawable.bottom_divider);
                button_allmerchant
                        .setCompoundDrawablesWithIntrinsicBounds(0, 0,
                                0, R.color.green);
                viewPager.setCurrentItem(1);
                HideView();
                break;
            case R.id.button_allmerchant:
                // 刷新全部商家和优惠商家的radioButton下端的图片
                button_allmerchant
                        .setCompoundDrawablesWithIntrinsicBounds(0, 0,
                                0, R.drawable.bottom_divider);
                button_salemerchant
                        .setCompoundDrawablesWithIntrinsicBounds(0, 0,
                                0, R.color.green);
                viewPager.setCurrentItem(0);
                HideView();
                break;
            default:
                HideView();
                break;
        }
    }

    private void HideView(){
        mask.setVisibility(View.GONE);
        dropdownType.setVisibility(View.GONE);
        dropdownLabel.setVisibility(View.GONE);
        dropdownOrder.setVisibility(View.GONE);

    }




}
