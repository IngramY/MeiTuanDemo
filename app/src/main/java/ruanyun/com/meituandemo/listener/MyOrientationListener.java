package ruanyun.com.meituandemo.listener;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

/**
 * 传感器监听器
 */
public class MyOrientationListener implements SensorEventListener {
	private SensorManager mSensorManager;
	private Context mContext;
	private Sensor mSensor;
	private float lastX;

	public MyOrientationListener(Context context) {
		this.mContext = context;
	}

	/**
	 * 获得管理器 方向传感器 并注册
	 */
	public void start() {
		mSensorManager = (SensorManager) mContext.getSystemService(Context.SENSOR_SERVICE);
		if (mSensorManager != null) {
			// 获得方向传感器
			mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION);
		}

		if (mSensor != null) {
			//注册方向传感器
			mSensorManager.registerListener(this, mSensor, SensorManager.SENSOR_DELAY_UI);
		}
	}

	/**
	 * 关闭传感器注册监听
	 */
	public void stop() {
		mSensorManager.unregisterListener(this);
	}

	@Override
	public void onAccuracyChanged(Sensor arg0, int arg1) {

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		if (event.sensor.getType() == Sensor.TYPE_ORIENTATION) {
			float x = event.values[SensorManager.DATA_X];

			if (Math.abs(x - lastX) > 1.0) { //取绝对值
				if (mOnOrientationListener != null) {
					mOnOrientationListener.onOrientationChanged(x);//调用接口方法
				}
			}
			lastX = x;
		}
	}

	private OnOrientationListener mOnOrientationListener;

	public void setOnOrientationListener(OnOrientationListener mOnOrientationListener) {
		this.mOnOrientationListener = mOnOrientationListener;
	}

	public interface OnOrientationListener {
		void onOrientationChanged(float x);
	}

}
