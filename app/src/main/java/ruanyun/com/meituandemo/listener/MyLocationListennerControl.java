package ruanyun.com.meituandemo.listener;

import android.content.Context;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import ruanyun.com.meituandemo.data.LocationBean;

/**
 * Created by Administrator on 2015/8/1.
 */
public class MyLocationListennerControl {

    private Context context;
    private LocationClient mLocClient;
    private MyLocationListenner myListener = new MyLocationListenner();
    private LocationBean locationBean = new LocationBean();

    public MyLocationListennerControl(Context context) {
        this.context = context;
    }

    public LocationBean getLocationBean() {
        return locationBean;
    }


    public void initLocationListenner(){
        mLocClient = new LocationClient(context);
        mLocClient.registerLocationListener(myListener);
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true);// 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);
        mLocClient.setLocOption(option);
//        mLocClient.start();
    }

    public void start(){
        mLocClient.start();
    }

    public void stop(){
        mLocClient.stop();
    }

    /**
     * 定位SDK监听函数
     */
    public class MyLocationListenner implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            // map view 销毁后不在处理新接收的位置
            if (location == null)
                return;
            locationBean.setAddrStr(location.getAddrStr());
            locationBean.setCity(location.getCity());
            locationBean.setLongitude(location.getLongitude());
            locationBean.setLatitude(location.getLatitude());
        }
    }

}
