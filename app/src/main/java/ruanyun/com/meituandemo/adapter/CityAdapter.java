package ruanyun.com.meituandemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import ruanyun.com.meituandemo.R;

/**
 * Created by Administrator on 2015/7/29.
 */
public class CityAdapter extends BaseAdapter {

    private Context context;
    String[] strings = new String[]{"全城","新站区","滨湖区","经开区","政务区","高新区","巢湖市","长丰县","肥东县","肥西县",
            "近郊","包河区","瑶海区","蜀山区","庐阳区","庐江县"};

    public CityAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return strings.length;
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
        Holder holder = null;
        if (holder == null) {
            holder = new Holder();
            convertView = LayoutInflater.from(context).inflate(R.layout.item_city_location, null);
            holder.textView = (TextView) convertView.findViewById(R.id.textview_location_part);
            convertView.setTag(holder);
        }else{
            holder = (Holder) convertView.getTag();
        }
        holder.textView.setText(strings[position]);
        return convertView;
    }

    public class Holder{
        TextView textView;
    }
}
