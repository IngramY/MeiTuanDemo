package ruanyun.com.meituandemo.adapter;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Administrator on 2015/7/12.
 */
public class MyViewPagerAdapter extends PagerAdapter {

    ArrayList<View> viewContainter = new ArrayList<View>();//view数

    public MyViewPagerAdapter(ArrayList<View> viewContainter) {
        this.viewContainter = viewContainter;
    }

    /**
     * view 页的个数
     * @return
     */
    @Override
    public int getCount() {
        return viewContainter.size();
    }

    /**
     * @param view
     * @param object
     *
     * @return
     */
    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    //滑动切换的时候销毁当前的组件
    @Override
    public void destroyItem(ViewGroup container, int position,
                            Object object) {
        ((ViewPager) container).removeView(viewContainter.get(position));
    }

    //每次滑动的时候生成的组件
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        ((ViewPager) container).addView(viewContainter.get(position));
        return viewContainter.get(position);
    }

}
