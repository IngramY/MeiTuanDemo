package ruanyun.com.meituandemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import ruanyun.com.meituandemo.R;

/**
 * Created by Administrator on 2015/7/25.
 */
public class MerchantAllAdapter extends BaseAdapter {
    private Context context;

    public MerchantAllAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 10;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
//        ViewHolder viewHolder;
//        if (view == null){
//            viewHolder = new ViewHolder();
            view = LayoutInflater.from(context).inflate(R.layout.item_merchant, null);
//            viewHolder.imageView = (ImageView) view.findViewById(R.id.imageview_list_view);
//            viewHolder.textView1 = (TextView) view.findViewById(R.id.textview_list_item);
//            viewHolder.textView2 = (TextView) view.findViewById(R.id.textview_list_describe);
//            viewHolder.textView3 = (TextView) view.findViewById(R.id.textview_list_current_price);
//            viewHolder.textView4 = (TextView) view.findViewById(R.id.textview_list_original_price);
//            viewHolder.textView5 = (TextView) view.findViewById(R.id.textview_list_sold);
//            view.setTag(viewHolder);
//        } else {
//            viewHolder = (ViewHolder) view.getTag();
//        }

//        viewHolder.imageView.setImageResource(R.mipmap.bg_default_poi_list);
//        viewHolder.textView1.setText("饭店");
//        viewHolder.textView2.setText("饭菜都好吃");
//        viewHolder.textView3.setText("9.9元");
//        viewHolder.textView4.setText("11元");
//        viewHolder.textView5.setText("已售1111");

        return view;
    }

    class ViewHolder {
        private ImageView imageView;
        private TextView textView1, textView2, textView3, textView4, textView5;

    }
}
