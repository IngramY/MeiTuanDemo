package ruanyun.com.meituandemo.data;

/**
 * Created by Administrator on 2015/8/1.
 */
public class LocationBean {
    private String AddrStr;//详细地址信息
    private String City;//获取城市
    private double Latitude;//纬度坐标
    private double Longitude;//经度坐标

    public String getAddrStr() {
        return AddrStr;
    }

    public void setAddrStr(String addrStr) {
        AddrStr = addrStr;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }
}
