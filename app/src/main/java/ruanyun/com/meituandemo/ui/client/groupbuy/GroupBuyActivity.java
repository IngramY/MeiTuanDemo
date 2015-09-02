package ruanyun.com.meituandemo.ui.client.groupbuy;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.androidquery.AQuery;
//import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import ruanyun.com.meituandemo.R;
import ruanyun.com.meituandemo.adapter.CityAdapter;
import ruanyun.com.meituandemo.adapter.GroupBuyLikeAdapter;
import ruanyun.com.meituandemo.adapter.MyViewPagerAdapter;
import ruanyun.com.meituandemo.myview.CirclePageIndicator;
import ruanyun.com.meituandemo.myview.MyListView;
import ruanyun.com.meituandemo.ui.client.AllClassActivity;
import ruanyun.com.meituandemo.ui.client.MyMapActivity;
import ruanyun.com.meituandemo.ui.client.SearchActivity;
import ruanyun.com.meituandemo.ui.client.WebViewActivity;

/**
 * Created by Administrator on 2015/7/6.
 */
public class GroupBuyActivity extends Activity implements View.OnClickListener,
        AdapterView.OnItemClickListener {

    private GridView gridview_menu1, gridview_menu2;//wanggebuju
    private ViewPager vPager;//
    private MyListView listview_like;//猜你喜欢
    private boolean hasMeasured;
    View contentView;
    LinearLayout linearLayout;
    GridView gv;
    ArrayList<View> viewContainter = new ArrayList<View>();//view数

    // gridView数据源
    private int[] images1 = new int[] { R.mipmap.ic_category_0,
            R.mipmap.ic_category_1, R.mipmap.ic_category_2,
            R.mipmap.ic_category_3, R.mipmap.ic_category_4,
            R.mipmap.ic_category_5, R.mipmap.ic_category_6,
            R.mipmap.ic_category_7 };
    private String[] names1 = new String[]{ "美食", "电影", "酒店", "KTY", "优惠买单", "周边游", "外卖", "休闲娱乐"};

    private int[] images2 = new int[] { R.mipmap.ic_category_8,
            R.mipmap.ic_category_9, R.mipmap.ic_category_10,
            R.mipmap.ic_category_11, R.mipmap.ic_category_12,
            R.mipmap.ic_category_13, R.mipmap.ic_category_14,
            R.mipmap.ic_category_15 };
    private String[] names2 = new String[]{ "丽人", "今日新单", "购物", "生活服务", "旅游", "足疗按摩", "小吃快餐", "全部分类"};


    /**
     * OnCreate
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groupbuy);
        init();
    }



    public void init() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View v1 =layoutInflater.inflate(R.layout.groupbuy_menu_grid1, null);//滑动的页卡1
        View v2 =layoutInflater.inflate(R.layout.groupbuy_menu_grid2, null);//滑动的页卡2

        gridview_menu1 = (GridView) v1.findViewById(R.id.gridview_groupbuy_menu1);//第一页的gridview
        gridview_menu2 = (GridView) v2.findViewById(R.id.gridview_groupbuy_menu2);

        //gridview 和listview 一样需要Adapter适配器
        gridview_menu1.setAdapter(this.getAdapter(images1, names1));
        gridview_menu2.setAdapter(this.getAdapter(images2, names2));

        gridview_menu1.setOnItemClickListener(this);
        gridview_menu2.setOnItemClickListener(this);

        vPager = (ViewPager) findViewById(R.id.vPager);
        viewContainter.add(v1);
        viewContainter.add(v2);
        vPager.setAdapter(new MyViewPagerAdapter(viewContainter));//viewpage适配器

        //viewpager指示器
        CirclePageIndicator indicator = (CirclePageIndicator)findViewById(R.id.indicator);
        indicator.setViewPager(vPager);

        listview_like = (MyListView) findViewById(R.id.lv_like_shop);//猜你喜欢
        listview_like.setAdapter(new GroupBuyLikeAdapter(getApplicationContext()));
        listview_like.setOnItemClickListener(this);//猜你喜欢列表

        AQuery aq = new AQuery(this);
        aq.id(R.id.tv_groupbuy_city).clicked(this);//城市
        aq.id(R.id.edittext_search).clicked(this);//搜索框
        aq.id(R.id.iv_groupbuy_map).clicked(this);//地图
        aq.id(R.id.button_lookall).clicked(this);//查看全部团购
        aq.id(R.id.button_personal_div).clicked(this);//我的美团DNA

        aq.id(R.id.linearlayout_groupbuy_yyyh_first).clicked(this);
        aq.id(R.id.linearlayout_groupbuy_yyyh_second_left).clicked(this);
        aq.id(R.id.linearlayout_groupbuy_yyyh_second_right).clicked(this);
        aq.id(R.id.linearlayout_groupbuy_yyyh_three_left).clicked(this);
        aq.id(R.id.linearlayout_groupbuy_yyyh_three_right).clicked(this);
        aq.id(R.id.linearlayout_groupbuy_bargain).clicked(this);
        aq.id(R.id.linearlayout_groupbuy_hot_restaurant).clicked(this);
    }

    /**
     * 地区选择泡泡窗口
     * @param view
     */
    private void showPopupWindow(View view) {
        // 一个自定义的布局，作为显示的内容
        contentView = LayoutInflater.from(this).inflate(
                R.layout.pop_city, null);
        linearLayout = (LinearLayout) contentView.findViewById(R.id.ll_pop);

        gv = (GridView) contentView.findViewById(R.id.gridview);
        hasMeasured = false;
        ViewTreeObserver vto = linearLayout.getViewTreeObserver();

        vto.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {

            public boolean onPreDraw() {
                if (hasMeasured == false) {
                    int  height = linearLayout.getMeasuredHeight();//保存linearlayout 的高度
                    //获取到宽度和高度后，可用于计算
                    ViewGroup.LayoutParams params = gv.getLayoutParams();
                    params.height = height * 3/5;
                    gv.setLayoutParams(params);

                    ViewGroup.LayoutParams params1 = gv.getLayoutParams();
                    params1.height = height * 3/5;
                    gv.setLayoutParams(params1);
                    hasMeasured = true;
                }
                return true;
            }
        });

        gv.setAdapter(new CityAdapter(GroupBuyActivity.this));

        final PopupWindow popupWindow = new PopupWindow(contentView,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT,
                true);//focusable属性

        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v = contentView.findViewById(R.id.rl_location);
                v.setOnClickListener(new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(GroupBuyActivity.this, CityListActivity.class));
                        popupWindow.dismiss();
                    }
                });

                return false;
                // 这里如果返回true的话，touch事件将被拦截
                // 拦截后 PopupWindow的onTouchEvent不被调用，这样点击外部区域无法dismiss
            }
        });

        // 如果不设置PopupWindow的背景，无论是点击外部区域还是Back键都无法dismiss弹框
        // 我觉得这里是API的一个bug
        popupWindow.setBackgroundDrawable(new BitmapDrawable());

        // 设置好参数之后再show
//        popupWindow.showAsDropDown(view);
        popupWindow.showAtLocation(view, Gravity.CENTER_HORIZONTAL, 0, 0);

    }

    /**
     * 视图点击事件
     * @param v
     */
    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.tv_groupbuy_city://城市列表
                showPopupWindow(v);
//                startActivity(new Intent(GroupBuyActivity.this, CityListActivity.class));
                break;

            case R.id.iv_groupbuy_map://地图界面
                startActivity(new Intent(GroupBuyActivity.this, MyMapActivity.class));
                break;

            case R.id.edittext_search://搜索界面
                startActivity(new Intent(GroupBuyActivity.this, SearchActivity.class));
                break;

            case R.id.button_lookall://查看全部团购
                startActivity(new Intent(GroupBuyActivity.this, AllClassActivity.class));
                break;

            case R.id.button_personal_div:
                startActivity(new Intent(GroupBuyActivity.this, DIYActivity.class));
                break;

            case R.id.linearlayout_groupbuy_yyyh_first:

            case R.id.linearlayout_groupbuy_yyyh_second_left:

            case R.id.linearlayout_groupbuy_yyyh_second_right:

            case R.id.linearlayout_groupbuy_yyyh_three_left:

            case R.id.linearlayout_groupbuy_yyyh_three_right:

            case R.id.linearlayout_groupbuy_bargain:

            case R.id.linearlayout_groupbuy_hot_restaurant:
                startActivity(new Intent(GroupBuyActivity.this, WebViewActivity.class));
                break;

            default:
                break;
        }
    }

    /**
     *  item点击事件
     * @param parent
     * @param view
     * @param position
     * @param id
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (parent.getId()) {
            case R.id.lv_like_shop:
                Toast.makeText(this, "点击了猜你喜欢 " + position, Toast.LENGTH_SHORT).show();

                break;
            case R.id.gridview_groupbuy_menu1:
                Toast.makeText(this, "点击了菜单1 的 " + position, Toast.LENGTH_SHORT).show();
                break;
            case R.id.gridview_groupbuy_menu2:
                Toast.makeText(this, "点击了菜单2 的 " + position, Toast.LENGTH_SHORT).show();
                break;
        }
    }

    /**
     * 获得一个simpleAdapter
     * @param images
     * @param names
     * @return
     */
    private ListAdapter getAdapter(int[] images, String[] names) {
        List<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < images.length; i++ ){
            HashMap<String, Object> item = new HashMap<String, Object>();
            item.put("image", images[i]);
            item.put("name", names[i]);
            data.add(item);
        }

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, data,
                R.layout.item_groupbuy_menu_gridview,
                new String[]{"image", "name"},
                new int[]{R.id.imageview_groupbuy_menu_item, R.id.textview_groupbuy_menu_item});
        return simpleAdapter;
    }

}
