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
 * Created by Administrator on 2015/7/7.
 */
public class GroupBuyLikeAdapter extends BaseAdapter {

    private Context context;
    public GroupBuyLikeAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return 20;
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null){
            viewHolder = new ViewHolder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_like, null);
            viewHolder.imageView = (ImageView) convertView.findViewById(R.id.imageview_list_view);
            viewHolder.textView1 = (TextView) convertView.findViewById(R.id.textview_list_item);
            viewHolder.textView2 = (TextView) convertView.findViewById(R.id.textview_list_describe);
            viewHolder.textView3 = (TextView) convertView.findViewById(R.id.textview_list_current_price);
            viewHolder.textView4 = (TextView) convertView.findViewById(R.id.textview_list_original_price);
            viewHolder.textView5 = (TextView) convertView.findViewById(R.id.textview_list_sold);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.imageView.setImageResource(R.mipmap.bg_default_poi_list);
        viewHolder.textView1.setText("饭店");
        viewHolder.textView2.setText("饭菜都好吃");
        viewHolder.textView3.setText("9.9元");
        viewHolder.textView4.setText("11元");
        viewHolder.textView5.setText("已售1111");

        return convertView;
    }

    class ViewHolder {
        private ImageView imageView;
        private TextView textView1, textView2, textView3, textView4, textView5;

    }
}
