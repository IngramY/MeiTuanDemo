package ruanyun.com.meituandemo.app;

import android.app.Application;

import com.baidu.mapapi.SDKInitializer;

/**
 * Created by Administrator on 2015/7/8.
 */
public class MyApplication extends Application {

    private int screenWidth;
    private int screenHeight;

    public int getScreenWidth() {
        return screenWidth;
    }

    public void setScreenWidth(int screenWidth) {
        this.screenWidth = screenWidth;
    }

    public int getScreenHeight() {
        return screenHeight;
    }

    public void setScreenHeight(int screenHeight) {
        this.screenHeight = screenHeight;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        SDKInitializer.initialize(this);//百度地图初始化配置

    }
/**
 * description ycw  ce shihdflkas   本地分支     设计时尚  测试
 * author ycw
 */

}
