package ruanyun.com.meituandemo.util;

import android.app.Application;
import android.content.ClipboardManager;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AppUtility {

    @SuppressWarnings ("unused")
    private static final String TAG = "AppUtility";
    private static Context context;
    //private static Toast toast;

    public static void setContext (Application app) {
        context = app.getApplicationContext();
    }

    public static Context getContext () {
        return context;
    }

	/*
     * public static void showToastMsg(Context context, String msg) {
	 * Toast.makeText(context, msg, Toast.LENGTH_SHORT).show(); }
	 * 
	 * public static void showToastMsg(Context context, int id) {
	 * Toast.makeText(context, id, Toast.LENGTH_SHORT).show(); }
	 */

    public static boolean isNotEmpty (String str) {
        return str != null && !"".equals(str) && !"null".equals(str);
    }

    public static void showToastMsg (Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

    public static void showLongToastMsg (Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    public static void showToastMsg (Context context, int id) {
        Toast.makeText(context, id, Toast.LENGTH_SHORT).show();
    }

    public static void showHttpError (Context mContext, Throwable cause) {
        String message = "请求失败";
        if (cause != null) {
            if (cause.toString().contains("java.net.SocketTimeoutException")) {
                message = "服务器未响应,请稍后重试";
            } else if (cause.toString().contains("java.net.ConnectException")) {
                message = "请检查网络连接！";
            }
        }
        showToastMsg(mContext, message);
    }

    /***
     * 判断网络是否连接
     *
     * @param context
     * @return
     */
    public static boolean isNetworkAvailable (Context context) {
        ConnectivityManager cm = (ConnectivityManager) context

                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm == null) {
        } else {// 如果仅仅是用来判断网络连接
            // 则可以使用 cm.getActiveNetworkInfo().isAvailable();

            NetworkInfo[] info = cm.getAllNetworkInfo();
            if (info != null) {
                for (int i = 0; i < info.length; i++) {
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
    //
    //	public static void OnSessionTimeOut(Activity activity, int resultCode) {
    //		if (resultCode == Constant.SESSION_TIME_OUT) {
    //			Intent intent = new Intent(activity, LoginActivity.class);
    //			intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
    //			ApiModule.resetHeader();
    //			XlApplication.getInstantce().setLoginStatus(false);
    //			LocalBroadcastManager.getInstance(activity).sendBroadcast(new Intent(Constant.ACTION_LOGOUT));
    //			activity.startActivity(intent);
    //			activity.finish();
    //		}
    //	}

    /***
     * 获取当前日期与时间
     *
     * @param
     * @return
     */
    public static String getCurrentDateAndTime () {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String date = sDateFormat.format(new Date());
        return date;

    }

    public static String getCurrentDate () {
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String date = sDateFormat.format(new Date());
        return date;

    }

    public static String getCurrentTime () {
        return new SimpleDateFormat("MM-dd HH:mm", Locale.CHINA).format(new Date());
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px (Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip (Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    // 获取屏幕的大小
    public static Screen getScreenPix (Context context) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager windowManager = (WindowManager) context.getSystemService(
                Context.WINDOW_SERVICE);
        windowManager.getDefaultDisplay().getMetrics(dm);
        return new Screen(dm.widthPixels, dm.heightPixels);
    }

    public static class Screen {

        public int widthPixels;
        public int heightPixels;

        public Screen () {

        }

        public Screen (int widthPixels, int heightPixels) {
            this.widthPixels = widthPixels;
            this.heightPixels = heightPixels;
        }

        @Override
        public String toString () {
            return "(" + widthPixels + "," + heightPixels + ")";
        }

    }

    /**
     * @param content
     * @Description:复制文本到剪贴板
     * @author sjj
     * @date 2016-1-20 下午4:31:46
     */
    @SuppressWarnings ("deprecation")
    public static void copy (String content) {
        // 得到剪贴板管理器
        ClipboardManager cmb = (ClipboardManager) context.getSystemService(
                Context.CLIPBOARD_SERVICE);
        cmb.setText(content.trim());
    }

    /**
     * 功能描述:将文件大小转换成MB,KB,GB形式
     *
     * @param size
     * @return
     * @author shengguo 2014-1-23 下午3:41:07
     */
    public static int formetFileSize (long size) {
        String length_display = null;
        int b_size = 0;
        if (size < 1024) {
            length_display = size + "B";
        } else if (size < 1048576 && size >= 1024) {
            if ((size / 1024 + "").contains(".") && (size / 1024 + "").substring(
                    (size / 1024 + "").lastIndexOf(".")).length() > 3) {
                length_display = (size / 1024 + "").substring(0, (size / 1024 + "").lastIndexOf(
                        ".") + 3) + "KB";
                b_size = Integer.parseInt(
                        length_display.substring(0, length_display.indexOf("B") * 1024));
            } else {
                length_display = size / 1024 * 1024 + "B";
            }
        } else if (size <= 1073741824 && size >= 1048576) {
            if ((size / 1048576 + "").contains(".") && (size / 1048576 + "").substring(
                    (size / 1048576 + "").lastIndexOf(".")).length() > 3) {
                length_display = (size / 1048576 + "").substring(0,
                                                                 (size / 1048576 + "").lastIndexOf(
                                                                         ".") + 3) + "MB";
            } else {
                length_display = size / 1048576 * 1024 * 1024 + "B";
            }

        } else {
            if ((size / 1073741824 + "").contains(".") && (size / 1073741824 + "").substring(
                    (size / 1073741824 + "").lastIndexOf(".")).length() > 3) {
                length_display = (size / 1073741824 + "").substring(0,
                                                                    (size / 1073741824 + "").lastIndexOf(
                                                                            ".") + 3) + "GB";
            } else {
                length_display = size / 1073741824 + "GB";
            }

        }
        b_size = Integer.parseInt(length_display.substring(0, length_display.indexOf("B")));
        return b_size;
    }

    /**
     * 动态设置ListView的高度
     *
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren (ListView listView) {
        if (listView == null)
            return;
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            // pre-condition
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    /***
     * 判断editText是否为空
     *
     * @param editText
     * @param <E>
     * @return
     * @author Daniel
     */
    public static <E extends EditText> boolean isEditTextNotEmpty (E editText) {
        return isNotEmpty(editText.getText().toString().trim());
    }

    /***
     * 判断editText列表是否为空
     *
     * @param editTextList
     * @param <E>
     * @return
     * @author Daniel
     */
    public static <E extends EditText> boolean isEditTextNotEmpty (List<E> editTextList) {
        for (E e : editTextList) {
            if (!isEditTextNotEmpty(e)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 根据用户生日计算年龄
     */
    public static int getAgeByBirthday (Date birthday) {
        Calendar cal = Calendar.getInstance();

        if (cal.before(birthday)) {
            return -1;
        }

        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(birthday);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);

        int age = yearNow - yearBirth;

        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                // monthNow==monthBirth
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else {
                // monthNow>monthBirth
                age--;
            }
        }
        return age;
    }

    /**
     * @description: 截取长时间的string
     * @author: Daniel
     */
    public static String getDateString (String secondString) {
        if (null != secondString) {
            if (secondString.length() > 9) {
                return secondString.substring(0, 10);
            }
        }
        return "";

    }
}
