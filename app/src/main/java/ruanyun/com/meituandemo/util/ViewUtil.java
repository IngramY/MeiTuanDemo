package ruanyun.com.meituandemo.util;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;

public class ViewUtil {
    @SuppressWarnings ("unchecked")
    public static <T extends View> T getViewById (int layout, int id, Context context) {

        View view = LayoutInflater.from(context).inflate(layout, null, false);

        return (T) view.findViewById(id);
    }
}
