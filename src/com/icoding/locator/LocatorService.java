package com.icoding.locator;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

public abstract class LocatorService extends Service implements LocatorManagerCallback{
	private static final String DEBUG = LocatorService.class.getSimpleName();
	
	public static final String BROADCAST_LOCATION_START = "com.icoding.locator.BROADCAST_LOCATION_START";
	public static final String BROADCAST_LOCATION_STOP = "com.icoding.locator.BROADCAST_LOCATION_STOP";
	public static final String BROADCAST_LOCATION_ERROR = "com.icoding.locator.BROADCAST_LOCATION_ERROR";
	public static final String BROADCAST_LOCATION_RECEIVED = "com.icoding.locator.BROADCAST_LOCATION_RECEIVED";
	public static final String EXTRA_TIME = "com.icoding.locator.EXTRA_TIME";
	public static final String EXTRA_ADDRESS = "com.icoding.locator.EXTRA_ADDRESS";
	public static final String EXTRA_COUNTRY = "com.icoding.locator.EXTRA_COUNTRY";
	public static final String EXTRA_LATITUDE = "com.icoding.locator.EXTRA_LATITUDE";
	public static final String EXTRA_LONGITUDE = "com.icoding.locator.EXTRA_LONGITUDE";
	public static final String EXTRA_BEARING = "com.icoding.locator.EXTRA_BEARING";
	public static final String EXTRA_SPEED = "com.icoding.locator.EXTRA_SPEED";
	public static final String EXTRA_ALTITUDE = "com.icoding.locator.EXTRA_ALTITUDE";
	public static final String EXTRA_RADIUS = "com.icoding.locator.EXTRA_RADIUS";
	public static final String EXTRA_ERROR = "com.icoding.locator.EXTRA_ERROR";
	public static final String EXTRA_PROVIDER = "com.icoding.locator.EXTRA_PROVIDER";
	public static final String EXTRA_FLOOR = "com.icoding.locator.EXTRA_FLOOR";
	public static final String EXTRA_ACCURACY = "com.icoding.locator.EXTRA_ACCURACY";
	public static final String EXTRA_LOCATION = "com.icoding.locator.EXTRA_LOCATION";
	private LocatorManager<LocatorManagerCallback> mLocatorManager;
	
	@Override
	@SuppressWarnings("unchecked")
	public void onCreate() {
		super.onCreate();
		mLocatorManager = initializeManager();
		mLocatorManager.setCallback(this);
		Log.d(DEBUG, "定位服务已创建");
	}
	
	@SuppressWarnings("rawtypes")
	protected abstract LocatorManager initializeManager();
	
	@Override
	public IBinder onBind(Intent intent) {
		return null;
	}
	
	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if(mLocatorManager != null){
			mLocatorManager.startLocation(true);
		}
		return START_FLAG_REDELIVERY;
	}
	
	@Override
	public void onDestroy() {
		if(mLocatorManager != null){
			mLocatorManager.stopLocation();
			mLocatorManager = null;
		}
		super.onDestroy();
	}
	
	@Override
	public void onLocationStart() {
		Log.d(DEBUG, "定位服务已开始定位");
		Intent broadcast = new Intent(BROADCAST_LOCATION_START);
		LocalBroadcastManager.getInstance(getApplicationContext())
			.sendBroadcast(broadcast);
		
	}
	
	@Override
	public void onLocationStop() {
		Log.d(DEBUG, "定位服务已停止定位");
		Intent broadcast = new Intent(BROADCAST_LOCATION_STOP);
		LocalBroadcastManager.getInstance(getApplicationContext())
			.sendBroadcast(broadcast);
	}
	
	@Override
	public void onLocationReceived(ILocation location) {
		Log.d(DEBUG, "定位服务已收到位置信息");
		StringBuffer sb = new StringBuffer(256);
		sb.append("time : ");
		sb.append(location.getTime());
		sb.append("\nerror code : ");
		sb.append(location.getErrorCode());
		sb.append("\ncountry name : ");
		sb.append(location.getCountry());
		sb.append("\nlatitude : ");
		sb.append(location.getLatitude());
		sb.append("\nlontitude : ");
		sb.append(location.getLongitude());
		sb.append("\naddress: ");
		sb.append(location.getAddress());
		sb.append("\nprovader:");
		sb.append(location.getProvider());
		Log.i(DEBUG,sb.toString());
		Intent broadcast = new Intent(BROADCAST_LOCATION_RECEIVED);
		broadcast.putExtra(EXTRA_LOCATION, location);
		LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(broadcast);
		stopSelf();
	}
	
	@Override
	public void onLocationError(int errorCode) {
		Log.e(DEBUG, "定位发生错误:" + errorCode);
		Intent broadcast = new Intent(BROADCAST_LOCATION_ERROR);
		broadcast.putExtra(EXTRA_ERROR, errorCode);
		LocalBroadcastManager.getInstance(getApplicationContext())
			.sendBroadcast(broadcast);
	}
}
