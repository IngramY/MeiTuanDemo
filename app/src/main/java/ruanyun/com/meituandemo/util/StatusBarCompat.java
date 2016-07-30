package ruanyun.com.meituandemo.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.Context;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;

import ruanyun.com.meituandemo.R;


/***
 * 状态栏透明 工具类
 * @author jery
 *
 */
public class StatusBarCompat {
    private static final int INVALID_VAL = -1;
    //    private static final int COLOR_DEFAULT = Color.parseColor("#25B6ED"); //默认颜色

    @TargetApi (VERSION_CODES.LOLLIPOP)
    public static void compat (Activity activity, int statusColor) {
        int colorDefault = activity.getResources().getColor(R.color.theme_color_default); // 默认颜色
        if (VERSION.SDK_INT == VERSION_CODES.KITKAT) {
            // 透明状态栏
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            // 透明导航栏
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }

        if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
            int v = VERSION.SDK_INT;
            activity.getWindow().setStatusBarColor(colorDefault);

            return;
        }

        if (VERSION.SDK_INT >= VERSION_CODES.KITKAT && VERSION.SDK_INT < VERSION_CODES.LOLLIPOP) {
            int color = colorDefault;
            ViewGroup contentView = (ViewGroup) activity.findViewById(android.R.id.content);
            if (statusColor != INVALID_VAL) {
                color = statusColor;
            }
            View statusBarView = contentView.getChildAt(0);
            // 改变颜色时避免重复添加statusBarView
            if (statusBarView != null && statusBarView.getMeasuredHeight() == getStatusBarHeight(
                    activity)) {
                statusBarView.setBackgroundColor(color);
                return;
            }
            statusBarView = new View(activity);
            ViewGroup.LayoutParams lp = new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity));
            statusBarView.setBackgroundColor(color);
            contentView.addView(statusBarView, lp);
        }

    }
   
/*    @SuppressLint("InlinedApi")
    public static void compatTransparent(Activity activity){
    	if (VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
			// 透明状态栏
			activity.getWindow().addFlags(
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
			// 透明导航栏
			activity.getWindow().addFlags(
					WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
		
		}
    }*/


    public static void compat (Activity activity) {
        compat(activity, INVALID_VAL);
    }


    public static int getStatusBarHeight (Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen",
                                                              "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }
}
